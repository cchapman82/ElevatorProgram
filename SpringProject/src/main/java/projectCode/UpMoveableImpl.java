package main.java.projectCode;

import main.java.gui.ElevatorDisplay;

import java.time.LocalTime;

import static main.Main.formatter;
import static main.java.gui.ElevatorDisplay.Direction.UP;

//move elevator and update display
public class UpMoveableImpl implements Movable {

    @Override
    public void move(int elevNum) {
        ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().setCurrPos(
                ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getCurrPos()+1);
        ElevatorDisplay.getInstance().updateElevator(elevNum + 1,
                ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getCurrPos(),
                ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getRiders().length, UP);
        System.out.println(formatter.format(LocalTime.now()) + " Elevator " + ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getId() +
                " moving from Floor " + (ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getCurrPos() - 1) +
                " to Floor " +  ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getCurrPos() +
                " [Current FLoor Requests: " +
                ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getFloorReqString() +
                "][Current Rider Requests: " +
                ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getElevReqString() + "]");
    }
}
