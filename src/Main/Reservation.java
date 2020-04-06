package Main;

import Logic.Food;

import javax.xml.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;

@XmlRootElement(name = "reservation")
@XmlType(propOrder = { "name","tableNumber","totalPriceAfterTax","numOfSeats","food"})
//@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation {

    private String name;
    private int tableNumber;
    private List<Food> food;
    private double totalPriceAfterTax;
    private int numOfSeats;


    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "tableNumber")
    public int getTableNumber() {
        return tableNumber;
    }

    @XmlElement(name = "totalPriceAfterTax")
    public double getTotalPriceAfterTax() {
        return totalPriceAfterTax;
    }

    @XmlElement(name = "food")
    public List<Food> getFood() {
        return food;
    }

    @XmlElement(name = "numOfSeats")
    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setTotalPriceAfterTax(double totalPriceAfterTax) {
        this.totalPriceAfterTax = totalPriceAfterTax;
    }

}
