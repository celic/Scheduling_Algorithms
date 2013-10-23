There's the main Scheduler class that all of the possible options are going to extend. getProcess() is called immeadiately before each tick. It is called immeadiately after process cleanup is done from the last tick. hasProcess() is true if the last process just completed.

Right now FCFS and SJF are basically working. RR is in the works and should be pretty easy. What needs to be done next is to expand the current Scheduler class to hold on to Process instances after they terminate. Next, make sure that the Process class is correctly keeping track of the times we have to calculate. Then, add something to Scheduler to output this and return after all processes are ran.

Things are structured in a way that part B should only be a quick addition.

Otherwise the rest of the work lies in implementing all of the different strategies.

Oh, also, support for more CPUs is required, thats a matter of seperating a Processor from a Scheduler. I'll take care of that soon.