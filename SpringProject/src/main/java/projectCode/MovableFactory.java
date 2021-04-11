package main.java.projectCode;

import main.java.gui.ElevatorDisplay;

//tell movable how to move
public class MovableFactory {

    public Movable build(ElevatorDisplay.Direction currDirection) {
        switch (currDirection) {
            case UP :
                return new UpMoveableImpl();
            case DOWN :
                return new DownMoveableImpl();
            default :
                return new NullMoveableImpl();
        }
    }
}
