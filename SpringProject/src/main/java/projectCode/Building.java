package main.java.projectCode;

/*Building is a singleton that is only here to have the elevator controller and the floors in the building.
  The building needs to know how many floors it has and how many elevators the building has to get an instance of
  the elevator controller.
  It needs to have a container for the floors as well.
 */
public class Building {

    //instance variables
    private int numFls;
    private Floor[] floors;

    //Singleton constructor
    private static final Building building = new Building();

    //get the singleton object
    public static Building getInstance() {
        return building;
    }

    //initialize floor array
    public void makeFloors(int numFloors) {
        System.out.println("Making " + numFloors + " floors.");
        numFls = numFloors;
        floors = new Floor[numFloors];
        for (int i = 0, j = 1; i < numFloors; i++, j++)
            floors[i] = new Floor(j);
    }

    //Get number of floors in building
    public int getFls() {
        return numFls;
    }

    //gain access to floor object
    public Floor getFloor(int num) {
        return floors[num - 1];
    }

}
