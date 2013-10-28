package com.austingulati.opsys.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main
{
    private static final Integer N = 20; // Number of processes
    public static final Integer CPU_COUNT = 2;
    public static final Integer CONTEXT_SWITCH = 15;

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

        // Add schedulers to the list
        schedulers.addAll(Arrays.asList(new FirstComeScheduler(), new ShortestJobFirstScheduler(), new RoundRobinScheduler()));

        // Add processes to schedulers
        // for(Scheduler scheduler : schedulers)
        // {
        //     scheduler.addProcesses(processes);
        // }

        // Run schedulers
        for(Scheduler scheduler : schedulers)
        {
            scheduler.addProcesses(processes);
            System.out.println("Using scheduler: " + scheduler.getName());
            while(scheduler.hasUnfinishedProcesses())
            {
                scheduler.tick();
            }
            System.out.println();

            // Print final results
            scheduler.printResults();

            for(Process process : processes)
            {
                process.reset();
            }
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
