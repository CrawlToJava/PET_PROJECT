package valid;

import entity.*;
import entity.Scooter;
import exceptions.EntityExistException;
import exceptions.NotAvailableException;

import java.util.List;

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

    public static void isUserPresent(List<User> userList, Long id) {
        if (userList.stream().noneMatch(u -> u.getId().equals(id))) {
            throw new EntityExistException("Проверьте корректность введенного id");
        }
    }

    public static void isScooterPresent(List<Scooter> scooterList, Long id) {
        if (scooterList.stream().noneMatch(scooter -> scooter.getId().equals(id))) {
            throw new EntityExistException("Проверьте корректность введенного id");
        }
    }

    public static void isRentalPointPresent(List<RentalPoint> rentalPoints, Long id) {
        if (rentalPoints.stream().noneMatch(rentalPoint -> rentalPoint.getId().equals(id))) {
            throw new EntityExistException("Проверьте корректность введенного id");
        }
    }

    public static void isOrderPresent(List<Order> orderList, Long id) {
        if (orderList.stream().noneMatch(order -> order.getId().equals(id))) {
            throw new EntityExistException("Проверьте корректность введенного id");
        }
    }

}
