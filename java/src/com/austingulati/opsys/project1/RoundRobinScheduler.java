package com.austingulati.opsys.project1;

class RoundRobinScheduler extends Scheduler
{
    private Integer key = 0;
    private Integer sliceLength = 100; // Slice length in ms

    public String getName()
    {
        return "Round Robin";
    }

    public Process getNextProcess(Integer processor)
    {
        Process currentProcess = runningProcesses.get(processor);
        if(currentProcess != null)
        {
            Integer timeRemaining = currentProcess.getTimeRemaining(),
                timeTotal = currentProcess.getTimeTotal();
            if((timeTotal - timeRemaining) % sliceLength > 0)
            {
                return currentProcess;
            }
            // Otherwise, stick it on the end of the list
            addProcess(currentProcess);
            runningProcesses.set(processor, null);
        }
        if(waitingProcesses.size() == 0) return null;
        return waitingProcesses.remove(0);
    }
}