package org.alibek.ls10.service;

import org.alibek.ls10.entity.Car;

import java.util.List;

public interface DbService {
    List<Car> getAllCars();


    void addCar(Car car);
}
