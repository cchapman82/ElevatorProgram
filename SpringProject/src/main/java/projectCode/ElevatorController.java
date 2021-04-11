package main.java.projectCode;

import main.java.gui.ElevatorDisplay;

import java.time.LocalTime;
import java.util.ArrayList;

import static main.Main.formatter;
import static main.Main.multElev;

/*
    Holds Elevators and tells where requests are handled
    ElevatorController is a singleton object and facade to facilitate operations
    between floors and elevators
 */
public class ElevatorController {

    //instance variables
    private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
    private static final ElevatorController controller = new ElevatorController();
    private ArrayList<MultipleElevators> multipleElevators = new ArrayList<MultipleElevators>();

    //get singleton object
    public static ElevatorController getInstance() {
        return controller;
    }

    //getters and setters

    //add one thread to arrayList
    public void setMultipleElevators(MultipleElevators m) {
        multipleElevators.add(m);
    }

    public MultipleElevators getMultipleElevators(int num) {
        return multipleElevators.get(num);
    }

    public void replaceThread( int num) {
        multipleElevators.set(multipleElevators.indexOf(multipleElevators.get(num)),
                new MultipleElevators(multipleElevators.get(num).getElevator()));

    }

    //make Elevator arrayList and dynamically give id's
    public void makeElevators(int numElevs) {
        System.out.println("Making " + numElevs + " elevators.");

        for (int j = 1; j <= numElevs; j++) {
            MultipleElevators m = new MultipleElevators(new Elevator(j));
            setMultipleElevators(m);
            elevators.add(m.getElevator());
        }
    }

    //get elevator to be updated and manipulated
    public Elevator getElevator(int elevNm) {
        return multipleElevators.get(elevNm).getElevator();
    }

    /*person is added to the floor and makes request, starts a new thread or
        adds request to running thread
        to get an elevator to handle the request.  ONLY ONE CALL per thread
     */
    public int getElevToReceiveRequest() {
        int next = 0;
        int result = 0;
        int busy = 0;
        //for initial test differentiation
        if (multElev == 1) {
            if (busy == 0) {
                for (MultipleElevators m : multipleElevators) {
                    Elevator e = m.getElevator();
                    if (e.getRequests().length == 0) {
                        result = multipleElevators.indexOf(m);
                        break;
                    }
                    busy = 1;
                }
            } else {
                //if all elevators are busy, start back at beginning of arrayList
                if (next <= 3) {
                    result = next;
                    next++;
                } else {
                    next = 0;
                    result = next;
                    next++;
                }
            }
        } else {
            result = 0;
        }
        return result;
    }

    //add request to elevator handling the request and add floorRequest for logging
    public int addRequest(int num) {
        int currElevator = getElevToReceiveRequest();
            multipleElevators.get(currElevator).setUpdate(true);
            multipleElevators.get(currElevator).setElev(currElevator);
            multipleElevators.get(currElevator).getElevator().addFloorRequests(num);
            multipleElevators.get(currElevator).getElevator().reqToElev(num);

        return currElevator;
    }

    //move a person from elevator to done list on floor
    public void elevToFloor(People person, int position) {
        Building.getInstance().getFloor(position).addDonePerson(person);
    }

    //move people from floor and move them one by one to elevator
    public void floorToElev(ElevatorDisplay.Direction dir, int position, int elevNum) {
        People[] people = Building.getInstance().getFloor(position).getWaiting(dir);
        if (people.length > 0) {
            for (int i = 0; i < people.length; i++) {
                try {
                    getElevator(elevNum).setRiders(people[i]);
                    System.out.println(formatter.format(LocalTime.now()) + " " + people[i].getName() + " entered Elevator " + (elevNum + 1) + " [Riders: " +
                            ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getRidersString()+ "]");
                } catch (MaxCapacityException m) {
                    m.getMessage();
                    Building.getInstance().getFloor(people[i].getOrigin()).addWaitingPerson(people[i]);
                }
            }
        }
    }
}
