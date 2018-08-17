package com.ajay.dropwizard.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

}
