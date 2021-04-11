package main.java.projectCode;

//tell requestable how to get next request
public class RequestableFactory {

    public Requestable build(int elevNum) {
        switch(ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getCurrDir()) {
            case UP :
                return new UpRequestableImpl();
            case DOWN :
                return new DownRequestableImpl();
            default :
                return new NullRequestableImpl();
        }
    }
}
