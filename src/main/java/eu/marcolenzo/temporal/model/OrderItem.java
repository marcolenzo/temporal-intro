package eu.marcolenzo.temporal.model;

import java.math.BigDecimal;

public record OrderItem(String productId, Integer quantity, Integer unitPriceInCents) {
}
