package Logic;

import Main.Restaurant;
import Main.User;

import java.util.HashMap;

public class Authentication {

    private HashMap usersAccounts;
    private Restaurant restaurant ;


    public boolean validate(String username,String password)
    {
        boolean result = false;

        String returnedPassword = (String) usersAccounts.get(username);
        if(returnedPassword != null)
            result = returnedPassword.equals(password);

        return result;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        usersAccounts = new HashMap();
        for(User user : restaurant.getUsers().getUser())
        {
            usersAccounts.put(user.getUsername(),user.getPassword());
        }
    }
}
