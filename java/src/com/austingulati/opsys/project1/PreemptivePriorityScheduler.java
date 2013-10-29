package com.austingulati.opsys.project1;

public class PreemptivePriorityScheduler extends Scheduler
{
    private Integer sliceLength = 100; // Slice length in ms

    @Override
    public void addProcess(Process process)
    {
        // System.out.printf("Adding process %d (size is %d)\n", process.getId(), waitingProcesses.size());
        if(waitingProcesses.size() == 0)
        {
            waitingProcesses.add(process);
        }
        else
        {
            Boolean processAdded = false;
            for(int i = 0; i < waitingProcesses.size(); ++i)
            {
                Process nextProcess = waitingProcesses.get(i);
                if(nextProcess.getPriority() > process.getPriority())
                {
                    waitingProcesses.add(i, process);
                    processAdded = true;
                    break;
                }
            }

            if(!processAdded)
            {
                waitingProcesses.add(process);
            }
        }
        // System.out.printf("Adding process %d (size is %d)\n", process.getId(), waitingProcesses.size());
    }

    public String getName()
    {
        return "Preemptive Priority";
    }

    public Process getNextProcess(Integer processor)
    {
        Process currentProcess = runningProcesses.get(processor);
        if(currentProcess != null)
        {
            Integer timeRemaining = currentProcess.getTimeRemaining(),
                timeTotal = currentProcess.getTimeTotal();
            Boolean preempted = false;
            if(waitingProcesses.size() > 0)
            {
                Process highestPriority = waitingProcesses.get(0);
                if(highestPriority.getPriority() < currentProcess.getPriority())
                {
                    // Get preempted
                    addProcess(currentProcess);
                    runningProcesses.set(processor, null);
                    preempted = true;
                }
            }
            if(!preempted && (timeTotal - timeRemaining) % sliceLength != 0)
            {
                // Continue the current process
                return currentProcess;
            }
            if(!preempted)
            {
                // Otherwise, stick it on the end of the list
                addProcess(currentProcess);
                runningProcesses.set(processor, null);
            }
        }
        if(waitingProcesses.size() == 0) return null;
        return waitingProcesses.remove(0); 
    }
}