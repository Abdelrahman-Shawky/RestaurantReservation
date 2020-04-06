package Logic;

import Main.Restaurant;
import Main.Table;
import Main.Tables;
import javafx.scene.control.Tab;

import java.util.Collections;
import java.util.List;

public class Reserving extends Table implements Comparable<Reserving>{

    private boolean reserved;

    public Reserving(int number, int numOfSeats, boolean smoking, boolean reserved) {
        this.reserved = reserved;
        setNumber(number);
        setNumOfSeats(numOfSeats);
        setSmoking(smoking);
    }

    @Override
    public int compareTo(Reserving o) {
        return this.getNumOfSeats() - o.getNumOfSeats();
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
