Problem 1 Discussion:

The problem they be running into is that they may be naively using compareAndSet, which leads to conflicts where if two threads attempt to remove adjacent nodes,
they will end up only removing one of the nodes. Instead, if you would like your threads to accurately perform these functions, they should mark the node and the next
node it is connected to before removal, and to skip the traversal of marked nodes.

My Solution:
I have implemented a Non-Blocking Synchronization Linked List. Each thread prints out it's id and current task until both the bag of presents and chain of presents is empty.
Then, the threads will print out how many presents and how many thank you letters they have written. I have verified that these both total to 500,000, indicating
that my application accurately adds and removes from the linked list.



Instructions for running the application:

  Navigate to the "Problem1" folder and type: "javac BirthdayPresents.java"

  Then type: "java BirthdayPresents"
