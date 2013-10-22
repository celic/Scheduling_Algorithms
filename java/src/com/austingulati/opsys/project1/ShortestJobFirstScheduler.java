package com.austingulati.opsys.project1;

class ShortestJobFirstScheduler extends Scheduler
{
    private Boolean preemption = false;

    public String getName()
    {
        return "Shortest Job First (SJF)";
    }

    public void enablePreemption()
    {
        preemption = true;
    }

    public void disablePreemption()
    {
        preemption = false;
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

        Process shortestProcess = processes.get(0);
        Integer shortestTime = shortestProcess.getTimeRemaining();
        for(Process process : processes)
        {
            if(process.getTimeRemaining() < shortestTime)
            {
                shortestTime = process.getTimeRemaining();
                shortestProcess = process;
            }
        }

        return shortestProcess;
    }
}