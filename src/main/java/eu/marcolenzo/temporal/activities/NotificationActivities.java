package eu.marcolenzo.temporal.activities;

import eu.marcolenzo.temporal.model.Order;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.util.List;

@ActivityInterface
public interface NotificationActivities {

    @ActivityMethod
    boolean sendOrderAcceptedEmail(Order order, String email, String trackingId);

    @ActivityMethod
    boolean sendReviewRequestEmail(Order order, String email);
    
}
