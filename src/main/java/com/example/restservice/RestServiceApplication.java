package com.example.restservice;

import com.example.restservice.entity.Account;
import com.example.restservice.entity.LogisticsCenter;
import com.example.restservice.entity.Roles;
import com.example.restservice.service.AccountService;
import com.example.restservice.service.CenterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.Scanner;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) throws SQLException {


        Tests test = new Tests();
        test.doTest();

        //SpringApplication.run(RestServiceApplication::class.java, args)


    }
}
