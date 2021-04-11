package main.java.projectCode;

import main.java.gui.ElevatorDisplay;
import java.time.LocalTime;

import static main.Main.formatter;
import static main.java.gui.ElevatorDisplay.Direction.UP;
import static main.java.gui.ElevatorDisplay.Direction.DOWN;

/*
    People have names, origin, destination, direction
 */
public class People {

    //instance variables
    private String name;
    private int origin;
    private int destination;
    private ElevatorDisplay.Direction direction;

    //custom constructor
   public People (String nName, int nOrigin, int nDestination) {
        name = nName;
        setOrigin(nOrigin);
        setDestination(nDestination);
        setDirection(nOrigin, nDestination);
    }

    //getters and setters
    public String getName() {
        return name;
    }
    public int getDestination() {
        return destination;
    }

    /*  Set destination/origin as long as
        the request is in range of the floors in the building
     */
    public void setDestination(int num) {
        try {
            if (num > Building.getInstance().getFls() || num < 1)
                throw new InvalidFloorRequestException("Invalid Floor Request");
            destination = num;
        } catch (InvalidFloorRequestException i) {
            System.out.println(i.getMessage());
        }
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int num) {
        try {
            if (num > Building.getInstance().getFls() || num < 1)
                throw new InvalidFloorRequestException("Invalid Floor Request");
            origin = num;
        } catch (InvalidFloorRequestException i) {
            System.out.println(i.getMessage());
        }
    }


    public void setDirection(int dOrigin, int dDestination) {
        if (dDestination - dOrigin > 0)
            direction = UP;
        else
            direction = DOWN;
    }
    public ElevatorDisplay.Direction getDirection() {
        return direction;
    }

    //add person to floor in elevator
    public int addPerson(People person) {
        System.out.println(formatter.format(LocalTime.now()) + " " + person.getName() + " created on Floor " + person.getOrigin() +
                ", wants to go " + person.getDirection() + " to Floor " + person.getDestination() );
        int elev = Building.getInstance().getFloor(person.getOrigin()).addWaitingPerson(person);
        System.out.println(formatter.format(LocalTime.now()) + "  Elevator " + ElevatorController.getInstance().getMultipleElevators(elev).getElevator().getId() +
                " is going to Floor " + ElevatorController.getInstance().getMultipleElevators(elev).getElevator().getRequest(elev) +
                " for " + ElevatorController.getInstance().getMultipleElevators(elev).getElevator().getCurrDir() + " request [Current FLoor Requests: " +
                ElevatorController.getInstance().getMultipleElevators(elev).getElevator().getFloorReqString() + "][Current Rider Requests:" +
                ElevatorController.getInstance().getMultipleElevators(elev).getElevator().getElevReqString() + "]");
        return  elev;
    }
}
