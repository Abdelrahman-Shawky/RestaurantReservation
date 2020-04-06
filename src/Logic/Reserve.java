package Logic;

import GUI.*;
import Main.*;
import Food.MainCourse;
import Food.Appetizer;
import Food.Dessert;
import com.sun.deploy.util.Waiter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reserve {


    private int count;
    private int reserveTemp;
    private TotalPriceScene totalPriceScene;
    private Restaurant restaurant;
    private Reservations reservations;
    private ManagerScene managerScene;
    private ChefScene chefScene;
    private WaiterScene waiterScene;
    private List<Reserving> reservings = new ArrayList<>();


    public Reserve(int count, Restaurant restaurant) {
        this.count = count;
        this.restaurant = restaurant;
        for (int i = 0; i < count; i++) {
            Reserving reserving = new Reserving(restaurant.getTables().getTable().get(i).getNumber(),
                    restaurant.getTables().getTable().get(i).getNumOfSeats(),
                    restaurant.getTables().getTable().get(i).isSmoking(),
                    false);

            if (restaurant.getReservations() != null) { //initial run
                for (Reservation reservation : restaurant.getReservations().getReservation()) {
                    if (reserving.getNumber() == reservation.getTableNumber())
                        reserving.setReserved(true); // to set reserved tables
                }
            }
            reservings.add(reserving);
        }

        Collections.sort(reservings);
    }

    public boolean checkAvailabile(int numberOfSeats, int x) {

        boolean res = false;
        switch (x) {
            case 1:
                for (int i = 0; i < count; i++) {
                    if ((reservings.get(i).getNumOfSeats() >= numberOfSeats) && reservings.get(i).isSmoking() && !reservings.get(i).isReserved()) {
                        reserveTemp = i;
                        System.out.println("Reserve?");
                        res = true;
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < count; i++) {
                    if ((reservings.get(i).getNumOfSeats() >= numberOfSeats) && !reservings.get(i).isSmoking() && !reservings.get(i).isReserved()) {
                        reserveTemp = i;
                        System.out.println("Reserve?");
                        res = true;
                        break;
                    }
                }
                break;
        }
        return res;

    }

    public void makeReservation(String name, int numOfSeats, List<MainCourse> mainCoursesMenu, List<Appetizer> appetizerMenu, List<Dessert> dessertMenu ) {

        ReservedTables newReservation = new ReservedTables(reservings.get(reserveTemp).getNumber(), numOfSeats, reservings.get(reserveTemp).isSmoking(),
                true, name, mainCoursesMenu,appetizerMenu,dessertMenu);

        totalPriceScene.setReservedTables(newReservation);

    }

    public void finalReserve(ReservedTables newReservation) throws JAXBException {

        reservings.get(reserveTemp).setReserved(true);

        Reservation res = new Reservation();
        res.setName(newReservation.getName());
        res.setTotalPriceAfterTax(newReservation.getTotalPriceAfterTax());
        res.setTableNumber(newReservation.getNumber());
        res.setNumOfSeats(newReservation.getNumOfSeats());

        List<Food> foodList = new ArrayList<>();

        System.out.println(newReservation.getMainCoursesMenu().size());
        System.out.println(newReservation.getAppetizerMenu().size());
        System.out.println(newReservation.getDessertMenu().size());

        for(MainCourse mainCourse : newReservation.getMainCoursesMenu())
        {
            Food food = new Food();
            food.setName(mainCourse.getName());
            food.setCount(mainCourse.getCount());
            foodList.add(food);
        }
        for(Appetizer appetizer : newReservation.getAppetizerMenu())
        {
            Food food = new Food();
            food.setName(appetizer.getName());
            food.setCount(appetizer.getCount());
            foodList.add(food);
        }
        for(Dessert dessert : newReservation.getDessertMenu())
        {
            Food food = new Food();
            food.setName(dessert.getName());
            food.setCount(dessert.getCount());
            foodList.add(food);
        }


        res.setFood(foodList);

        if (restaurant.getReservations() != null)
            restaurant.getReservations().getReservation().add(res);
        else {
            Reservations reservation2 = new Reservations();
            List<Reservation> reservationsList = new ArrayList<Reservation>();
            reservationsList.add(res);
            reservation2.setReservation(reservationsList);
            restaurant.setReservations(reservation2);
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(restaurant, new File("input.xml"));

        setRestaurant(restaurant);
        managerScene.setRestaurant(restaurant);
        chefScene.setRestaurant(restaurant);
        waiterScene.setRestaurant(restaurant);

    }

    public void setTotalPriceScene(TotalPriceScene totalPriceScene) {
        this.totalPriceScene = totalPriceScene;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public void setManagerScene(ManagerScene managerScene) {
        this.managerScene = managerScene;
    }

    public void setChefScene(ChefScene chefScene) {
        this.chefScene = chefScene;
    }

    public void setWaiterScene(WaiterScene waiterScene) {
        this.waiterScene = waiterScene;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
