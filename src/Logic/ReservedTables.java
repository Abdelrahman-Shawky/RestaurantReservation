package Logic;
import Food.MainCourse;
import Food.Appetizer;
import Food.Dessert;
import Main.Dish;

import java.util.List;

public class ReservedTables extends Reserving{

    private String name;
//    private Dish grilledChicken;
//    private Dish beefSteak;
//    private Dish mushroomSoup;
//    private Dish greekSalad;
//    private Dish friedPotatoes;
//    private Dish applePie;
//    private Dish moltenCake;
    private double totalPriceBeforeTax;
    private double totalPriceAfterTax;
    private List<MainCourse> mainCoursesMenu;
    private List<Appetizer> appetizerMenu;
    private List<Dessert> dessertMenu;

    public ReservedTables(int number, int numOfSeats, boolean smoking, boolean reserved, String name, List<MainCourse> mainCoursesMenu, List<Appetizer> appetizerMenu, List<Dessert> dessertMenu ) {
        super(number, numOfSeats, smoking, reserved);
        this.name = name;
//        this.grilledChicken = grilledChicken;
//        this.beefSteak = beefSteak;
//        this.mushroomSoup = mushroomSoup;
//        this.greekSalad = greekSalad;
//        this.friedPotatoes = friedPotatoes;
//        this.applePie = applePie;
//        this.moltenCake = moltenCake;
        this.mainCoursesMenu = mainCoursesMenu;
        this.appetizerMenu = appetizerMenu;
        this.dessertMenu = dessertMenu;
        this.totalPriceBeforeTax=0;
        this.totalPriceAfterTax=0;

        for(MainCourse mainCourse : mainCoursesMenu)
        {
            this.totalPriceAfterTax += mainCourse.getPriceAfterTax();
            this.totalPriceBeforeTax += mainCourse.getTotalPrice();
        }
        for(Appetizer appetizer : appetizerMenu)
        {
            this.totalPriceAfterTax += appetizer.getPriceAfterTax();
            this.totalPriceBeforeTax += appetizer.getTotalPrice();
        }
        for(Dessert dessert : dessertMenu)
        {
            this.totalPriceAfterTax += dessert.getPriceAfterTax();
            this.totalPriceBeforeTax += dessert.getTotalPrice();
        }
    }

    public String getName() {
        return name;
    }



    public double getTotalPriceBeforeTax() {
        return totalPriceBeforeTax;
    }

    public double getTotalPriceAfterTax() {
        return totalPriceAfterTax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPriceBeforeTax(double totalPriceBeforeTax) {
        this.totalPriceBeforeTax = totalPriceBeforeTax;
    }

    public void setTotalPriceAfterTax(double totalPriceAfterTax) {
        this.totalPriceAfterTax = totalPriceAfterTax;
    }

    public List<MainCourse> getMainCoursesMenu() {
        return mainCoursesMenu;
    }

    public void setMainCoursesMenu(List<MainCourse> mainCoursesMenu) {
        this.mainCoursesMenu = mainCoursesMenu;
    }

    public List<Appetizer> getAppetizerMenu() {
        return appetizerMenu;
    }

    public void setAppetizerMenu(List<Appetizer> appetizerMenu) {
        this.appetizerMenu = appetizerMenu;
    }

    public List<Dessert> getDessertMenu() {
        return dessertMenu;
    }

    public void setDessertMenu(List<Dessert> dessertMenu) {
        this.dessertMenu = dessertMenu;
    }
}
