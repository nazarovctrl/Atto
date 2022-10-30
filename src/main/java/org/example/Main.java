package org.example;

import org.example.config.Config;
import org.example.controller.AuthController;
import org.example.db.DataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        DataBase dataBase = (DataBase) applicationContext.getBean("dataBase");
        dataBase.createTable();

        AuthController authController = (AuthController) applicationContext.getBean("authController");


        authController.start();

    }
}