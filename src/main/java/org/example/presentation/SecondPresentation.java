package org.example.presentation;

import org.example.dao.Dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SecondPresentation {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // dependency injection throughout a dynamic => config file
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();
        Class daoClass = Class.forName(daoClassName);
        Dao dao = (Dao) daoClass.newInstance();
        System.out.println(dao.getData());

    }
}
