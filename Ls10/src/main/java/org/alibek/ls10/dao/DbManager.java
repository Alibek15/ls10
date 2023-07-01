package org.alibek.ls10.dao;

import org.alibek.ls10.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private static final String GET_ALL_CARS = "SELECT * FROM mysql.Cars";//создаются query
    private static final String ADD_CAR = "INSERT INTO mysql.Cars values(NULL, ?, ?, ?)";
    private  final String url = "jdbc:mysql://localhost:3306";

    private  final String user = "root";

    private  final String password = "root";

    private Connection  connection;

    public void connect()  { //таким образом он будет exception кидать в импл (throws ClassNotFoundException)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
    //try catch сразу отлавливает на месте
    //из за того что нет обратки в импл его будет вызывать сервлет
     public List<Car> getCars(){
        List<Car> cars  = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement(GET_ALL_CARS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                Double price = resultSet.getDouble(3);
                Double engineVolume = resultSet.getDouble("engine_volume");
                cars.add(new Car(id, name, engineVolume, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
    public void addCar(final Car car){
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_CAR);
            statement.setString(1, car.getName());
            statement.setDouble(2, car.getPrice());
            statement.setDouble(3, car.getEngineVolume());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
