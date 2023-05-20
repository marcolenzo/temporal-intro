package eu.marcolenzo.temporal.activities;

import eu.marcolenzo.temporal.model.Order;

public class NotificationActivitiesImpl implements NotificationActivities {
    @Override
    public boolean sendOrderAcceptedEmail(Order order, String email, String trackingId) {
        return true;
    }

    @Override
    public boolean sendReviewRequestEmail(Order order, String email) {
        return true;
    }
}
