package main.java.projectCode;

import java.util.Collections;
import java.util.PriorityQueue;

//Change request priority queue to max to min
public class DownOrderableImpl implements Orderable {

    public void reOrder(int[] ints, int elevNum) {

        //max priority queue
        PriorityQueue reordered = new PriorityQueue(Collections.reverseOrder());

        //add items
        for (Object o : ints) {
            reordered.add(o);
        }

        //set new queue to be the elevator's queue
        ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().setRequests(reordered);
    }
}
