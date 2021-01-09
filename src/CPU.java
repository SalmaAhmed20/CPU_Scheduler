import java.util.ArrayList;
import java.util.Scanner;

public class CPU {
    public static void main( String[] args ) {
        Scanner input = new Scanner(System.in);
        ArrayList <Process> P = new ArrayList <>();
        //Objects for SJF,RR,Priority Queue
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
            P.add(new Process("P"+(i+1),Burst,arrival,Pri,Queuenum));


        }
        System.out.print(" Context Switch for SJF, RR : ");
        int Context = input.nextInt();
        System.out.print(" Time Quantum for RR, MLQ : ");
        int time = input.nextInt();
        MLQ multi =new MLQ();
        multi.setTimeQuantum(time);
        for (int i = 0; i <P.size() ; i++) {
            //.add For SJF
            //.add For Round Robin
            //.add For priority Queue
            multi.AddtoSch(P.get(i));

        }
        multi.Algorithm();
        multi.Statistics_MLQ();
    }
}
