package com.austingulati.opsys.project1;

class FirstComeScheduler extends Scheduler
{
    public String getName()
    {
        return "First Come First Serve (FCFS)";
    }

    public Process getProcess()
    {
        if(processes.size() == 0)
        {
            return null;
        }
        else if(hasCurrentProcess())
        {
            return getCurrentProcess();
        }

        return processes.get(0);
    }
}