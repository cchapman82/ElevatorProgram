package main.java.projectCode;

import main.Main;
import main.java.gui.ElevatorDisplay;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import static main.Main.formatter;
import static main.java.gui.ElevatorDisplay.Direction.*;

/*
    elevators need to hold people, requests and current position
 */
public class Elevator implements Loadable, Movable, Updateable, Orderable {

    //instance variables
    private int idleTime = 0;
    private int id;
    private static final int maxCapacity = 10;
    private int currCap = 0;
    private int currPos = 1;
    private Movable movable;
    private Updateable updateable;
    private Orderable orderable;
    private Requestable requestable;
    private boolean doorsOpen = false;
    private ElevatorDisplay.Direction currDir = IDLE;
    private PriorityQueue<Integer> requests = new PriorityQueue<Integer>();
    private ArrayList<People> riders = new ArrayList<People>();
    private ArrayList<Integer> floorRequests = new ArrayList<Integer>();
    private ArrayList<Integer> elevatorRequests = new ArrayList<Integer>();


    //constructor
    public Elevator(int num) {
        setId(num);
    }

    //getters and setters
    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int num) {
        idleTime = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int num) {
        try {
            if (num > Main.getNumElev() || num < 1)
                throw new InvalidFloorRequestException(" Elevator id must between 1 and " + Main.getNumElev());
            id = num;
        } catch (InvalidFloorRequestException i) {
            i.getMessage();
        }
    }

    public void setCurrCap(int cap) {
        currCap = cap;
    }

    public void setCurrPos(int num) {
        currPos = num;
    }

    public int getCurrPos() {
        return currPos;
    }

    //add riders as long as there is room
    public void setRiders(People person) throws MaxCapacityException {
        if(currCap == maxCapacity)
            throw new MaxCapacityException("Elevator at capacity, please wait for next available elevator.");
        else {
            riders.add(person);
            setCurrCap(riders.size());
            addElevatorRequests(person.getDestination());
            reqToElev(person.getDestination());
        }
    }

    //get an array from arrayList of riders in elevator
    public People[] getRiders() {
        People[] currRiders = new People[riders.size()];
        int i = 0;
        for (People p : riders) {
            currRiders[i] = p;
            i++;
        }
        return currRiders;
    }
    public String getRidersString() {
        String result = "";
        if (riders.size() == 0)
            result = "No Riders";
        else {
            for (int i = 0; i < riders.size(); i++)
                if (i == riders.size() - 1)
                    result += riders.get(i).getName();
                else {
                    result += riders.get(i).getName() + ", ";
                }
        }
        return result;
    }

    public void setDoorsOpen(boolean status) {
        doorsOpen = status;
    }

    public ElevatorDisplay.Direction getCurrDir() {
        return currDir;
    }

    /*change direction based on request
        left as if statements because there will be no other way to
        get different directions
     */
    public void setCurrDir(int elevNum) {
        if (getRequest(elevNum) == 0) {
            if (idleTime < 20) {
                idleTime++;
                currDir = IDLE;
            } else {
                reqToElev(1);
                currDir = DOWN;
            }
        }
        else if (currPos == 1 && currPos < getRequest(elevNum)) {
            currDir = UP;
            reOrder(getRequests(), elevNum);
        }
        if (getCurrPos() == 20 && currPos > getRequest(elevNum)) {
            currDir = DOWN;
            reOrder(getRequests(), elevNum);
        }
    }

    //get an array from arrayList of requests in queue
    public int[] getRequests() {
        int[] intRequests = new int[requests.size()];
        Iterator<Integer> iterator;
        int i = 0;
        for (iterator = requests.iterator(); iterator.hasNext(); ) {
            intRequests[i] = iterator.next();
            i++;
        }
        return intRequests;
    }

    //set reordered priorityQueue
    public void setRequests(PriorityQueue reqs) {
        requests = reqs;
    }

    //add unique request to queue
    public void reqToElev(int num) {
        if (!requests.contains(num))
            requests.add(num);
    }

    //for logging
    public void addFloorRequests(Integer num) {
        if (!floorRequests.contains(num))
            floorRequests.add(num);
    }

    public String getFloorReqString() {
        String result = "";
        if (floorRequests.size() == 0)
            result = "0";
        else {
            for (int i = 0; i < floorRequests.size(); i++)
                if (i == floorRequests.size() - 1)
                    result += floorRequests.get(i);
                else {
                    result += floorRequests.get(i) + ", ";
                }
        }
        return result;
    }

    public void addElevatorRequests(Integer num) {
        if (!elevatorRequests.contains(num))
            elevatorRequests.add(num);
        System.out.println(formatter.format(LocalTime.now()) + "  Elevator " + id + " Rider Request made for Floor " + num +
                " [Current FLoor Requests: " + getFloorReqString() + "][Current Rider Requests:" +
                getElevReqString() + "]");
    }

    public String getElevReqString() {
        String result = "";
        if (elevatorRequests.size() == 0)
            result = "0";
        else {
            for (int i = 0; i < elevatorRequests.size(); i++)
                if (i == elevatorRequests.size() - 1)
                    result += elevatorRequests.get(i);
                else {
                    result += elevatorRequests.get(i) + ", ";
                }
        }
        return result;
    }

    public void removeNextRequest(int elevNum) {
        if (getCurrPos() == getRequest(elevNum))
            requests.remove(getRequest(elevNum));
    }

    // interface methods

    //change to get middle requests and remove next request
    public int getRequest(int elevNum) {
        requestable = new RequestableFactory().build(elevNum);
        return requestable.getRequest(elevNum);
    }

    public void reOrder(int[] ints, int elevNum) {
        orderable = new OrderableFactory().build(currDir);
        orderable.reOrder(getRequests(), elevNum);
    }

    @Override
    public void move(int elevNum) {
        setCurrDir(elevNum);
        movable = new MovableFactory().build(getCurrDir());
        movable.move(elevNum);
    }

    public void update(int elevNum)  {
        updateable = new UpdateableFactory().build(getRequest(elevNum), elevNum);
        updateable.update(elevNum);
    }

    /*load people off floor to elevator and vice versa, update capacity, riders
        coded to interface in case there could be priority or other kinds of loading
     */
    public void load(int elevNum, ElevatorDisplay.Direction dir) {
        if (riders.size() > 0) {
            for (Iterator<People> iterator = riders.iterator(); iterator.hasNext();) {
                People p = iterator.next();
                if (p.getDestination() == getCurrPos()) {
                    ElevatorController.getInstance().elevToFloor(p, getCurrPos());
                    iterator.remove();
                    System.out.println(formatter.format(LocalTime.now()) + " "+ p.getName() + " has left Elevator " + (elevNum + 1) + " [Riders: " +
                            getRidersString() + "]");
                    setCurrCap(riders.size());
                }
            }
        }
        ElevatorController.getInstance().floorToElev(dir, currPos, elevNum);

        //items are unique in specific req Lists, can only remove once
        if (elevatorRequests.contains(currPos))
            elevatorRequests.remove(elevatorRequests.indexOf(currPos));
        if (floorRequests.contains(currPos))
            floorRequests.remove(floorRequests.indexOf(currPos));
    }

}

