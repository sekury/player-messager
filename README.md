# Communicating players application

## Requirements

Java 21

## Launch single process application

Initiator and Replier communicate over `BlockingQueue`.

Run command
```
mvn compile
mvn exec:java -Dexec.mainClass="fx360t.Main" -Dexec.args="threads hi"
```
Arguments: 
- single process application type (e.g. threads)
- initial message (e.g. hi)

Example:

```
PID: 3320
May 21, 2024 1:07:57 AM fx360t.task.PlayerTask receiveMessage
INFO: Replier: hi1
May 21, 2024 1:07:57 AM fx360t.task.PlayerTask receiveMessage
INFO: Initiator: hi11
...
May 21, 2024 1:07:57 AM fx360t.task.PlayerTask receiveMessage
INFO: Replier: hi11223344556677889910
May 21, 2024 1:07:57 AM fx360t.task.PlayerTask receiveMessage
INFO: Initiator: hi1122334455667788991010
May 21, 2024 1:08:00 AM fx360t.task.PlayerTask receiveMessage
INFO: Replier: null
Result: hi1122334455667788991010
```

## Launch double process application

Initiator and Replier communicate over socket connection.  
Initiator starts sending messages when replier connects.  
Initiator and Replier must be started separately.

### Launch Initiator process

Run command
```
mvn exec:java -Dexec.mainClass="fx360t.Main" -Dexec.args="soinit hi 8000"
```
Arguments:
- socket initiator application type (e.g. soinit)
- initial message (e.g. hi)
- initiator port number (e.g. 8000)

### Launch Replier process

Run command
```
mvn exec:java -Dexec.mainClass="fx360t.Main" -Dexec.args="soreply 8000"
```
Arguments:
- socket replier application type (e.g. soreply)
- initiator port number (e.g. 8000)
