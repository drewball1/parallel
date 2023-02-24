
For problem 1, I assigned the thread with an id of "1" to be the checker. Every thread enters the maze when directed, and when they reach the end, 
if they have not eaten and there is a cupcake, they eat the cupcake. Otherwise, they do nothing, unless they have the id "1", 
in which case they add one to their counter and replace the cupcake.
Once they have seen 100 empty plates, then we know that everyone has been in the maze at least once. I performed this in Java with a threadpool of size 100, 
utilizing Reentrant Locks, and Conditions. 
There is a shared cupcake object which contains the flag for the cupcakes and the flag for everyone having eaten.

For problem 2, I believe that the first option is likely the fastest. There are no real critical sections, 
and as long as the number of guests is reasonable, it should be fast even with repeated entries.
Any delays from repeated entries is still likely less than the delays from communication between threads. 
My code does have a counter and a flag for room occupation so it is not clear whether it's one or two, but
I chose to use it since otherwise everyone could be in the room at once, which would make no sense. 
I assumed that option 2 related more to a traditional Lock class. There is no coordination between threads directly,
they only check whether the room is full and thus unenterable, (there is no signal method or anything). 
It operates very quickly, and the "huddling" issue only really appears towards the end when nearly everyone has already been in.
However, this still is not a significant time waste.


How to Run:

*Set working directory to Minotaur folder*

Type:

javac Birthday.java

javac Vase.java

java Birthday

java Vase
