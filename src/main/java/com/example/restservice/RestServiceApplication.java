package com.example.restservice;

import com.example.restservice.DAO.DAOAccount;
import com.example.restservice.DAO.DAOProduct;
import com.example.restservice.entity.Account;
import com.example.restservice.entity.Product;
import com.example.restservice.entity.Roles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.Scanner;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) throws SQLException {

        Statement statement = MyConnection.INSTANCE.getConnection().createStatement();

        DAOAccount daoAccount = new DAOAccount();
        DAOProduct daoProduct = new DAOProduct();
//        Account account1 = new Account(1,"newLogin","newPass", Roles.Driver);
//        daoAccount.insertAccount(account1);

//        statement = connection.createStatement();
//        statement.execute(
//                "insert into Account(login, pass, role_id)" +
//                        " values (\"${account.login}\", \"${account.password}\", 2);");
////
//        Account account2 = daoAccount.getAccount(1);
//        System.out.println(account2.toString());

//        Product product = new Product(1,"Article","title", 1, );

        Scanner scanner = new Scanner(System.in);
        while (true){
            switch (scanner.nextInt()){
                case 1:
//                    daoProduct.insertProduct();
                    break;

            }
        }

//
//        Account account2 = new DAOAccount().getAccount(2);
//        System.out.println(account2.toString());

//        SpringApplication.run(RestServiceApplication.class, args);

    }
}
