package valid;

import entity.*;
import exceptions.NotAvailableException;
import repository.JPARepository;

public class Valid {
    public static void isRentAvailable(ScooterStatus status, UserStatus userStatus, RentalPointStatus rentalPointStatus) {
        if (!ScooterStatus.AVAILABLE.equals(status)
                || !UserStatus.FRIENDLY.equals(userStatus)
                || !RentalPointStatus.OPEN.equals(rentalPointStatus)) {
            throw new NotAvailableException("Вы не можете арендовать самокат");
        }
    }

    public static void isOrderPresent(Long ordersId, JPARepository JPARepository) {
        if (JPARepository.findById(ordersId).isPresent()) {
            throw new NotAvailableException("Заказ с таким id уже существует");
        }
    }
}
