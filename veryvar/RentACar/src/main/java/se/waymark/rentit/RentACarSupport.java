package se.waymark.rentit;

import se.waymark.rentit.model.dao.CarDAO;
import se.waymark.rentit.model.dao.InMemoryCarDAO;
import se.waymark.rentit.model.entity.Car;

public class RentACarSupport {
    private CarDAO carDatabase;

    public void createCars(int availableCars) {
        carDatabase = new InMemoryCarDAO();
        for (int i = 0; i < availableCars; i++) {
            Car car = new Car();
            carDatabase.add(car);
        }
    }

    public void rentACar() {
        Car car = carDatabase.findAvailableCar();
        car.rent();
    }

    public void returnACar() {
        Car car = carDatabase.findRentedCar();
        car.returnCar();
    }

    public int getAvailableNumberOfCars() {
        return carDatabase.getNumberOfAvailableCars();
    }
}