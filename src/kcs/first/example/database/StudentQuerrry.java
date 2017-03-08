package kcs.first.example.database;

import kcs.first.example.WO.Studenttt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Egidijus on 3/7/2017.
 */
public class StudentQuerrry
{
    private Connection connection;
    public StudentQuerrry(Connection connection)
    {
        this.connection=connection;
    }

    public void getStudents()
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
    public void updateStudentName(int studentID, String name)
    {
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate("UPDATE students SET NAME ='"+name+"' WHERE id ="+studentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertStudent(Studenttt studenttt)
    {
        try {
            Statement statement=connection.createStatement();
            statement.execute("INSERT INTO students(name,surname,phone,email) VALUE('"+studenttt.getName()+"','"+studenttt.getSurname()+"','"+
                    studenttt.getPhone()+"','"+studenttt.getEmail()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public boolean isStudentExists(String name, String surname)
    {
        boolean doesExist=false;
        try {
            Statement statement=connection.createStatement();
            ResultSet resulset= statement.executeQuery("SELECT * FROM students");
            while (resulset.next()){
                        if(resulset.getString("name").equals(name)&&resulset.getString("surname").equals(surname)){
                            doesExist=true;
                        }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doesExist;
    }*/
    public boolean isStudentExistsV2(String name,String surname)
    {
        boolean doesExist=false;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM students WHERE surname ='"+surname+"' AND name='"+name+"'");
            doesExist=resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doesExist;
    }
    public List<Studenttt> listStudents()
    {
        List<Studenttt> studentttList=new LinkedList<>();
        try {
                Statement st= connection.createStatement();
                ResultSet resulset= st.executeQuery("SELECT * FROM students");
                while (resulset.next()){
                    studentttList.add(new Studenttt(resulset.getInt("id"), resulset.getString("name"),
                            resulset.getString("surname"),resulset.getString("phone"),resulset.getString("email")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return studentttList;
    }
}


