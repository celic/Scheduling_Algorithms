package com.austingulati.opsys.project1;

class FirstComeScheduler extends Scheduler
{
    public FirstComeScheduler()
    {
        super();
    }
    
    public String getName()
    {
        return "First Come First Serve (FCFS)";
    }

    public Process getNextProcess(Integer processor)
    {
        if (runningProcesses.get(processor) != null)
        {
            return runningProcesses.get(processor);
        }
        else
        {
            if(waitingProcesses.size() > 0)
            {
                return waitingProcesses.remove(0);
            }
            else
            {
                return null;
            }
        }
    }
}