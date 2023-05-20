package eu.marcolenzo.temporal;

import eu.marcolenzo.temporal.model.OrderItem;
import eu.marcolenzo.temporal.workflows.OrderWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public class OrderWorkflowLauncher {

    public static void main(String[] args) {
        WorkflowServiceStubs serviceStub = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(serviceStub);

        OrderWorkflow workflow = client.newWorkflowStub(OrderWorkflow.class,
                WorkflowOptions.newBuilder().setTaskQueue("orders").build());
        WorkflowClient.start(() -> workflow.processOrder(createRandomOrderItems(), "marco@marcolenzo.eu"));

    }

    private static List<OrderItem> createRandomOrderItems() {
        OrderItem orderItem1 = new OrderItem(RandomStringUtils.randomAlphanumeric(6),
                RandomUtils.nextInt(), RandomUtils.nextInt());

        OrderItem orderItem2 = new OrderItem(RandomStringUtils.randomAlphanumeric(6),
                RandomUtils.nextInt(), RandomUtils.nextInt());

        return List.of(orderItem1, orderItem2);
    }
}
