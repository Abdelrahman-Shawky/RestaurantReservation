package Main;

import GUI.*;
import Logic.Reserve;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class Main extends Application {

    private static Restaurant restaurant;
    private static Reserve reserve;

    public static void main(String[] args) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        restaurant = (Restaurant)unmarshaller.unmarshal(new File("input.xml"));

        reserve = new Reserve(restaurant.getTables().getTable().size(),restaurant);
        reserve.setReservations(restaurant.getReservations()); //append



	launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setTitle("Restaurant");

        Login login = new Login(primaryStage);
        ManagerScene managerScene = new ManagerScene(primaryStage);
        CustomerScene customerScene = new CustomerScene(primaryStage);
        TotalPriceScene totalPriceScene = new TotalPriceScene(primaryStage);
        ChefScene chefScene = new ChefScene(primaryStage);
        WaiterScene waiterScene = new WaiterScene(primaryStage);
        SignUp signUpScene = new SignUp(primaryStage);

        login.setRestaurant(restaurant);
        login.setManagerScene(managerScene);
        login.setCustomerScene(customerScene);
        login.setChefScene(chefScene);
        login.setWaiterScene(waiterScene);
        login.setSignUpScene(signUpScene);

        customerScene.setRestaurant(restaurant);
        customerScene.setReserve(reserve);
        customerScene.setLogin(login);
        customerScene.setTotalPriceScene(totalPriceScene);

        totalPriceScene.setReserve(reserve);
        totalPriceScene.setCustomerScene(customerScene);
        totalPriceScene.setLoginScene(login);

        managerScene.setRestaurant(restaurant);
//        managerScene.setReservations(restaurant.getReservations());
        managerScene.setLogin(login);

        chefScene.setRestaurant(restaurant);
        chefScene.setLogin(login);

        waiterScene.setRestaurant(restaurant);
        waiterScene.setLogin(login);

        reserve.setTotalPriceScene(totalPriceScene);
        reserve.setManagerScene(managerScene);
        reserve.setChefScene(chefScene);
        reserve.setWaiterScene(waiterScene);

        signUpScene.setRestaurant(restaurant);
        signUpScene.setLoginScene(login);

        login.prepareScene();
//        managerScene.prepareScene();
//        waiterScene.prepareScene();
//        chefScene.prepareScene();
//        customerScene.prepareScene();
//        signUpScene.prepareScene();

        primaryStage.centerOnScreen();
        primaryStage.setScene(login.getScene());
//        primaryStage.setFullScreen(true);
        primaryStage.show();

    }


}
