package com.austingulati.opsys.project1;

class RoundRobinScheduler extends Scheduler
{
    private Integer key = 0;
    private Integer sliceLength = 100; // Slice length in ms

    public String getName()
    {
        return "Round Robin";
    }

    public Process getProcess()
    {
        if(processes.size() == 0)
        {
            return null;
        }
        else if(getTime() % sliceLength == 0)
        {
            key = (key + 1) % processes.size();
            return processes.get(key);
        }
        else
        {
            return getCurrentProcess();
        }
    }
}