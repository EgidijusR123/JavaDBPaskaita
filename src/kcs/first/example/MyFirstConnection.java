package kcs.first.example;

import kcs.first.example.WO.Studenttt;
import kcs.first.example.database.StudentQuerrry;
import kcs.utils.JDBCUtils;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

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
        Scanner sc=new Scanner(System.in);
        try {
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/kcs","root","");
            if(connection !=null){
                System.out.println("Connected");
                StudentQuerrry studentQuerrry=new StudentQuerrry(connection);
                studentQuerrry.getStudents();
                System.out.println(JDBCUtils.isTableExist(connection,"students"));
                studentQuerrry.updateStudentName(3,"Jokubas");
                //studentQuerrry.insertStudent(new Studenttt("Naujas","Naujokaitis","867993456","naujasnaujokaitis@gmail.com"));
                System.out.println(studentQuerrry.isStudentExistsV2("Nauja","Naujokaitis"));
                for(Studenttt studenttt:studentQuerrry.listStudents()){
                    System.out.println(studenttt);
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection to DB failed "+e);
        }
    }
}
