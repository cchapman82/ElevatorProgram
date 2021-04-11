package main.java.projectCode;

//Get next request if elevator is already past maximum request
public class DownRequestableImpl implements Requestable {

    @Override
    public int getRequest(int elevNum) {

        //hold the array given by priority queue
        int[] ints =  ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getRequests();

        //hold result of lowest request after current position
        int results = 0;

        //find the request
        for (int i = 0; i < ints.length; i++) {
            //while going down, if the request is less than or equal to current elevator position
            if (ints[i] <= ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getCurrPos())
                results = ints[i];
        }

        return results;
    }
}
