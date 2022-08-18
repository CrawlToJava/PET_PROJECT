package service;

import entity.Orders;
import entity.RentalPoints;
import entity.Scooters;
import entity.User;

public interface Renter {
    void rent(Scooters scooters, Orders orders, User user, RentalPoints rentalPoints);

    void close(Scooters scooters, Orders orders);
}
