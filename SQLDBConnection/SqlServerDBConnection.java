package arrays;

import java.sql.*;

public class SqlServerDBConnection {
    public static void main(String args[]) {
        Connection con=null;
        String conURL="jdbc:sqlserver://hostnamename:port;database=*name*;user=*name*;password=";

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con= DriverManager.getConnection(conURL);

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Query to be executed");
           // ResultSet rs=stmt.executeUpdate("Query to be executed");
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getString(2));
            }
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}