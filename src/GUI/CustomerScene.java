package GUI;

import Logic.Food;
import Logic.Reserve;
import Main.Dish;
import Main.Restaurant;
import Food.MainCourse;
import Food.Appetizer;
import Food.Dessert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class CustomerScene extends ParentScene {

    private Stage stage;
    private Scene scene;
    private Login login;
    private Restaurant restaurant;
    private Reserve reserve;
    private TotalPriceScene totalPriceScene;
    private String name;
    private List<MainCourse> mainCourseMenu = new ArrayList<>();
    private List<Appetizer> appetizerMenu = new ArrayList<>();
    private List<Dessert> dessertMenu = new ArrayList<>();

    public CustomerScene(Stage stage){
        super(stage);
        this.stage = stage;
    }

    public void prepareScene(){

        mainCourseMenu.clear();
        appetizerMenu.clear();
        dessertMenu.clear();

        System.out.println(restaurant.getDishes().getDish().size());
        for (Dish dish : restaurant.getDishes().getDish())
        {
            if(dish.getType().equalsIgnoreCase("main_course"))
            {
                MainCourse mainCourse = new MainCourse(dish.getName(),0,dish.getPrice());
                mainCourseMenu.add(mainCourse);
            }
            else if (dish.getType().equalsIgnoreCase("appetizer"))
            {
                Appetizer appetizer = new Appetizer(dish.getName(),0,dish.getPrice());
                appetizerMenu.add(appetizer);
            }
            else if (dish.getType().equalsIgnoreCase("desert") || dish.getType().equalsIgnoreCase("dessert"))
            {
                Dessert dessert = new Dessert(dish.getName(),0,dish.getPrice());
                dessertMenu.add(dessert);
            }
        }


//        MainCourse grilledChicken = new MainCourse("Grilled Chicken",0,75);
//        MainCourse beefSteak = new MainCourse("Beef Steak",0,80);
//        MainCourse mushroomSoup = new MainCourse("Mushroom Soup",0,60);
//
//        Appetizer greekSalad = new Appetizer("Greek Salad",0,35);
//        Appetizer friedPotatoes = new Appetizer("Fried Potatoes",0,30);
//
//        Dessert applePie = new Dessert("Apple Pie",0,50);
//        Dessert moltenCake = new Dessert("Molten Cake",0,60);


        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(50);
        grid.setPadding(new Insets(25));

        BorderPane borderPane = new BorderPane();

        Label noOfSeats = new Label("Number of Seats");
        noOfSeats.setFont(new Font("Arial",25));
        noOfSeats.setTextFill(Color.WHITE);
        grid.add(noOfSeats,0,0);

        ChoiceBox<Integer> noOfSeatsValue = new ChoiceBox<>();
        for(int i=1;i<13;i++)
        noOfSeatsValue.getItems().add(i);
        noOfSeatsValue.getSelectionModel().selectFirst();

        grid.add(noOfSeatsValue,1,0);

        RadioButton smoking = new RadioButton("Smoking");
        smoking.setFont(new Font("Arial",25));
        smoking.setTextFill(Color.WHITE);
        grid.add(smoking,0,1);

        RadioButton nonSmoking = new RadioButton("Non Smoking");
        nonSmoking.setFont(new Font("Arial",25));
        nonSmoking.setTextFill(Color.WHITE);
        grid.add(nonSmoking,1,1);

        ToggleGroup group = new ToggleGroup();
        smoking.setToggleGroup(group);
        nonSmoking.setToggleGroup(group);
        smoking.setSelected(true);

        Button checkAvailability = new Button("Check Availability");
        checkAvailability.setFont(new Font("Arial",25));
        checkAvailability.setStyle("-fx-background-color: blue");
        checkAvailability.setTextFill(Color.WHITE);
        grid.add(checkAvailability,0,2);

        borderPane.setCenter(grid);
        BorderPane.setAlignment(grid, Pos.CENTER);
        BorderPane.setMargin(grid, new Insets(12,12,12,12));

        Button confirmReservation = new Button("Check Out");
        confirmReservation.setFont(new Font("Arial",25));
        confirmReservation.setStyle("-fx-background-radius: 15;");
        confirmReservation.setDisable(true);

        Button exit = new Button("Exit");
        exit.setFont(new Font("Arial",25));
        exit.setStyle("-fx-background-radius: 15;");

        Button resetReservation = new Button("Reset Reservation");
        resetReservation.setFont(new Font("Arial",25));
        resetReservation.setStyle("-fx-background-radius: 15;");

        Button logOut = new Button("Log Out");
        logOut.setFont(new Font("Arial",25));
        logOut.setTextFill(Color.WHITE);
        logOut.setStyle("-fx-background-radius: 15; -fx-background-color: red;");

        Label availability = new Label();
        grid.add(availability, 1, 2);

        try {
            FileInputStream input = new FileInputStream("food.jpg");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.fitWidthProperty().bind(stage.widthProperty());
            imageView.fitHeightProperty().bind(stage.heightProperty());
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            borderPane.setBackground(background);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        grid.setStyle("-fx-background-color: rgba(0, 100, 100, 0.8); -fx-background-radius: 15;");

        HBox hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(25));
        hBox.getChildren().addAll(resetReservation,confirmReservation,exit,logOut);

        checkAvailability.setOnAction( e -> {
            int num = noOfSeatsValue.getValue();
            boolean res;
            if(smoking.isSelected())
            {
                res=reserve.checkAvailabile(num,1);
            }
            else
            {
                res=reserve.checkAvailabile(num,2);
            }

            if(res) {
                confirmReservation.setDisable(false);
                checkAvailability.setDisable(true);
                noOfSeatsValue.setDisable(true);
                smoking.setDisable(true);
                nonSmoking.setDisable(true);

                availability.setText("Available");
                availability.setTextFill(Color.WHITE);
                availability.setFont(new Font("Arial",24));

                ToggleButton mainCourse = new ToggleButton("Main Course");
                mainCourse.setFont(new Font("Arial", 24));
                mainCourse.setStyle("-fx-background-color: blue; -fx-background-radius: 15;");
                mainCourse.setTextFill(Color.WHITE);
                grid.add(mainCourse, 0, 3);

                ToggleButton appetizer = new ToggleButton("Appetizer");
                appetizer.setFont(new Font("Arial", 24));
                appetizer.setStyle("-fx-background-color: blue; -fx-background-radius: 15;");
                appetizer.setTextFill(Color.WHITE);
                grid.add(appetizer, 1, 3);

                ToggleButton dessert = new ToggleButton("Dessert");
                dessert.setFont(new Font("Arial", 24));
                dessert.setStyle("-fx-background-color: blue; -fx-background-radius: 15;");
                dessert.setTextFill(Color.WHITE);
                grid.add(dessert, 2, 3);

                ToggleGroup buttonGroup = new ToggleGroup();
                mainCourse.setToggleGroup(buttonGroup);
                appetizer.setToggleGroup(buttonGroup);
                dessert.setToggleGroup(buttonGroup);

                Label label1 = new Label();
                label1.setFont(new Font("Arial", 24));
                label1.setTextFill(Color.WHITE);
                grid.add(label1, 0, 4);

                Label label2 = new Label();
                label2.setFont(new Font("Arial", 24));
                label2.setTextFill(Color.WHITE);
                grid.add(label2, 0, 5);

                Label label3 = new Label();
                label3.setFont(new Font("Arial", 24));
                label3.setTextFill(Color.WHITE);
                grid.add(label3, 0, 6);

                Button minus1 = new Button("-");
                grid.add(minus1,1,4);
                minus1.setVisible(false);
                minus1.setFont(new Font("Arial", 25));
                minus1.setStyle("-fx-background-color: red; -fx-background-radius: 15;");
                minus1.setTextFill(Color.WHITE);
                grid.setHalignment(minus1, HPos.CENTER);
                minus1.setMaxSize(40,20);

                Button minus2 = new Button("-");
                grid.add(minus2,1,5);
                minus2.setVisible(false);
                minus2.setFont(new Font("Arial", 25));
                minus2.setStyle("-fx-background-color: red; -fx-background-radius: 15;");
                minus2.setTextFill(Color.WHITE);
                grid.setHalignment(minus2, HPos.CENTER);
                minus2.setMaxSize(40,20);

                Button minus3 = new Button("-");
                grid.add(minus3,1,6);
                minus3.setVisible(false);
                minus3.setFont(new Font("Arial", 25));
                minus3.setStyle("-fx-background-color: red; -fx-background-radius: 15;");
                minus3.setTextFill(Color.WHITE);
                grid.setHalignment(minus3, HPos.CENTER);
                minus3.setMaxSize(40,20);

                Button plus1 = new Button("+");
                grid.add(plus1,2,4);
                plus1.setVisible(false);
                plus1.setFont(new Font("Arial", 25));
                plus1.setStyle("-fx-background-color: red; -fx-background-radius: 15;");
                plus1.setTextFill(Color.WHITE);
                grid.setHalignment(plus1, HPos.CENTER);
                plus1.setMaxSize(40,20);

                Button plus2 = new Button("+");
                grid.add(plus2,2,5);
                plus2.setVisible(false);
                plus2.setFont(new Font("Arial", 25));
                plus2.setStyle("-fx-background-color: red; -fx-background-radius: 15;");
                plus2.setTextFill(Color.WHITE);
                grid.setHalignment(plus2, HPos.CENTER);
                plus2.setMaxSize(40,20);

                Button plus3 = new Button("+");
                grid.add(plus3,2,6);
                plus3.setVisible(false);
                plus3.setFont(new Font("Arial", 25));
                plus3.setStyle("-fx-background-color: red; -fx-background-radius: 15;");
                plus3.setTextFill(Color.WHITE);
                grid.setHalignment(plus3, HPos.CENTER);
                plus3.setMaxSize(40,20);

                Label price1 = new Label();
                grid.add(price1,3,4);
                price1.setFont(new Font("Arial", 24));
                price1.setTextFill(Color.WHITE);
                price1.setVisible(false);

                Label price2 = new Label();
                grid.add(price2,3,5);
                price2.setFont(new Font("Arial", 24));
                price2.setTextFill(Color.WHITE);
                price1.setVisible(false);

                Label price3 = new Label();
                grid.add(price3,3,6);
                price3.setFont(new Font("Arial", 24));
                price3.setTextFill(Color.WHITE);
                price1.setVisible(false);

                Label total = new Label("Total Price = ");
                total.setFont(new Font("Arial", 24));
                total.setTextFill(Color.WHITE);
                grid.add(total,0,7);
                total.setVisible(false);

                Label totalValue = new Label();
                totalValue.setFont(new Font("Arial", 24));
                totalValue.setTextFill(Color.WHITE);
                grid.add(totalValue,1,7);
                totalValue.setText("0");
                totalValue.setVisible(false);


//                Label info1 = new Label();
//                info1.setVisible(false);
//                info1.setTextFill(Color.WHITE);
//                info1.setFont(new Font("Arial", 26));
//                info1.setWrapText(true);
//                info1.setPrefWidth(800);
//                grid.add(info1,4,4);
//
//                Label info2 = new Label();
//                info2.setVisible(false);
//                info2.setTextFill(Color.WHITE);
//                info2.setFont(new Font("Arial", 26));
//                info2.setWrapText(true);
//                info2.setPrefWidth(800);
//                grid.add(info2,4,5);
//
//                Label info3 = new Label();
//                info3.setVisible(false);
//                info3.setTextFill(Color.WHITE);
//                info3.setFont(new Font("Arial", 26));
//                info3.setWrapText(true);
//                info3.setPrefWidth(800);
//                grid.add(info3,4,6);

                mainCourse.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {


                        label1.setText(mainCourseMenu.get(0).getName() + ": " + mainCourseMenu.get(0).getPrice() + " LE");
                        label2.setText(mainCourseMenu.get(1).getName() + ": " + mainCourseMenu.get(1).getPrice() + " LE");
                        label3.setText(mainCourseMenu.get(2).getName() + ": " + mainCourseMenu.get(2).getPrice() + " LE");

                        price1.setText("x" + mainCourseMenu.get(0).getCount() + " " + "Price = " + mainCourseMenu.get(0).getTotalPrice());
                        price2.setText("x" + mainCourseMenu.get(1).getCount() + " " + "Price = " + mainCourseMenu.get(1).getTotalPrice());
                        price3.setText("x" + mainCourseMenu.get(2).getCount() + " " + "Price = " + mainCourseMenu.get(2).getTotalPrice());

                        minus1.setVisible(true);
                        minus2.setVisible(true);
                        minus3.setVisible(true);
                        plus1.setVisible(true);
                        plus2.setVisible(true);
                        plus3.setVisible(true);
                        price1.setVisible(true);
                        price2.setVisible(true);
                        price3.setVisible(true);
                        total.setVisible(true);
                        totalValue.setVisible(true);

//                        info1.setText("Perfectly Cooked Boneless Chicken Breasts with Yoghurt, Olive Oil and Sesame Seeds.");
//                        info2.setText("Flavorful Steak Cooked with your Preferred Way (Well done, Medium or Medium Rare).");
//                        info3.setText("Creamy Mushroom Soup with Crumbled Croutons.");

//                        info1.setVisible(true);
//                        info2.setVisible(true);
//                        info3.setVisible(true);

                        minus1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if(mainCourseMenu.get(0).getTotalPrice()!=0) {
                                    mainCourseMenu.get(0).setPriceMinus();
                                    mainCourseMenu.get(0).setCountMinus();
                                    price1.setText("x" + mainCourseMenu.get(0).getCount() + " " + "Price = " + mainCourseMenu.get(0).getTotalPrice());
                                    totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                    + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                                }
                            }
                        });

                        plus1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mainCourseMenu.get(0).setPricePlus();
                                mainCourseMenu.get(0).setCountPlus();
                                price1.setText("x" + mainCourseMenu.get(0).getCount()  + " " + "Price = " + mainCourseMenu.get(0).getTotalPrice());
                                totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                        + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                            }
                        });

                        minus2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if(mainCourseMenu.get(1).getTotalPrice()!=0) {
                                    mainCourseMenu.get(1).setPriceMinus();
                                    mainCourseMenu.get(1).setCountMinus();
                                    price2.setText("x" + mainCourseMenu.get(1).getCount() + " " + "Price = " + mainCourseMenu.get(1).getTotalPrice());
                                    totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                            + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                                }
                            }
                        });

                        plus2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mainCourseMenu.get(1).setPricePlus();
                                mainCourseMenu.get(1).setCountPlus();
                                price2.setText("x" + mainCourseMenu.get(1).getCount() + " " + "Price = " + mainCourseMenu.get(1).getTotalPrice());
                                totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                        + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));

                            }
                        });

                        minus3.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if(mainCourseMenu.get(2).getTotalPrice()!=0) {
                                    mainCourseMenu.get(2).setPriceMinus();
                                    mainCourseMenu.get(2).setCountMinus();
                                    price3.setText("x" + mainCourseMenu.get(2).getCount() + " " + "Price = " + mainCourseMenu.get(2).getTotalPrice());
                                    totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                            + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                                }
                            }
                        });

                        plus3.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mainCourseMenu.get(2).setPricePlus();
                                mainCourseMenu.get(2).setCountPlus();
                                price3.setText("x" + mainCourseMenu.get(2).getCount() + " " + "Price = " + mainCourseMenu.get(2).getTotalPrice());
                                totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                        + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                            }
                        });
                    }
                });

                appetizer.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        label1.setText(appetizerMenu.get(0).getName() + ": " + appetizerMenu.get(0).getPrice() + " LE");
                        label2.setText(appetizerMenu.get(1).getName() + ": " + appetizerMenu.get(1).getPrice() + " LE");
                        label3.setText(" ");
                        price1.setText("x" + appetizerMenu.get(0).getCount() + " " + "Price = " + appetizerMenu.get(0).getTotalPrice());
                        price2.setText("x" + appetizerMenu.get(1).getCount() + " " + "Price = " + appetizerMenu.get(1).getTotalPrice());

                        minus1.setVisible(true);
                        minus2.setVisible(true);
                        plus1.setVisible(true);
                        plus2.setVisible(true);
                        minus3.setVisible(false);
                        plus3.setVisible(false);
                        price1.setVisible(true);
                        price2.setVisible(true);
                        price3.setVisible(false);
                        total.setVisible(true);
                        totalValue.setVisible(true);

//                        info1.setText("Iceberg Lettuce, Tomatoes, Cucumbers, Feta Cheese, Black Olives, Green Peppers.");
//                        info2.setText("French Fries with our Special Spices and Sauce.");

//                        info1.setVisible(true);
//                        info2.setVisible(true);
//                        info3.setVisible(false);


                        minus1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if(appetizerMenu.get(0).getTotalPrice()!=0) {
                                    appetizerMenu.get(0).setPriceMinus();
                                    appetizerMenu.get(0).setCountMinus();
                                    price1.setText("x" + appetizerMenu.get(0).getCount() + " " + "Price = " + appetizerMenu.get(0).getTotalPrice());
                                    totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                            + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));

                                }
                            }
                        });

                        plus1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                appetizerMenu.get(0).setPricePlus();
                                appetizerMenu.get(0).setCountPlus();
                                price1.setText("x" + appetizerMenu.get(0).getCount() + " " + "Price = " + appetizerMenu.get(0).getTotalPrice());
                                totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                        + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                            }
                        });

                        minus2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if(appetizerMenu.get(1).getTotalPrice()!=0) {
                                    appetizerMenu.get(1).setPriceMinus();
                                    appetizerMenu.get(1).setCountMinus();
                                    price2.setText("x" + appetizerMenu.get(1).getCount() + " " + "Price = " + appetizerMenu.get(1).getTotalPrice());
                                    totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                            + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                                }
                            }
                        });

                        plus2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                appetizerMenu.get(1).setPricePlus();
                                appetizerMenu.get(1).setCountPlus();
                                price2.setText("x" + appetizerMenu.get(1).getCount() + " " + "Price = " + appetizerMenu.get(1).getTotalPrice());
                                totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                        + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                            }
                        });

                    }
                });

                dessert.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        label1.setText(dessertMenu.get(0).getName() + ": " + dessertMenu.get(0).getPrice() + " LE");
                        label2.setText(dessertMenu.get(1).getName() + ": " + dessertMenu.get(1).getPrice() + " LE");
                        label3.setText(" ");
                        price1.setText("x" + dessertMenu.get(0).getCount() + " " + "Price = " + dessertMenu.get(0).getTotalPrice());
                        price2.setText("x" + dessertMenu.get(1).getCount() + " " + "Price = " + dessertMenu.get(1).getTotalPrice());

                        minus1.setVisible(true);
                        minus2.setVisible(true);
                        plus1.setVisible(true);
                        plus2.setVisible(true);
                        minus3.setVisible(false);
                        plus3.setVisible(false);
                        price1.setVisible(true);
                        price2.setVisible(true);
                        price3.setVisible(false);
                        total.setVisible(true);
                        totalValue.setVisible(true);

//                        info1.setText("Local Apples in Blend of Spices Baked in a Flaky Pie Crust.");
//                        info2.setText("Warm Molten Chocolate Cake Oozing with Chocolate topped with Vanilla Ice Cream.");

//                        info1.setVisible(true);
//                        info2.setVisible(true);
//                        info3.setVisible(false);


                        minus1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if(dessertMenu.get(0).getTotalPrice()!=0) {
                                    dessertMenu.get(0).setPriceMinus();
                                    dessertMenu.get(0).setCountMinus();
                                    price1.setText("x" + dessertMenu.get(0).getCount() + " " + "Price = " + dessertMenu.get(0).getTotalPrice());
                                    totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                            + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                                }
                            }
                        });

                        plus1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                dessertMenu.get(0).setPricePlus();
                                dessertMenu.get(0).setCountPlus();
                                price1.setText("x" + dessertMenu.get(0).getCount() + " " + "Price = " + dessertMenu.get(0).getTotalPrice());
                                totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                        + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                            }
                        });

                        minus2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if(dessertMenu.get(1).getTotalPrice()!=0) {
                                    dessertMenu.get(1).setPriceMinus();
                                    dessertMenu.get(1).setCountMinus();
                                    price2.setText("x" + dessertMenu.get(1).getCount() + " " + "Price = " + dessertMenu.get(1).getTotalPrice());
                                    totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                            + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                                }
                            }
                        });

                        plus2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                dessertMenu.get(1).setPricePlus();
                                dessertMenu.get(1).setCountPlus();
                                price2.setText("x" + dessertMenu.get(1).getCount() + " " + "Price = " + dessertMenu.get(1).getTotalPrice());
                                totalValue.setText(String.valueOf(mainCourseMenu.get(0).getTotalPrice() + mainCourseMenu.get(1).getTotalPrice() + mainCourseMenu.get(2).getTotalPrice() + appetizerMenu.get(0).getTotalPrice() + appetizerMenu.get(1).getTotalPrice()
                                        + dessertMenu.get(0).getTotalPrice() + dessertMenu.get(1).getTotalPrice()));
                            }
                        });

                    }
                });

                confirmReservation.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Confirmbox confirmReservation = new Confirmbox();
                        Boolean answer = confirmReservation.message("Confirm Reservation","Are You Sure You Want To Make A Reservation ?");
                        if(answer) {
                                reserve.makeReservation(name,noOfSeatsValue.getValue(), mainCourseMenu, appetizerMenu, dessertMenu);
                            totalPriceScene.prepareScene();
                            stage.setScene(totalPriceScene.getScene());
                        }

                    }
                });
            }
            else
            {
                confirmReservation.setDisable(true);
                availability.setText("Not Available");
                availability.setTextFill(Color.WHITE);
                availability.setFont(new Font("Arial",24));
            }

            borderPane.setCenter(grid);
            BorderPane.setAlignment(grid, Pos.CENTER);
            BorderPane.setMargin(grid, new Insets(12,12,12,12));

        });

        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(12,12,12,12));

        resetReservation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Confirmbox confirmReservation = new Confirmbox();
                Boolean answer = confirmReservation.message("Reset Reservation","Are You Sure You Want To Reset Reservation ?");
                if(answer) {
                    prepareScene();
                    stage.setScene(scene);
                }
            }
        });

        exit.setOnAction(e -> {
            closeProgram();
        });

        logOut.setOnAction( e -> {
            boolean answer =  logOut();
            if(answer) {
                login.prepareScene();
                stage.setScene(login.getScene());
            }
        });

        scene = new Scene(borderPane,1900,1000);
    }

    public Scene getScene() {
        stage.centerOnScreen();
        return scene;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public void setTotalPriceScene(TotalPriceScene totalPriceScene) {
        this.totalPriceScene = totalPriceScene;
    }

    public void setName(String name) {
        this.name = name;
    }
}
