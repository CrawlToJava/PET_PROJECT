package valid;

import entity.*;
import entity.Scooters;
import exceptions.NotAvailableException;

public class Valid {
    public static boolean isRentAvailable(Scooters scooter, Users users, RentalPoints rentalPoint) {
        if (scooter.getScootersStatus().equals(ScootersStatus.AVAILABLE)
                && users.getUsersStatus().equals(UsersStatus.FRIENDLY)
                && rentalPoint.getRentalPointsStatus().equals(RentalPointsStatus.OPEN)) {
            return true;
        } else {
            throw new NotAvailableException("Вы не можете арендовать самокат");
        }
    }

    public static boolean isCloseAvailable(Scooters scooters) {
        if (scooters.getScootersStatus().equals(ScootersStatus.BOOKED)) {
            return true;
        } else {
            throw new NotAvailableException("Вы не можете закрыть прокат");
        }
    }
}
