package kcs.first.example;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by Egidijus on 3/7/2017.
 */
public class MyFirstConnection
{
    private static void getStudents(Connection connection)
    {
        try {
            Statement st= connection.createStatement();
           ResultSet resulset= st.executeQuery("SELECT * FROM students");
           while (resulset.next()){
               System.out.println("id>> "+resulset.getInt(1)
                       +" username >> "+ resulset.getString("name")
               +" surname >> "+ resulset.getString("surname")
               +" phone >> "+resulset.getString(4)
               +" email >> "+resulset.getString("email"));

           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        try {
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/kcs","root","");
            if(connection !=null){
                System.out.println("Connected");
                getStudents(connection);
            }
        } catch (SQLException e) {
            System.out.println("Connection to DB failed "+e);
        }
    }
}
