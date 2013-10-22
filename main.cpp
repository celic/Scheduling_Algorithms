/* 
*  main.cpp
*  Operating Systems - Project 1
*  Chris Celi
*  Damian Mastylo
*  Austin Gulati
*/ 

#include <iostream>
#include <stdlib.h>

int main(int argc, char* argv[]){

	// General outline

	/* 
	* Class for each algorithm type to keep it modular, perhaps extending another class with the CPU methods and such
	* Create the class when it is that algorithms turn and run the simulate() method in that class
	* Simulation data can be stored in the class
	* Processes perhaps instances of its own class? so we have a set of processes or a map to organize them by attribute
	* Simply retrieve the data later when needed for display
	*/

	// Other details

	/*
	* Must support 1-m CPUs (labeled with A, B, C etc)
	*	Default 1, arg commands for more
	*
	* Must support 1-n Processes (labeled with numbers)
	*	Default 20, arg commands for more/less
	*	Processes have a 'time' meaning how much time they need a CPU to finish their task
	*		Between 50-400ms
	*
	* Algorithms
	*	All have separate 'running times' starting at 0
	*
	*	First-Come, First-Served - no preemption
	*	Shortest-Job-First - no preemption
	*	Shortest-Job-First - with preemption
	*	Round-Robin - configurable time slice 't' initially set to 100ms
	*	Preemptive Priority - random priority levels (0-4, 0 highest), same priority processes go through RR
	*		if a higher priority process joins, it will preempt a running process
	*	
	*	TCS between processes, starting at 15ms, for every context switch
	*		8ms for removing 'before' process
	*		7ms for selecting and resuming 'after' process
	*/

	return EXIT_SUCCESS;
}