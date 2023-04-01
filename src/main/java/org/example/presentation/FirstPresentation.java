package org.example.presentation;

import org.example.business.BusinessImpl;
import org.example.dao.DaoImpl;

public class FirstPresentation {
    public static void main( String[] args){
        // dependency injection throughout a static => new
        DaoImpl dao = new DaoImpl();
        BusinessImpl business = new BusinessImpl();
        business.setDao(dao);
        System.out.println(business.compute());
    }
}