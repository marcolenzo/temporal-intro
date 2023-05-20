package eu.marcolenzo.temporal.workflows;

import eu.marcolenzo.temporal.model.OrderItem;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.List;

@WorkflowInterface
public interface OrderWorkflow {

    @WorkflowMethod
    void processOrder(List<OrderItem> orderItems, String email);

    @SignalMethod
    void setDelivered();

}
