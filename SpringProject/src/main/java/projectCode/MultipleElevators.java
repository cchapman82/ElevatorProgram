package main.java.projectCode;

/*  Threads to handle requests, while still being able to get new requests
    concurrently
 */
public class MultipleElevators  extends Thread {

    // instance variables

    private int elev;
    private static int time;
    private Elevator elevator;
    private boolean update = false;

    public MultipleElevators(Elevator e){
        setElevator(e);
        setElev(e.getId()-1);
    }
    //getters and setters


    public void setUpdate ( boolean status){
        update = status;
    }
    public void setElev ( int num){
        elev = num;
    }

    public void setElevator (Elevator e){
        elevator = e;
    }
    public Elevator getElevator () {
        return elevator;
    }

    //set the number of update calls
    public static void setTime ( int t){
        time = t;
    }

    //make one update call to current thread's elevator, once per second
    @Override
    public void run () {
        for (int i = 0; i < time; i++) {
            //try {
                getElevator().update(elev);
            /*} catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
