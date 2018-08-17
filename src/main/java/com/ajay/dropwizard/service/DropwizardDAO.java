package com.ajay.dropwizard.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DropwizardDAO {

   private Connection connection = null;
   private Statement statement = null;
   private ResultSet resultSet = null;
   private Student student = new Student();
   private List<Student> studentList = new ArrayList<Student>();

    private void createDbConnection(){
        try {
            // Step 1: Loading or registering Oracle JDBC driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String msAccDB = "C:/Users/ajay/Documents/Database2.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;

            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL);

            // Step 2.B: Creating JDBC Statement
            statement = connection.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void closeDbConnection() {
        try{
            if(null != connection){
                resultSet.close();
                statement.close();;
                connection.close();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public List<Student> getAllStudentList(){
        try {
            createDbConnection();
            resultSet = statement.executeQuery("SELECT * FROM Student");
            while (resultSet.next()) {
                student.setAge(Integer.valueOf(resultSet.getString("Age")));
                student.setMarks(Integer.valueOf(resultSet.getString("Marks")));
                student.setRollNumber(Integer.valueOf(resultSet.getString("RollNumber")));
                student.setName(String.valueOf(resultSet.getString("Name")));
                student.setSex(String.valueOf(resultSet.getString("Name")));
                studentList.add(student);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        finally {
            closeDbConnection();
        }
        return studentList;
    }

}
