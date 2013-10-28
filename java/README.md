There's the main Scheduler class that all of the possible options are going to extend. getProcess() is called immeadiately before each tick. It is called immeadiately after process cleanup is done from the last tick. hasProcess() is true if the last process just completed.

Right now FCFS and SJF are basically working. RR is in the works and should be pretty easy. What needs to be done next is to expand the current Scheduler class to hold on to Process instances after they terminate. Next, make sure that the Process class is correctly keeping track of the times we have to calculate. Then, add something to Scheduler to output this and return after all processes are ran.

Things are structured in a way that part B should only be a quick addition.

Otherwise the rest of the work lies in implementing all of the different strategies.

Oh, also, support for more CPUs is required, thats a matter of seperating a Processor from a Scheduler. I'll take care of that soon.

--------

Touched up some things. We need to implement TCS, time for context switches, which is just a flat 15ms whenever a process is changed to another process, but not before the first process or after the last. 

It doesn't matter for part I, but I preemptive and non-preemptive SJF two separate instances of the object because that is what we will need for part II (separate stats and such.) I also added more stats to the Scheduler class that will have to be worked in eventually and outputted after all processes run in a comparison.

I lastly imported his ExponentialRandom class so we can call for the random numbers as they ask for part II, again not necessary now but will be later.

--------

Round Robin - If a process ends with time left in its burst, nothing happens.
			- It should switch to the next process
				- This means the times will not end in even 00s
				- Reset workingTime variable in RR when a process ends
				- Check context switching against workingTime variable instead of overall time