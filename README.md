Operating Systems
Project 1 - Process Management Simulation System
Chris Celi, Damian Mastylo, Austin Gulati
10/30/13

This project is to be completed individually or in teams of up to three students. Beyond your team, do not share code or review anyone else's code. You may discuss the problem and potential solutions, but copying or using someone else's code is strictly forbidden. Feel free to use the LMS Discussion Board to discuss this assignment (and find teammates).

Submit your homework via RPI LMS as a single compressed and zipped file (using tar and gzip); include only source code and documentation files. Be sure to use both tar and gzip; i.e. do not use bzip2 or any other alternatives.

To package up your submission, use tar and gzip. More specifically, create a compressed tar file using one of your team members's RCS userid, as in goldsd.tar.gz, that contains your source files (e.g. main.c and file2.c); include a readme.txt file only if necessary. 

Here's an example:

bash$ tar cvf goldsd.tar main.c file2.c readme.txt
main.c
file2.c
readme.txt

bash$ gzip -9 goldsd.tar

Be sure to comment your code and include your name(s) at the top of each file submitted. Note that you should only submit one file per team; therefore, clearly indicate all names at the top of each file submitted. And double-check that everyone receives a grade!

If necessary, please be sure to include any compilation and execution instructions in your submission (via a README text file).


Part I:

Using C, C++, Java, Python, or ... (contact Dr. G if you'd like to use another language), design and implement a simulation program for process management in an operating system, in particular short-term (CPU) scheduling.

Be sure your implementation is purely a simulation and nothing more! There is no need to use fork() or other process-related system calls. All of the following should be simulated. Further, do not use real-time measurements for this project. Simulate elapsed time using a (global?) variable.

Be sure to have your program run with absolutely no user interaction whatsoever. Use the defaults noted in the project specifications.

Processes:

A process is a program in execution. For this project, processes are ready to use the CPU or are actively using the CPU. Ignore I/O for now (this greatly simplifies the model).

Your system must support m CPUs (start m at 1). Label your CPUs with capital letters starting at A (i.e. A, B, C, etc.).

Build an easily tunable program that simulates the creation and execution of n processes numbered 1-n (consider these the process IDs).

Each process requires a specified amount of CPU time to complete its CPU burst. Specify these times using random numbers in the range 50-400ms. Use a default value of 20 for n (though test with other values, too).

Using these initial conditions, your simulation program must simulate each of the following scheduling algorithms (one at a time and with the same initial conditions, each starting at time 0!):
	First-Come, First-Served (FCFS) with no preemption
	Shortest-Job-First (SJF) with no preemption
	Shortest-Job-First (SJF) with preemption
	Round-Robin (RR) with configurable time slice t initially set to 100 milliseconds
	Preemptive Priority with random priority levels 0-4 assigned to processes at the onset (low numbers indicate high priority); processes with the same priority are processed via the RR algorithm; higher-priority processes entering the system preempt a running process

Each process starts in the ready queue at time 0, though you may assume they are ordered by process ID (also, process ID ordering breaks any "ties" regarding scheduling). The CPU scheduler dispatches a ready process as designated by the given scheduling algorithm, and the process runs. When a context switch occurs, add tcs, the overhead for performing the context switch. Set tcs to 15 milliseconds (in which the first 8 ms are spent removing the "before" process and the second 7 ms are spent selecting and resuming the "after" process).

Display a line of output for each of the following events:
	Process creation (display the process ID, required CPU time, and priority, if applicable)
	Context switch per CPU (display the two before/after process IDs involved, as well as the CPU label); display this at the beginning of the context switch
	Process CPU burst completion (display the process ID, its turnaround time, its initial wait time, and its total wait time)
	For each line of output, display the current elapsed time of the simulation, as shown below. For the context switch output line, display the line of output at the beginning of the context switch.

[time 0ms] Process 1 created (requires 788ms CPU time)
...
[time 4279ms] Context switch (swapping out process 4 for process 17 in CPU A)
...
[time 5288ms] Process 3 completed its CPU burst (turnaround time 5288ms, initial wait time 1752ms, total wait time 3655ms)
...

Skip the very first context switch(es) for the very first process(es). In other words, at time 0, assume there's a process starting its CPU burst in each CPU. Assume that process creation/entry time is negligible.

When a process completes its CPU burst, a context switch occurs to swap out the process and swap in the next ready process (so be sure to add tcs appropriately). If there is no ready process, wait until one becomes available (which can occur in Part II below).

Run your simulation until all processes complete their CPU bursts. Upon completion, display the following data for each scheduling algorithm:
	Minimum, average, and maximum turnaround times
	Minimum, average, and maximum initial wait times
	Minimum, average, and maximum total wait times

Display using the following format:
	Number of CPUs: ___
	Turnaround time: min ___ ms; avg ___ ms; max ___ ms
	Initial wait time: min ___ ms; avg ___ ms; max ___ ms
	Total wait time: min ___ ms; avg ___ ms; max ___ ms

Calculate the above values and show to at least three decimal places for each.

Be sure your program runs with no interactive user input! Do not have the user decide which algorithm to run or how any parameters should be set.

Command-line arguments may be used to specify simulation parameters, but all such arguments must be optional (so use default values appropriately).


Part II:

Extend your simulation such that 10% of the n processes begin at time 0, the other 90% entering the system at random times after time 0. Use an exponential distribution to determine arrival times. More specifically, use an average arrival rate of 1000 milliseconds and ignore any random values beyond 8000 milliseconds (see ExponentialRandom.java for more details).

As in Part I, run your simulation until all processes complete their CPU bursts. Note that there may be periods of time where no processes are in the system.

Be careful in calculating turnaround and wait times, as the arrival times for processes may now vary.

Please be sure to submit code that handles both parts via an optional -PART2 command-line flag.

Compiler:
	Use gcc or g++ to compile your C or C++ code (and specify -Wall to ensure all compiler warnings are addressed before submitting your assignment). For Java programs, use javac to compile your code and java to execute it. Your code must work on a standard installation of Ubuntu.

Grading Criteria:
	Compiles and runs without errors, crashes, or warnings: 10 points
	(Part I) Successfully simulates each of the five scheduling algorithms: 40 points
	(Part I) Displays lines of output for each case (mimicking the output format above): 20 points
	(Part I) Displays correct statistics (shown above) at end of simulation: 10 points
	(Part II) Successfully simulates each of the five scheduling algorithms given some processes have non-zero arrival times: 20 points
	(Late) -10 points per day, up to 4 days maximum