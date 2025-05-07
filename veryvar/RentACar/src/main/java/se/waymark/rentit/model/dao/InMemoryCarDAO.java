
package se.waymark.rentit.model.dao;

import se.waymark.rentit.model.entity.Car;

import java.util.LinkedList;
import java.util.List;

public class InMemoryCarDAO implements CarDAO {
    private List<Car> cars;

    public InMemoryCarDAO() {
        cars = new LinkedList<Car>();
    }

    public void add(Car car) {
        cars.add(car);
    }

    public Car findAvailableCar() {
        for (Car car : cars) {
            if (!car.isRented()) {
                return car;
            }
        }
        throw new RuntimeException("No car available");
    }

    public Car findRentedCar() {
        for (Car car : cars) {
            if (car.isRented()) {
                return car;
            }
        }
        throw new RuntimeException("No car rented");
    }

    public int getNumberOfAvailableCars() {
        int availableCars = 0;
        for (Car car : cars) {
            if (!car.isRented()) {
                availableCars++;
            }
        }
        return availableCars;
    }
}