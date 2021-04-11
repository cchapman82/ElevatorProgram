package main.java.projectCode;

//if no requests return 0
public class NullRequestableImpl implements Requestable {

    public int getRequest(int elevNum) {
        int[] ints = ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getRequests();
        if (ints.length == 0)
            return 0;
        return ints[0];
    }

}
