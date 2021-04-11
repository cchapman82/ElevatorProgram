package main.java.projectCode;

//make call to move elevatro if there are no requests at current position
public class MoveUpdateableImpl implements Updateable {

    public void update(int elevNum) {
        ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().move(elevNum);
    }
}
