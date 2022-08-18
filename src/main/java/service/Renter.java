package service;

public interface Renter {

    void rent(Long scootersId, Long ordersId, Long userId, Long rentalPointsId);

    void finishRent(Long scootersId, Long ordersId);
}
