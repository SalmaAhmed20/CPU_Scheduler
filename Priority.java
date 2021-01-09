import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Priority {

   Vector<Process> queue;
    Vector<Process> PPQ_queue;
    int currentTime;

    public Priority()
    {
        queue=new Vector<>();
        PPQ_queue=new Vector<>();
        currentTime=0;
    }

   public void Add(Process p)
   {
       queue.add(p);
   }
   public boolean chickFinish(Process p)
   {
       if(p.getBurst_time()==0)
           return true;
       else
           return false;
   }
   public boolean checkPriority(Process p,int curTime)
   {
       int chickPri;
       if(PPQ_queue.size()==0&&p.getArrival_time()<=curTime)
       {
           return true;
       }
       chickPri=p.getPriority();
       for(int i=0;i<queue.size();i++)
       {
           if(!chickFinish(queue.get(i))&&queue.get(i).getArrival_time()<=curTime&&queue.get(i).getPriority()<=chickPri){
              chickPri=queue.get(i).getPriority();
           }

       }
       if(!chickFinish(p)&&p.getArrival_time()<=curTime&&p.getPriority()<=chickPri) {
           return true;
       }
       else
        return false;

   }

   public void displayTime()
   {
       int total_wait=0,total_turnaround=0;
       System.out.println( "-------------- Priority Scheduling --------------");
       for(int i=0;i<PPQ_queue.size();i++)
       {
           PPQ_queue.get(i).turnaround_Time=PPQ_queue.get(i).turnaround_Time-PPQ_queue.get(i).getOriginalAT();
           PPQ_queue.get(i).waiting_Time=PPQ_queue.get(i).turnaround_Time - PPQ_queue.get(i).getOriginalBT();
           total_wait =total_wait+PPQ_queue.get(i).waiting_Time;
           total_turnaround=total_turnaround+PPQ_queue.get(i).turnaround_Time;
           System.out.println( PPQ_queue.get(i).toString());

       }
       System.out.println("Average Waiting Time = " + total_wait/(float) PPQ_queue.size()+" unit ");
       System.out.println("Average Turn Around Time = " + total_turnaround/(float) PPQ_queue.size()+" unit ");
   }

   public void PPS()
   {

       for(int i=0;i<queue.size();i++) {
           if (checkPriority(queue.get(i), currentTime)) {

               if (queue.get(i).getBurst_time() == queue.get(i).getOriginalBT()) {
                   queue.get(i).setArrival_time(currentTime);
               }
               queue.get(i).setBurst_time(queue.get(i).getBurst_time() - 1);
               currentTime++;
               if (chickFinish(queue.get(i))) {
                   queue.get(i).setTurnaround_Time(currentTime);
                   PPQ_queue.add(queue.get(i));
               }
               i = 0;
           }
           if(currentTime%15==0)
           {
               if(!chickFinish(queue.get(i)))
               {
                   queue.get(i).setPriority(queue.get(i).getPriority()-1);
               }

           }
       }

   }

}