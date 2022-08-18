package service;

import java.time.LocalDateTime;

public interface PriceCounter {
    Double counter(LocalDateTime orderedAt, LocalDateTime finishedAt, Double price);
}
