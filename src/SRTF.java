
import java.util.Vector;

public class SRTF {
    private Vector<Process> Completed = new Vector <>();
    private Vector<Process> SJF = new Vector <>();
    private  int context_switch;
    int currentTime = 0;

    public boolean Add( Process p )
    {
        return SJF.add(p);
    }

    public void  Algorithm()
    {


        int total = 0;
        while (true) {
            if ( total == SJF.size() )
                break;
            int min = Integer.MAX_VALUE;
            int shortest = SJF.size();
            for (int i = 0; i < SJF.size(); i++) {
                if ( SJF.get(i).getArrival_time() <= currentTime && SJF.get(i).flag == false &&
                        SJF.get(i).getBurst_time() < min  ) {
                    min = SJF.get(i).getBurst_time();
                    shortest = i;
                }
            }
            if ( shortest == SJF.size() )
                currentTime++;
            else {
                SJF.get(shortest).setBurst_time(SJF.get(shortest).getBurst_time() - 1);
                currentTime++;
                if ( SJF.get(shortest).getBurst_time() == 0 ) {
                    SJF.get(shortest).completeTime = currentTime;
                    System.out.println(SJF.get(shortest).getName()+" "+SJF.get(shortest).completeTime);
                    SJF.get(shortest).flag = true;
                    Completed.add(SJF.get(shortest));
                    total++;
                    //SJF.remove(shortest);
                }
            }
        }
    }
    void Statistic()
    {
        float TotalWait =0;
        float TotalTurnAround=0;
        int i =0;
        System.out.println("------------Shortest remaining Time Queue-----------");
        for (Process process : Completed)
        {
            process.turnaround_Time =process.completeTime- process.getOriginalAT();
            process.waiting_Time=process.turnaround_Time- process.getOriginalBT();
            TotalWait =TotalWait+process.waiting_Time;
            TotalTurnAround=TotalTurnAround+process.turnaround_Time;
            System.out.println(process);
        }
        System.out.println("Average Waiting Time = " + TotalWait/(float) Completed.size()+" unit ");
        System.out.println("Average Turn Around Time = " + TotalTurnAround/(float) Completed.size()+" unit ");
    }


    public void setContext_switch( int context_switch ) {
        this.context_switch = context_switch;
    }
}
