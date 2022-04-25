package ToStudents.ToStudents.Templates;

import java.util.*;

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//TODO Implement this
		
		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());

		// If homeworkPlan[i] has a value -1, it indicates that the 
		// i'th timeslot in the homeworkPlan is empty
		//homeworkPlan contains the homework schedule between now and the last deadline
		int[] homeworkPlan = new int[lastDeadline];
		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}

		int[] dCounter = new int[lastDeadline+1];
		int d;
		int k;

		for(Assignment a: Assignments){
			d = a.deadline;
			if(dCounter[d]++ >= d) continue; //if not enough room for assignment before its deadline
			k = d-1;
			System.out.println(k);
			while(k!= -1 && homeworkPlan[k]!=-1){	//find next available slot before deadline
				k--;
			}
			if(k==-1) continue;
			homeworkPlan[k] = a.number;

		} 
	
		
		return homeworkPlan;
	}
}
	



