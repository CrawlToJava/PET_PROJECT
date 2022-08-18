package service.impl;

import service.PriceCounter;

import java.time.LocalDateTime;
import java.time.ZoneId;


public class OrderService implements PriceCounter {

    @Override
    public Double counter(LocalDateTime orderedAt, LocalDateTime finishedAt, Double price) {
        Long start = orderedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long end = finishedAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return price * (end - start);
    }

}
