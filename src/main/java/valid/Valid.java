package valid;

import entity.*;
import entity.Scooter;
import exceptions.NotAvailableException;

public class Valid {
    public static void isRentAvailable(Scooter scooter, User user, RentalPoint rentalPoint) {
        if (!scooter.getScooterStatus().equals(ScooterStatus.AVAILABLE)
                || !user.getUserStatus().equals(UserStatus.FRIENDLY)
                || !rentalPoint.getRentalPointsStatus().equals(RentalPointStatus.OPEN)) {
                    throw new NotAvailableException("Вы не можете арендовать самокат");
                }
    }

    public static void isFinishRentAvailable(Scooter scooter) {
        if (!scooter.getScooterStatus().equals(ScooterStatus.BOOKED)) {
            throw new NotAvailableException("Вы не можете закрыть прокат");
        }
    }
}
