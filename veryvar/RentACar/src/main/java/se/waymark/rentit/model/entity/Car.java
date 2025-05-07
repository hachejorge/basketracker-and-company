package se.waymark.rentit.model.entity;

public class Car {
    private boolean rented;

    public void rent() {
        rented = true;
    }

    public void returnCar() { rented = false; }

    public boolean isRented() {
        return rented;
    }
}