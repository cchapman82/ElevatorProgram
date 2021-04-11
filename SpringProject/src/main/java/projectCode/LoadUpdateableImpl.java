package main.java.projectCode;

import main.java.gui.ElevatorDisplay;

import java.time.LocalTime;

import static main.Main.formatter;

public class LoadUpdateableImpl implements Updateable {
    @Override
    public void update(int elevNum) {
        //returns to floor one and removes the idle request, but does not open the doors
        if (ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getIdleTime() == 20) {
            ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().setIdleTime(0);
            ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().removeNextRequest(elevNum);
        }
        //sets current direction for elevator, elevator is created in IDLE, opens/closes doors, loads people and removes the request
        else {
            ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().removeNextRequest(elevNum);
            ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().setDoorsOpen(true);
            ElevatorDisplay.getInstance().openDoors(elevNum + 1);

            System.out.println(formatter.format(LocalTime.now()) + " Elevator " + (elevNum + 1) + " Doors Open");

            ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().load(elevNum, ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getCurrDir());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().setDoorsOpen(false);
            ElevatorDisplay.getInstance().closeDoors(elevNum + 1);

            System.out.println(formatter.format(LocalTime.now()) + " Elevator " + (elevNum + 1) + " Doors Closed");

        }
    }
}
