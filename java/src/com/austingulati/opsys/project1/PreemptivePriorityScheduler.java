package com.austingulati.opsys.project1;

class PreemptivePriorityScheduler extends Scheduler
{
    private Integer workingPriority = 0;

    public String getName()
    {
        return "Preemptive Priority";
    }

    public Process getNextProcess(Integer processor)
    {
        // Working priority is the current level of priority being evaluated in RR fashion by the CPUs

        // If a process is preempted with a priority less than the workingPriority, it gains full access
        // otherwise it is just put at the end of the RR cycle

        System.out.printf("PROCESSES LEFT: %d %d\n", waitingProcesses.size(), getTime());

        Process currentProcess = runningProcesses.get(processor);
        
        // Continue with the current process if it exists
        if(currentProcess != null)
        {
            Integer timeRemaining = currentProcess.getTimeRemaining(),
                timeTotal = currentProcess.getTimeTotal();
            
            if((timeTotal - timeRemaining) % Main.SLICE_LENGTH > 0)
            {
                return currentProcess;
            }
            
            // Process finished its burst, stick it on the end of the list
            addProcess(currentProcess);
            runningProcesses.set(processor, null);
        }

        // No processes left
        if(waitingProcesses.size() == 0) return null;

        // Sift through priorties in ascending order until a process is found
        for(int currentPriority = workingPriority; currentPriority <= 4; currentPriority++)
        {
            for(int i = 0; i < waitingProcesses.size(); i++)
            {
                if(waitingProcesses.get(i).getPriority() == workingPriority)
                {
                    // Set new priority for efficiency
                    workingPriority = currentPriority;

                    // Remove found process from waitingProcesses list
                    return waitingProcesses.remove(i);
                }
            }
        }

        // No valid processes were found
        return null;
    }
}