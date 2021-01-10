import java.util.ArrayList;
import java.util.Scanner;

public class CPU {
    public static void main( String[] args ) {
        Scanner input = new Scanner(System.in);
        //Objects for SJF,RR,Priority Queue
        MLQ multi =new MLQ();
        Priority pro = new Priority();
        RoundRobin RR = new RoundRobin();
        System.out.print("Enter number of Process you want to Execute: ");
        int num = input.nextInt();
        for (int i = 0; i < num ; i++) {
            System.out.print("P"+(i+1)+" Arrival Time : ");
            int arrival = input.nextInt();
            System.out.print("P"+(i+1)+" Burst Time : ");
            int Burst = input.nextInt();
            System.out.print("P"+(i+1)+" Priority : ");
            int Pri = input.nextInt();
            System.out.print("P"+(i+1)+" Queue Number : ");
            int Queuenum = input.nextInt();
            System.out.println("-------------------------------------------");
            RR.Add(new Process(("P"+(i+1)),Burst,arrival,Pri,Queuenum));
            pro.Add(new Process(("P "+(i+1)),Burst,arrival,Pri,Queuenum));
            multi.Add(new Process(("P "+(i+1)),Burst,arrival,Pri,Queuenum));


        }
        System.out.print(" Context Switch for SJF, RR : ");
        int Context = input.nextInt();
        System.out.print(" Time Quantum for RR, MLQ : ");
        int time = input.nextInt();
        multi.setTimeQuantum(time);

        RR.setContextSwitch(Context);
        RR.setQuantumTime(time);
        
        System.out.println("----------------------------------------RoundRobin Algorithm------------------------------");
        RR.roundRobinAlgorithm();
        RR.printStatistics();

        pro.PPS();
        pro.displayTime();

        multi.Algorithm();
        multi.Statistics_MLQ();
        

    }
}
