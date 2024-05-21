### Launch two thread

```
mvn exec:java -Dexec.mainClass="fx360t.Main" -Dexec.args="threads hi"
```

Result:

```
PID: 3320
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi1
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi11
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi112
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi1122
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi11223
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi112233
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi1122334
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi11223344
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi112233445
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi1122334455
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi11223344556
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi112233445566
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi1122334455667
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi11223344556677
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi112233445566778
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi1122334455667788
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi11223344556677889
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi112233445566778899
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: hi11223344556677889910
May 21, 2024 1:07:57 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Initiator: hi1122334455667788991010
May 21, 2024 1:08:00 AM fx360t.tasks.PlayerTask receiveMessage
INFO: Replier: null
Result: hi1122334455667788991010
```
