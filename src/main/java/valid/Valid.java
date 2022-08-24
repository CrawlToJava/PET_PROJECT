package valid;

import entity.*;
import entity.Scooter;
import exceptions.NoDataFoundException;
import exceptions.NotAvailableException;

import java.util.List;

public class Valid {
    public static void isRentAvailable(ScooterStatus status, UserStatus userStatus, RentalPointStatus rentalPointStatus) {
        if (!ScooterStatus.AVAILABLE.equals(status)
                || !UserStatus.FRIENDLY.equals(userStatus)
                || !RentalPointStatus.OPEN.equals(rentalPointStatus)) {
                    throw new NotAvailableException("Вы не можете арендовать самокат");
                }
    }

    public static void isFinishRentAvailable(Scooter scooter) {
        if (!scooter.getScooterStatus().equals(ScooterStatus.BOOKED)) {
            throw new NotAvailableException("Вы не можете закрыть прокат");
        }
    }

    public static void isUserPresent(List<User> userList, Long id) {
        if (userList
                .stream()
                .anyMatch(u -> u.getId().equals(id))) {
            throw new NoDataFoundException("Проверьте корректность введенного id");
        }
    }

    public static void isScooterPresent(List<Scooter> scooterList, Long id) {
        if (scooterList
                .stream()
                .anyMatch(scooter -> scooter.getId().equals(id))) {
            throw new NoDataFoundException("Проверьте корректность введенного id");
        }
    }

    public static void isRentalPointPresent(List<RentalPoint> rentalPoints, Long id) {
        if (rentalPoints
                .stream()
                .anyMatch(rentalPoint -> rentalPoint.getId().equals(id))) {
            throw new NoDataFoundException("Проверьте корректность введенного id");
        }
    }

    public static void isOrderPresent(List<Order> orderList, Long id) {
        if (orderList
                .stream()
                .noneMatch(order -> order.getId().equals(id))) {
            throw new NoDataFoundException("Проверьте корректность введенного id");
        }
    }

}
