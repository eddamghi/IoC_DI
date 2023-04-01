package org.example.dao;

import static java.lang.Math.sqrt;

public class DaoImpl implements Dao{

    @Override
    public double getData() {
        System.out.println(" ");
        return Math.random() * 10 + 1 * sqrt(2);
    }
}
