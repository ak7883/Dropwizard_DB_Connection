package com.ajay.dropwizard.service;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class MainApplication extends Application<Configuration>{

    public static void main(String[] args) throws Exception{
        new MainApplication().run(args);
    }

    @Override
    public void run(Configuration c,Environment e){
        e.jersey().register(new DropwizardService());
    }
}
