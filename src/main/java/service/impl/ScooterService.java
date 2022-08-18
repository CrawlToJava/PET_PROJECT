package service.impl;

import entity.*;
import exceptions.CantCloseException;
import exceptions.NotAvailableException;
import lombok.SneakyThrows;
import service.Renter;

import java.time.LocalDateTime;

public class ScooterService implements Renter {

    private boolean isScooterAvailable(ScootersStatus status) {
        return status.equals(ScootersStatus.AVAILABLE);
    }

    private boolean isUserFriendly(UserStatus status) {
        return status.equals(UserStatus.FRIENDLY);
    }

    private boolean isRentalPointOpen(RentalPointsStatus status) {
        return status.equals(RentalPointsStatus.OPEN);
    }

    private boolean isBooked(ScootersStatus status) {
        return status.equals(ScootersStatus.BOOKED);
    }

    private LocalDateTime timeNow() {
        return LocalDateTime.now();
    }

    @SneakyThrows
    @Override
    public void rent(Scooters scooter, Orders orders, User user, RentalPoints rentalPoint) {
        if (isScooterAvailable(scooter.getScootersStatus()) && isUserFriendly(user.getUserStatus()) && isRentalPointOpen(rentalPoint.getRentalPointsStatus())) {
            scooter.setScootersStatus(ScootersStatus.BOOKED);
            orders.setOrdersStatus(OrdersStatus.OPEN);
            orders.setOrderedAt(timeNow());
        } else {
            throw new NotAvailableException("Вы не можете арендовать самокат");
        }
    }

    @Override
    public void close(Scooters scooter, Orders orders) {
        if (isBooked(scooter.getScootersStatus())) {
            scooter.setScootersStatus(ScootersStatus.AVAILABLE);
            orders.setOrdersStatus(OrdersStatus.CLOSE);
            orders.setFinishedAt(timeNow());
        } else {
            throw new CantCloseException("Вы не можете закрыть прокат");
        }
    }
}
