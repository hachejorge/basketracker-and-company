package se.waymark.rentit.model.dao;

import se.waymark.rentit.model.entity.Car;

public interface CarDAO {

    public void add(Car car);

    Car findAvailableCar();

    Car findRentedCar();

    int getNumberOfAvailableCars();
}
