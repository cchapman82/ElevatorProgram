package main.java.projectCode;

import java.util.PriorityQueue;

//order priorityQueue to natural order
public class UpOrderableImpl implements Orderable {

    public void reOrder(int[] ints, int elevNum) {
        PriorityQueue reordered = new PriorityQueue();
        for (Object o : ints) {
            reordered.add(o);
        }
        ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().setRequests(reordered);

    }
}
