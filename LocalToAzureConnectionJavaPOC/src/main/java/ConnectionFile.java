import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.file.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;


public class ConnectionFile {
    public static void main(String args[]){
        final String storageConnectionString=
                "DefaultEndpointsProtocol=https;"+
                        "AccountName=*accountname;"+
                        "AccountKey=*account key*";


        try{
            CloudStorageAccount storageAccount= CloudStorageAccount.parse(storageConnectionString);

            //Create the Azure Files client
            CloudFileClient fileClient= storageAccount.createCloudFileClient();

            //Get a reference to the file share
            CloudFileShare share=fileClient.getShareReference("Sample_Mail_Dir");

            if(share.createIfNotExists()){
                System.out.println("New share created");
            }else{
                System.out.println("share dir already present");
            }

            /* to delete the sample  share
            if(share.deleteIfExists()){
                System.out.println("Delete");
            }
             */

            //Get a reference to the root directory for the share
            CloudFileDirectory rootDir=share.getRootDirectoryReference();

            //Get a reference to the sample_main_dir directory
            CloudFileDirectory sampleDir= rootDir.getDirectoryReference("Child_Dir");

            if(sampleDir.createIfNotExists()){
                System.out.println("Child directory created");
            }else{
                System.out.println("Already exists");
            }

            /* to delete the sample  share
            if(sampleDir.deleteIfExists()){
                System.out.println("Delete");
            }
             */

            //Define path to the local file
            final String filePath= "c:\\users\\example.txt";

            CloudFile cloudFile=sampleDir.getFileReference("example.txt");
            cloudFile.uploadFromFile(filePath);

            //Download a file, write the content to the file to the console
            //System.out.println(cloudFile.downloadText());

            //delete the file
            /* to delete the sample  share
            if(cloudFile.deleteIfExists()){
                System.out.println("Delete");
            }
             */
        }catch (InvalidKeyException invalidKey){
            invalidKey.printStackTrace();
        }catch (URISyntaxException e){
            e.printStackTrace();
        }catch (StorageException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
