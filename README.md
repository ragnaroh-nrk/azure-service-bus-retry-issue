# Spring Messaging Azure Service Bus retry issue demo

This project demonstrates an issue in the `spring-cloud-azure-starter-servicebus` library.

## The issue

When a message is received from an Azure Service Bus queue, and the message processing fails, the message is retried immediately instead of waiting for the configured delay.

## How to run

```shell
./gradlew bootRun
```
