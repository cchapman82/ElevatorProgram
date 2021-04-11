package main.java.projectCode;

import main.Main;
import main.java.gui.ElevatorDisplay;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import static main.Main.formatter;

//Floors need to hold a container of waiting people and done people
public class Floor {

    //instance variables
    private int id;
    private ArrayList<People> done = new ArrayList<>();
    private ArrayList<People> waiting = new ArrayList<People>();

    public Floor (int num) {
        setId(num);
    }
     public void setId(int num) {
        try {
            if (id > Main.getNumFloor() || id < 1)
                throw new InvalidFloorRequestException("Floor id is not between 1 and " + Main.getNumFloor());
            id = num;
        } catch (InvalidFloorRequestException i) {
            i.getMessage();
        }
     }
    /*  Take people waiting on the floor and for the direction the elevator is going in
        and put them in an array and send them to the elevatorController
     */
    public People[] getWaiting(ElevatorDisplay.Direction dir){
        ArrayList<People> dirWaiting = new ArrayList<People>();
        for (Iterator iterator = waiting.iterator(); iterator.hasNext();) {
            People p = (People) iterator.next();
            if(p.getDirection().equals(dir) || (p.getOrigin() == 1 || p.getOrigin() == Building.getInstance().getFls())) {
                dirWaiting.add(p);
            }
        }
        People[] waitingArray = new People[dirWaiting.size()];
        int i = 0;
        for (People p : dirWaiting) {
            waiting.remove(p);
            waitingArray[i] = p;
            i++;
        }
        return waitingArray;
    }

    //add a person to a floor on the building and send request to controller
    public int addWaitingPerson(People person) {
        waiting.add(person);
        int results = ElevatorController.getInstance().addRequest(person.getOrigin());
        System.out.println(formatter.format(LocalTime.now()) + " " + person.getName() + " presses " + person.getDirection() +
                " on Floor " + id);
        return results;
    }

    //add person to done list on floor
    public void addDonePerson(People person) {
        done.add(person);
    }
}
