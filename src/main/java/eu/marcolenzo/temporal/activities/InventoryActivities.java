package eu.marcolenzo.temporal.activities;

import eu.marcolenzo.temporal.model.Order;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface InventoryActivities {

    @ActivityMethod
    boolean reserveProducts(Order order);

    @ActivityMethod
    boolean releaseProducts(Order order);

}
