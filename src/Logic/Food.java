package Logic;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="food")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Food{

    private String name;
    private int count;
    private double price;
    private double totalPrice;


    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "count")
    public int getCount() {
        return count;
    }

    @XmlTransient
    public double getPrice() {
        return price;
    }

    @XmlTransient
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }





}
