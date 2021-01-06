public class Process {
    private String name;
    private int burst_time;
    private int arrival_time;
    private int priority;
    private int quantum_time;
    int waiting_Time;
    int turnaround_Time;
    int stoppedTime;

    public String toString() {
        String var = this.getName();
        return String.valueOf(var + " : " + this.getPriority());
    }

    public Process(String name, int burst_time, int arrival_time, int priority) {
        this.name = name;
        this.burst_time = burst_time;
        this.arrival_time = arrival_time;
        this.setPriority(priority);
    }

    public Process(String name, int burst_time, int arrival_time, int priority, int quantum_time) {
        this.name = name;
        this.setBurst_time(burst_time);
        this.arrival_time = arrival_time;
        this.setPriority(priority);
        this.setQuantum_time(quantum_time);
        this.setTurnaround_Time(burst_time);
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

    public int getQuantum_time() {
        return this.quantum_time;
    }

    public void setQuantum_time(int quantum_time) {
        this.quantum_time = quantum_time;
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
}
