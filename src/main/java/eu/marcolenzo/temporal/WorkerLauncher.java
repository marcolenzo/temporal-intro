package eu.marcolenzo.temporal;

import eu.marcolenzo.temporal.activities.InventoryActivitiesImpl;
import eu.marcolenzo.temporal.activities.LogisticActivitiesImpl;
import eu.marcolenzo.temporal.activities.NotificationActivitiesImpl;
import eu.marcolenzo.temporal.workflows.OrderWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class WorkerLauncher {

    public static final String TASK_QUEUE = "orders";

    public static void main(String[] args) {
        WorkflowServiceStubs serviceStub = WorkflowServiceStubs.newInstance();
        WorkerFactory workerFactory = WorkerFactory.newInstance(WorkflowClient.newInstance(serviceStub));
        Worker worker = workerFactory.newWorker(TASK_QUEUE);

        // Supported workflows
        worker.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);

        // Supported activities
        worker.registerActivitiesImplementations(new InventoryActivitiesImpl(),
                new LogisticActivitiesImpl(),
                new NotificationActivitiesImpl());

        workerFactory.start();
    }

}
