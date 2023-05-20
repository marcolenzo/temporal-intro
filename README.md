# Introduction to Temporal

This repository contains the samples supporting the YouTube video https://youtu.be/LulonIoYOP8.

## Installing Temporal CLI
The easiest way to try out Temporal is to use the CLI which is a lightweight distribution with zero runtime dependencies. This is perfect to start experimenting.

You can use the following command on Linux:

```
curl -sSf https://temporal.download/cli.sh | sh
```

Other installation methods are described in the [official repository](https://github.com/temporalio/cli).

## Start the Temporal Server and the Web UI

Run the following command to start the Temporal Server and Web UI:

```
temporal server start-dev
```

The Temporal server will start in ephemeral mode, i.e. all data is stored in memory thus it is lost on restart. You can access the Web UI at http://localhost:8233.

If you want to make data persistent across restarts, use the following command line:

```
temporal server start-dev --db-filename temporal.db
```

## Creating a workflow request
The code showing how a workflow is invoked is found in the [OrderWorkflowLauncher](src/main/java/eu/marcolenzo/temporal/OrderWorkflowLauncher.java) class. You can launch this class and if you access the Web UI, you should see the workflow scheduled.

## Launching the Worker process
Workflows and Activities are executed by Workers. The code showing how we declare a worker and connect it to temporal is in the [WorkerLauncher](src/main/java/eu/marcolenzo/temporal/WorkerLauncher.java) class.

## Workflows and Activities
Workflows and Activities are declared as interfaces, which are then implemented by workers.

In this example, we put all the code in one component. However, you could implement activities in other microservices. You can also nest workflows if you wish.

## Upcoming
I will try to put more effort on this repository if you find it useful. Just leave me a comment on the video https://youtu.be/LulonIoYOP8
