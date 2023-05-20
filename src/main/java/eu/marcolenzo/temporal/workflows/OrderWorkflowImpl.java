package eu.marcolenzo.temporal.workflows;

import eu.marcolenzo.temporal.activities.InventoryActivities;
import eu.marcolenzo.temporal.activities.LogisticActivities;
import eu.marcolenzo.temporal.activities.NotificationActivities;
import eu.marcolenzo.temporal.model.Order;
import eu.marcolenzo.temporal.model.OrderItem;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class OrderWorkflowImpl implements OrderWorkflow {

    private static final Logger log = LoggerFactory.getLogger(OrderWorkflowImpl.class);

    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.of(30L, ChronoUnit.SECONDS))
            .build();

    private final InventoryActivities inventoryActivities = Workflow.newActivityStub(
            InventoryActivities.class, defaultActivityOptions);

    private final LogisticActivities logisticActivities = Workflow.newActivityStub(
            LogisticActivities.class, defaultActivityOptions);

    private final NotificationActivities notificationActivities = Workflow.newActivityStub(
            NotificationActivities.class, defaultActivityOptions);

    private boolean delivered = false;

    @Override
    public void processOrder(List<OrderItem> orderItems, String email) {
        log.info("Processing order for user {}", email);

        // Generate Order ID
        String orderId = Workflow.sideEffect(String.class, () -> UUID.randomUUID().toString());
        Order order = new Order(orderId, orderItems);

        // Reserve Product from Inventory
        inventoryActivities.reserveProducts(order);

        // Book Logistics and Send Notification with Tracking ID
        String trackingId = logisticActivities.bookShipping(order);
        notificationActivities.sendOrderAcceptedEmail(order, email, trackingId);

        // Wait for the package to be delivered
        Workflow.await(() -> delivered);

        // Wait 1 day after delivery and request Product Review
        Workflow.sleep(Duration.of(1, ChronoUnit.DAYS));
        notificationActivities.sendReviewRequestEmail(order, email);

    }

    @Override
    public void setDelivered() {
        this.delivered = true;
    }


}
