package com.ajay.dropwizard.service;

import jdk.nashorn.internal.ir.CatchNode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DropwizardDAO {

   private Connection connection = null;
   private PreparedStatement statement = null;
   private ResultSet resultSet = null;
   private List<Student> studentList = new ArrayList<>();
   Student student = new Student();
   int check = 1;

    private void createDbConnection(){
        student = new Student();
        try {
            // Step 1: Loading or registering Oracle JDBC driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String msAccDB = "C:/Users/ajay/Documents/Database2.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;

            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL);

            // Step 2.B: Creating JDBC Statement
//            statement = connection.createStatement();
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
            check++;
            createDbConnection();
            statement = connection.prepareStatement("SELECT * FROM Student");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setAge(Integer.valueOf(resultSet.getString("Age")));
                student.setMarks(Integer.valueOf(resultSet.getString("Marks")));
                student.setRollNumber(Integer.valueOf(resultSet.getString("RollNumber")));
                student.setName(String.valueOf(resultSet.getString("Name")));
                student.setSex(String.valueOf(resultSet.getString("Sex")));
                studentList.add(student);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        finally {
            closeDbConnection();
            System.out.println(check);
        }
        return studentList;
    }

    public Student getStudentByRollNumber(int rollNumber){
        try{
            check++;
            createDbConnection();
            statement = connection.prepareStatement("SELECT * FROM Student where RollNumber = ?");
            statement.setString(1,String.valueOf(rollNumber));
            resultSet = statement.executeQuery();
            setValuesToStudent(resultSet);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            closeDbConnection();
            System.out.println(check);
        }
        return student;
    }

    private void setValuesToStudent(ResultSet resultSet){
        try {
            while (resultSet.next()) {
                student.setSex(resultSet.getString("Sex"));
                student.setAge(Integer.valueOf(resultSet.getString("Age")));
                student.setMarks(Integer.valueOf(resultSet.getString("Marks")));
                student.setRollNumber(Integer.valueOf(resultSet.getString("RollNumber")));
                student.setName(resultSet.getString("Name"));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public Student getStudentByName(String name){
        try{
            createDbConnection();
            statement = connection.prepareStatement("SELECT * FROM Student where Name = ?");
            statement.setString(1,name);
            resultSet = statement.executeQuery();
            setValuesToStudent(resultSet);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            closeDbConnection();
        }
        return student;
    }

}
