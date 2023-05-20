package eu.marcolenzo.temporal.activities;

import eu.marcolenzo.temporal.model.Order;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface LogisticActivities {

    @ActivityMethod
    String bookShipping(Order order);
    
}
