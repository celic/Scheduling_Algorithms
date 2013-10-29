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



        Process currentProcess = runningProcesses.get(processor);
        
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

        for(Process nextProcess : waitingProcesses)
        {
            if(nextProcess.getPriority() == workingPriority)
            {
                int indexToRemove = waitingProcesses.indexOf(nextProcess);
                return waitingProcesses.remove(indexToRemove);
            }
        }

        // No more processes left, move to lower priority
        workingPriority++;

    }
}