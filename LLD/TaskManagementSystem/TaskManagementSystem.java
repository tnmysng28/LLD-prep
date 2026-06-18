Functional Requirements: 
- System should schedule one time taks to run at a specific time
- System should schedule recurring tasks to run at a fixed interval
- Add/Remove worker nodes fro the pool
- System should allow cancelling a scheduled task before it executes
- Life cycle Management of the task



Entities: 

TaskType: enum

TaskState: enum
PENDIND, RUNNING, COMPLETED, FAILED



Task: interface
- id
- name 
- execute()

ScheduledTask: class

Scheduler: class


Scheduling Strategy: 



