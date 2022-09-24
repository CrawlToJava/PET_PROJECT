package valid;


import entity.RentalPointStatus;
import entity.ScooterStatus;
import entity.UserStatus;
import exceptions.NotAvailableException;
import repository.OrderRepository;

public class Valid {
    public static void isRentAvailable(ScooterStatus status, UserStatus userStatus, RentalPointStatus rentalPointStatus) {
        if (!ScooterStatus.AVAILABLE.equals(status)
                || !UserStatus.FRIENDLY.equals(userStatus)
                || !RentalPointStatus.OPEN.equals(rentalPointStatus)) {
            throw new NotAvailableException("Вы не можете арендовать самокат");
        }
    }

    public static void isOrderPresent(Long ordersId, OrderRepository orderRepository) {
        if (orderRepository.findById(ordersId).isPresent()) {
            throw new NotAvailableException("Заказ с таким id уже существует");
        }
    }
}
