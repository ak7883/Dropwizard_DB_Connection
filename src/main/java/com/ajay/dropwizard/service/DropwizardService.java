package com.ajay.dropwizard.service;

import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DropwizardService {

    DropwizardDAO dropwizardDAO = new DropwizardDAO();

    @GET
    @Path("/all")
    public List<Student> getAllStudent() {
        return dropwizardDAO.getAllStudentList();
    }

    //Getting the student details using @PathParam annotation

    @GET
    @Path("/{rollNumber}")
    public Student getStudentByRollNumber(@PathParam(value = "rollNumber") int rollNumber){
        System.out.println("Inside getstudentBYRollnumber function with roll number" + rollNumber);
        return dropwizardDAO.getStudentByRollNumber(rollNumber);
    }

    //Getting the student details using #QueryParam annotation

    @GET
    public Student getStudentByRollnumber(@QueryParam(value = "rollNumber") int rollNumber){
        System.out.println("Inside getStudentByRollnumber using queryParam annotation with rollnumber" + rollNumber);
        return dropwizardDAO.getStudentByRollNumber(rollNumber);
    }

    @GET
    @Path("/name")
    public Student getStudentByName(@QueryParam(value = "name") String name){
        return dropwizardDAO.getStudentByName(name);
    }
}
