package com.austingulati.opsys.project1;

class ShortestJobFirstScheduler extends Scheduler
{
    private Boolean preemption = false;

    public String getName()
    {
        return "Shortest Job First (SJF) with" + (preemption ? " preemption" : "out preemption");
    }

    public void enablePreemption()
    {
        preemption = true;
    }

    public void disablePreemption()
    {
        preemption = false;
    }

    public Process getNextProcess(Integer processor)
    {
        if(runningProcesses.get(processor) != null)
        {
            return runningProcesses.get(processor);
        }
        if(waitingProcesses.size() == 0)
        {
            return null;
        }

        Integer shortestProcess = 0;
        Integer shortestTime = waitingProcesses.get(0).getTimeRemaining();
        for(Integer i = 0; i < waitingProcesses.size(); ++i)
        {
            Process process = waitingProcesses.get(i);
            if(process.getTimeRemaining() < shortestTime)
            {
                shortestTime = process.getTimeRemaining();
                shortestProcess = i;
            }
        }

        return waitingProcesses.remove((int) shortestProcess);
    }
}