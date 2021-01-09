

import java.util.ArrayList;

/*
 * Content: This class has the RoundRobin algorithm in CPU scheduling
 */
public class RoundRobin {
	
	/*This arrayList to hold the all processes*/
	private ArrayList<Process> processes = new ArrayList<Process>();/*Ready Queue*/
	
	/*To store the updated processes*/
	private ArrayList<Process> stoppedProcesses = new ArrayList<Process>();/*Running Queue*/
	
	/*Context-Switch*/
	private int contextSwitch;
	
	/*Quantum Time*/
	private int quantumTime;
	
	/*The following arrayList to hold the all waiting times to calculate the average*/
	private ArrayList<Integer> waitingTimeList = new ArrayList<Integer>();
	
	/*The following arrayList to hold the all turn arround times to calculate the average*/
	private ArrayList<Integer> turnArroundTimeList = new ArrayList<Integer>();


	public RoundRobin() {
	
	}

	public void setProcess(Process process) {
		processes.add(process);
	}

	public int getContextSwitch() {
		return contextSwitch;
	}

	public void setContextSwitch(int contextSwitch) {
		this.contextSwitch = contextSwitch;
	}

	public int getQuantumTime() {
		return quantumTime;
	}

	public void setQuantumTime(int quantumTime) {
		this.quantumTime = quantumTime;
	}
	
	/*The following function to do the algorithm*/
	public void roundRobinAlgorithm()
	{
		int completionTime = 0;
		/*We need to know how many contextSwitches done in the algorithm*/
		System.out.println("Execution Order: ");
		do
		{
			/*We need to get the smallest arrival time*/
			int mini = processes.get(0).getArrival_time();/*initial value*/
			int index = 0;
			for(int i = 0 ; i<processes.size() ; i++) {
				if(mini > processes.get(i).getArrival_time()) {
					mini = processes.get(i).getArrival_time();
					index = i;
				}
			}
			if(processes.get(index).getBurst_time() < this.quantumTime) {
				completionTime+=processes.get(index).getBurst_time();
			}
			else {
				completionTime+=this.quantumTime;
			}
			
			if(processes.get(index).getBurst_time() <= this.quantumTime) {
					processes.get(index).setCompletionTime(completionTime);
					stoppedProcesses.add(processes.get(index));
					System.out.println(processes.get(index).getName() + " had finished execution");
					processes.remove(index);
					
			}
			else
			{
				int tempBurstTime = processes.get(index).getBurst_time() - this.quantumTime;
				processes.get(index).setBurst_time(tempBurstTime);/*The remaining*/
				processes.add(processes.get(index));
				processes.remove(index);
			}
			completionTime += contextSwitch;
		}while(processes.size()!=0);
		
	}
	
	public void printStatistics() {
		int waitingSum = 0;
		double averageWaiting = 0.0;
		int turnArroundSum = 0;
		double averageTurnArround = 0.0;
		System.out.println("******************************************");
		for(int i=0 ; i<stoppedProcesses.size() ; i++) {
			waitingSum += stoppedProcesses.get(i).waiting_Time;
			turnArroundSum += stoppedProcesses.get(i).turnaround_Time;
		}
		averageWaiting = waitingSum / stoppedProcesses.size();
		System.out.println("Average waiting time = " + averageWaiting);
		System.out.println("******************************************");
		averageTurnArround = turnArroundSum / stoppedProcesses.size();
		System.out.println("Average turnArround time = " + averageTurnArround);
		System.out.println("******************************************");
		for(int i=0 ; i<stoppedProcesses.size() ; i++) {
			System.out.println(stoppedProcesses.get(i).getName() + " " + stoppedProcesses.get(i).waiting_Time);
		}
	}
}
