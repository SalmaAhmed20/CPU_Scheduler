public class Process {
    /*common Attributes */
    private String name;
    private int burst_time;
    private int arrival_time;
    int waiting_Time;
    int turnaround_Time;
    /*need in Priority Queue Algorithm*/
    private int priority;

    private int stoppedTime ;
    /*Need in multilevel statistic*/
    private int QueueNum;
    private  int originalAT;
    private  int OriginalBT;


    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", burst_time=" + OriginalBT +
                ", arrival_time=" + originalAT +
                ", waiting_Time=" + waiting_Time +
                ", turnaround_Time=" + turnaround_Time +
                '}';
    }

    public Process( String name, int burst_time, int arrival_time, int priority,int num) {
        this.name = name;
        this.burst_time = burst_time;
        this.arrival_time = arrival_time;
        this.setPriority(priority);
        this.stoppedTime=-1;
        /*need in Multilevel Queue*/
        this.QueueNum=num;
        this.originalAT=arrival_time;
        this.OriginalBT=burst_time;
    }


    public String getName() {
        return this.name;
    }

    public int getBurst_time() {
        return this.burst_time;
    }

    public int getArrival_time() {
        return this.arrival_time;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }

    public int getTurnaround_Time() {
        return this.turnaround_Time;
    }

    public void setTurnaround_Time(int turnaround_Time) {
        this.turnaround_Time = turnaround_Time;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getQueueNum() {
        return QueueNum;
    }

    public void setQueueNum( int queueNum ) {
        QueueNum = queueNum;
    }

    public int getStoppedTime() {
        return stoppedTime;
    }

    public void setStoppedTime( int stoppedTime ) {
        this.stoppedTime = stoppedTime;
    }

    public void setArrival_time( int arrival_time ) {
        this.arrival_time = arrival_time;
    }

    public int getOriginalAT() {
        return originalAT;
    }

    public void setOriginalAT( int originalAT ) {
        this.originalAT = originalAT;
    }

    public int getOriginalBT() {
        return OriginalBT;
    }

    public void setOriginalBT( int originalBT ) {
        OriginalBT = originalBT;
    }
}
