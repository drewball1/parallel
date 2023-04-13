The program I wrote is very efficient, in that no sensor ever touches data that another sensor touches. They put their data into a 2d array, with one dimension
devotes to each sensor. After the hour is done, I use two priority queues to find the maximum and minimum 5 temperatures, and within the same loop overall loop
I also discover the 10 second interval with the greatest difference between high temp and low temp and also keep track of the specific minutes where it occurs.
There are no errors in output, and there are no deadlocks, and all data entry is, by nature of the 2d array structure, mutually exclusive.

To run, enter the problem 2 folder and type into the command line: 

"javac TemperatureAnalyzer.java"

then type: 

"java TemperatureAnalyzer"
