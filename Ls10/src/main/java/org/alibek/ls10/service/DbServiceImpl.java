package org.alibek.ls10.service;

import org.alibek.ls10.dao.DbManager;
import org.alibek.ls10.entity.Car;

import java.util.List;

public class DbServiceImpl implements DbService {

    private DbManager dbManager;

    public DbServiceImpl() {
        this.dbManager = new DbManager();
        dbManager.connect(); //нужен для инициализации  connection
        //в дальнейшем все методы с connection он не будет уже использовать проинициализированный connection
        //выравнивание делается на ctrl+alt+L
    }

    @Override
    public List<Car> getAllCars() {


        return dbManager.getCars();
    }

    @Override
    public void addCar(final Car car) {
        dbManager.addCar(car);
    }
}
