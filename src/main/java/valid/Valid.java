package valid;

import entity.*;
import entity.Scooters;
import exceptions.NotAvailableException;

public class Valid {
    public static void isRentAvailable(Scooters scooter, Users users, RentalPoints rentalPoint) {
        if (!scooter.getScootersStatus().equals(ScootersStatus.AVAILABLE)
                || !users.getUsersStatus().equals(UsersStatus.FRIENDLY)
                || !rentalPoint.getRentalPointsStatus().equals(RentalPointsStatus.OPEN)) {
                    throw new NotAvailableException("Вы не можете арендовать самокат");
                }
    }

    public static void isFinishRentAvailable(Scooters scooters) {
        if (!scooters.getScootersStatus().equals(ScootersStatus.BOOKED)) {
            throw new NotAvailableException("Вы не можете закрыть прокат");
        }
    }
}
