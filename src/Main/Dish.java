package Main;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "dish")
@XmlType(propOrder = { "name","price","type"})

//@XmlAccessorType(XmlAccessType.FIELD)
public class Dish {

    private String name;
    private double price;
    private String type;
    private int count;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "price")
    public double getPrice() {
        return price;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    @XmlTransient
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCountPlus() {
        this.count++;
    }

    public void setCountMinus(){
        this.count--;
    }
}
