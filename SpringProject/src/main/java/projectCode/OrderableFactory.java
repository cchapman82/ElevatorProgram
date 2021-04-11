package main.java.projectCode;

import main.java.gui.ElevatorDisplay;

//tell orderable to order priority queue natural or reverse order
public class OrderableFactory {

    public Orderable build(ElevatorDisplay.Direction dir) {

        switch (dir) {
            case UP:
                return new UpOrderableImpl();
            default: DOWN:
                return new DownOrderableImpl();
        }
    }
}
