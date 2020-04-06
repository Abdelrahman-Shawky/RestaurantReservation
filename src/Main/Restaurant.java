package Main;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Restaurant {


    private Users users = null;
    private Tables tables = null;
    private Dishes dishes = null;
    private Reservations reservations = null;

//    @XmlElement(name = "Users")
    public Users getUsers() {
        return users;
    }


//    @XmlElement(name = "tables")
    public Tables getTables() {
        return tables;
    }

//    @XmlElement(name = "dishes")
    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

    public void setTabels(Tables tabels) {
        this.tables = tabels;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public Reservations getReservations() {
        return reservations;
    }
}

