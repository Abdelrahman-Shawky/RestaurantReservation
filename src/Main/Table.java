package Main;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "table")
@XmlType(propOrder = { "number","numOfSeats","smoking"})
//@XmlAccessorType(XmlAccessType.FIELD)
public class Table {

    private int number;
    private int numOfSeats;
    private boolean smoking;
//    private boolean reserved;


    @XmlElement(name = "number")
    public int getNumber() {
        return number;
    }

    @XmlElement(name = "number_of_seats")
    public int getNumOfSeats() {
        return numOfSeats;
    }

    @XmlElement(name = "smoking")
    public boolean isSmoking() {
        return smoking;
    }

//    @XmlElement(name = "reserved")
//    public boolean isReserved() {
//        return reserved;
//    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

//    public void setReserved(boolean reserved) {
//        this.reserved = reserved;
//    }

//    @Override
//    public int compareTo(Table o) {
//        return this.getNumOfSeats() - o.getNumOfSeats();
//    }

//    public Table(int number, int numOfSeats, boolean smoking) {
//        this.number = number;
//        this.numOfSeats = numOfSeats;
//        this.smoking = smoking;
//    }
}
