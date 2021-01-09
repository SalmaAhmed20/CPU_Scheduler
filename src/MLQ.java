import java.util.Vector;

public class MLQ {
    //Just an array to store all process
    private Vector <Process> Process = new Vector <>() ;
    //Round Robin Queue
    private Vector <Process> Foreground =new Vector <>();
    //First come first service  Queue
    private Vector <Process> background = new Vector <>() ;
    private int timeQuantum;
    private int currenttime=0;


    private void SortArrivalTime( Vector<Process> p)
    {
        for (int i = 1; i < p.size(); i++) {
            var Process = p.get(i);
            int j;
            for ( j = i-1; j >=0 && (Process.getArrival_time() < p.get(j).getArrival_time()) ; j--)
                p.set(j+1,p.get(j));
            p.set(j+1,Process);
        }

    }
    private boolean CheckExistQ1(int t, Vector<Process> Plist){

        for (Process p : Plist){
            if (p.getArrival_time() <= t){
                return true;
            }
        }
        return false;
    }

    public void Statistics_MLQ ()
    {
        float TotalWait =0;
        float TotalTurnAround=0;
        System.out.println("------------MultiLevel Queue-----------");
        for (Process process : Process)
        {
            process.turnaround_Time =process.turnaround_Time-process.getOriginalAT();
            process.waiting_Time=process.turnaround_Time - process.getOriginalBT();
            TotalWait =TotalWait+process.waiting_Time;
            TotalTurnAround=TotalTurnAround+process.turnaround_Time;
            System.out.println(process);
        }
        System.out.println("Average Waiting Time = " + TotalWait/(float) Process.size()+" unit ");
        System.out.println("Average Turn Around Time = " + TotalTurnAround/(float) Process.size()+" unit ");
    }
    public boolean Add( Process p)
    {
        if (p.getQueueNum() == 1)
            return Foreground.add(p);
        else if (p.getQueueNum() == 2)
            return background.add(p);
        System.out.println("Not Available Queue");
        return false;
    }
    public void Algorithm()
    {
        //Loop until all processes in 2 Queues executed
        while (!Foreground.isEmpty() || !background.isEmpty())
        {
            //sort the processes depend on arrival time
            this.SortArrivalTime(Foreground);
            this.SortArrivalTime(background);
            //for round robin Queue are there any process arrived
            for (int i =0 ; i < Foreground.size() ; i++)
            {
                if ( Foreground.get(i).getArrival_time() <= currenttime ) {
                    //Execute current process for Time Quantum specified by user
                    //every time period there is a process that will leave a cpu
                    //and another one enter instead of it
                    for (int j = 0; j < timeQuantum && Foreground.get(i).getBurst_time() != 0; j++) {
                        //for response time
                        /**if ( Foreground.get(i).getStoppedTime() == -1 )
                            Foreground.get(i).setStoppedTime(currenttime);*/

                        // increment the time
                        currenttime++;
                        Foreground.get(i).setBurst_time(Foreground.get(i).getBurst_time() - 1);
                        Foreground.get(i).setArrival_time(currenttime);
                    }

                    if ( Foreground.get(i).getBurst_time() == 0 ) {
                        Foreground.get(i).setTurnaround_Time(currenttime);
                        Process.add(Foreground.remove(i));

                    }
                }
            }
            //for First come first service Queue
            for (int i = 0; i < background.size()  ; i++) {
                if ( background.get(i).getArrival_time() <= currenttime
                        && !this.CheckExistQ1(currenttime,Foreground) )
                {
                    for (int j = 0; background.get(i).getBurst_time() !=0
                            && ! this.CheckExistQ1(currenttime,Foreground) ; j++) {
                        /**if ( Foreground.get(i).getStoppedTime() == -1 )
                            Foreground.get(i).setStoppedTime(currenttime);*/
                        currenttime++;
                        background.get(i).setBurst_time(background.get(i).getBurst_time() - 1);
                        background.get(i).setArrival_time(currenttime);
                 }
                    if ( background.get(i).getBurst_time() == 0 ) {
                        background.get(i).setTurnaround_Time(currenttime);
                        Process.add(background.remove(i));
                    }

                }
                
            }

        }
    }

    public void setTimeQuantum( int timeQuantum ) {
        this.timeQuantum = timeQuantum;
    }
}
