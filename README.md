# parallel

I attempted to run a Sieve of Eratosthenes in rust. This works by taking a list of all numbers in the range and assuming they are true. Then, for every
number up to the square root of our target(10^8), we find all numbers that are factored by the primes we find. This results in a list where all true values left
are in fact prime, and all falses are composite.

To explain a couple weird values:
***let s: u64 = 4999999949999999;
***let np: u64 = 99999998;

s is the sum of all numbers from 2-10^8
np is the total amount of numbers

this allows us, in theory, to parallelize the discovery of sum and number of primes or at minimum, speed up those calculations rather than iterating over
a very large array. This works because we already have the target number in our prompt.

However, in this implementation, thread 1 hogs the resources. I tried many other implementations
and did end up getting multiple threads to run, however, those had run times of 30+ seconds, which was significantly slower than this implementation. 

I also attempted to run a deterministic miller-rabin prime checker with multiple threads. This implementation took about 50 seconds to run.

I also attempted to run similar implementations to ones the professor gave as examples. These ran at 120+ seconds.

I also started from scratch and attempted to perform deterministic miller-rabin in Java. That one ran about 4 minutes before completing.

Basically, please be gentle.



***to run this code, enter into your terminal:***

--->    rustc primes.rs

--->    ./primes.exe
