package Food;
import Logic.Food;
import Main.Dish;

public class MainCourse extends Dish {

    private double priceAfterTax;
//    private int count;
    private double totalPrice;



    public MainCourse(String name,int count,double price) {
        setName(name);
//        this.count = count;
        setCount(count);
        setPrice(price);
        this.totalPrice = 0;
        this.priceAfterTax = 0;
    }

    public void setPricePlus() {
        this.totalPrice += getPrice();
        this.priceAfterTax = this.totalPrice*1.15;
    }

    public void setPriceMinus() {
        this.totalPrice -= getPrice();
        this.priceAfterTax = this.totalPrice*1.15;
    }

    public double getPriceAfterTax() {
        return priceAfterTax;
    }


    public void setPriceAfterTax(double priceAfterTax) {
        this.priceAfterTax = priceAfterTax;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
