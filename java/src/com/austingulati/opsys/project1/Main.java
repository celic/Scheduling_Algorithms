package com.austingulati.opsys.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main
{
    private static final Integer N = 3; // Number of processes
    public static final Integer M = 1;  // Number of cores
    public static final Integer TCS = 15;  // Duration of context switching

    public static void main(String[] args)
    {
        // Create ExponentialRandom object for random times
        ExponentialRandom expRand = new ExponentialRandom();
        // use via expRand.nextInt()

        // Create processes
        List<Process> processes = new ArrayList<Process>();
        Random random = new Random();
        for(Integer i = 0; i < N; ++i)
        {
            Integer time = random.nextInt(350) + 50;
            Integer priority = random.nextInt(4);
            Process process = new Process(i, time, priority);
            processes.add(process);
        }

        // Create schedulers
        List<Scheduler> schedulers = new ArrayList<Scheduler>();

        // Need a temp object for a preemptive SJF
        ShortestJobFirstScheduler preemptiveShortestJob = new ShortestJobFirstScheduler();
        preemptiveShortestJob.enablePreemption();

        // Add schedulers to the list
        //schedulers.addAll(Arrays.asList(new FirstComeScheduler(), new ShortestJobFirstScheduler(), preemptiveShortestJob));
        schedulers.addAll(Arrays.asList(new RoundRobinScheduler()));

        // Add processes to schedulers
        for(Scheduler scheduler : schedulers)
        {
            scheduler.addProcesses(processes);
        }

        // Run schedulers
        for(Scheduler scheduler : schedulers)
        {
            System.out.println("Using scheduler: " + scheduler.getName());
            while(scheduler.hasProcesses())
            {
                scheduler.tick();
            }
            System.out.println();

            // Print final results
            scheduler.printResults();
        }

        // Run system
       // System.out.println("   TIME\t|  FCFS\t|   SJF\t| SJF+P\t| RR\t");
       // while(schedulers.size() > 0)
       // {
       //     System.out.printf("%7d\t| ", schedulers.get(0).getTime());
       //     for(Scheduler scheduler : schedulers)
       //     {
       //         scheduler.tick();
       //         System.out.printf("%5d\t| ", scheduler.getCurrentProcess().getId());
       //     }
       //     System.out.println();
//        }
    }
}
