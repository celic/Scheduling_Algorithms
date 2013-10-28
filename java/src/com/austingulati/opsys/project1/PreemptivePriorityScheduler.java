package com.austingulati.opsys.project1;

class PreemptivePriorityScheduler extends Scheduler
{
    private Boolean preemptive = true;

    public String getName()
    {
        return "Preemptive Priority";
    }

    public Process getNextProcess(Integer processor)
    {
        return null;
    }
}