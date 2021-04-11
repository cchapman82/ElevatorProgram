package main.java.projectCode;

//get the next highest request based on current position
public class UpRequestableImpl implements Requestable {

    @Override
    public int getRequest(int elevNum) {
        int[] ints =  ElevatorController.getInstance().getElevator(elevNum).getRequests();
        int results = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getCurrPos() <= ints[i]) {
                results = ints[i];
                break;
            }
        }
        return results;
    }
}
