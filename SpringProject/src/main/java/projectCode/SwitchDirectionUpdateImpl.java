package main.java.projectCode;

import java.time.LocalTime;

import static main.Main.formatter;

public class SwitchDirectionUpdateImpl implements Updateable {

    @Override
    public void update(int num) {
        ElevatorController.getInstance().getElevator(num).setCurrDir(num);
        System.out.println(formatter.format(LocalTime.now()) + " Elevator " + ElevatorController.getInstance().getMultipleElevators(num).getElevator().getId()
                + " is IDLE now for : " + ElevatorController.getInstance().getMultipleElevators(num).getElevator().getIdleTime());

    }
}
