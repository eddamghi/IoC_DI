package org.example.business;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dao.Dao;

@NoArgsConstructor
@Data
public class BusinessImpl implements Business{
    @Setter private Dao dao;

    @Override
    public double compute() {
        double data = dao.getData();
        return data * 23;
    }
}