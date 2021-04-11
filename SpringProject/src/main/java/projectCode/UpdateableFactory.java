package main.java.projectCode;

//tell updateable to move or load
public class UpdateableFactory {

    public Updateable build(Integer reqNum, int elevNum) {
        int num  = reqNum;
        if (reqNum.equals(ElevatorController.getInstance().getMultipleElevators(elevNum).getElevator().getCurrPos()))
            num = 1000;
        switch(num) {
            case 0 :
                return new SwitchDirectionUpdateImpl();
            case 1000 :
                return new LoadUpdateableImpl();
            default :
                return new MoveUpdateableImpl();
        }
    }
}
