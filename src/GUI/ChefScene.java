package GUI;

import Logic.Food;
import Main.Reservation;
import Main.Reservations;
import Main.Restaurant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class ChefScene extends ParentScene{

    private Scene scene;
    private Stage stage;
    private TableView<CustomerTable> table;
    private Restaurant restaurant;
    private Reservations reservations;
    private Login login;
    private List<String> names = new ArrayList<>();
    private int[] count = new int[10];

    public ChefScene(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public void prepareScene() {
        VBox vbox3 = new VBox(5);

        Label label = new Label("WELCOME BACK!");
        Image image = new Image("file:chef.jpg");
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background1 = new Background(background);

        table = new TableView<>();
        table.setPadding(new Insets(25));
        table.setStyle("-fx-background-color: transparent;");

        label.setPadding(new Insets(20,20,20,700));
        vbox3.getChildren().add(label);

        if(restaurant.getReservations()!=null) {

            for (Food food : restaurant.getReservations().getReservation().get(0).getFood()) {
                names.add(food.getName());
            }


            TableColumn<CustomerTable, Integer> seatsColumn = new TableColumn<>("Table Number");
            seatsColumn.setMinWidth(150);
            seatsColumn.setStyle("-fx-font-size: 17; -fx-alignment: CENTER;");
            seatsColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

            TableColumn<CustomerTable, Integer> column1 = new TableColumn<>(names.get(0));
            column1.setMinWidth(200);
            column1.setStyle("-fx-font-size: 17; -fx-alignment: CENTER;");
            column1.setCellValueFactory(new PropertyValueFactory<>("num1"));

            TableColumn<CustomerTable, Integer> column2 = new TableColumn<>(names.get(1));
            column2.setMinWidth(250);
            column2.setStyle("-fx-font-size: 17; -fx-alignment: CENTER;");
            column2.setCellValueFactory(new PropertyValueFactory<>("num2"));

            TableColumn<CustomerTable, Integer> column3 = new TableColumn<>(names.get(2));
            column3.setMinWidth(200);
            column3.setStyle("-fx-font-size: 17; -fx-alignment: CENTER;");
            column3.setCellValueFactory(new PropertyValueFactory<>("num3"));

            TableColumn<CustomerTable, Integer> column4 = new TableColumn<>(names.get(3));
            column4.setMinWidth(200);
            column4.setStyle("-fx-font-size: 17; -fx-alignment: CENTER;");
            column4.setCellValueFactory(new PropertyValueFactory<>("num4"));

            TableColumn<CustomerTable, Integer> column5 = new TableColumn<>(names.get(4));
            column5.setMinWidth(200);
            column5.setStyle("-fx-font-size: 17; -fx-alignment: CENTER;");
            column5.setCellValueFactory(new PropertyValueFactory<>("num5"));

            TableColumn<CustomerTable, Integer> column6 = new TableColumn<>(names.get(5));
            column6.setMinWidth(200);
            column6.setStyle("-fx-font-size: 17; -fx-alignment: CENTER;");
            column6.setCellValueFactory(new PropertyValueFactory<>("num6"));

            TableColumn<CustomerTable, Integer> column7 = new TableColumn<>(names.get(6));
            column7.setMinWidth(200);
            column7.setStyle("-fx-font-size: 17; -fx-alignment: CENTER;");
            column7.setCellValueFactory(new PropertyValueFactory<>("num7"));

            table.getColumns().addAll(seatsColumn, column1, column2, column3, column4, column5, column6, column7);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        }

        Button button = new Button("Display Today's Work!");
        button.setTextFill(Color.RED);
        button.setStyle("-fx-background-radius: 15 ");
        button.setFont(new Font("Arial", 25));

        vbox3.getChildren().add(button);
        vbox3.getChildren().add(new Label("   "));

        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        Label label5 = new Label();
        Label label6 = new Label();
        Label label7 = new Label();

        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        int num5 = 0;
        int num6 = 0;
        int num7 = 0;

        if(restaurant.getReservations()!=null) {
            for (Reservation reservation : restaurant.getReservations().getReservation()) {

                for (Food food : reservation.getFood()) {
                    String name = food.getName();
                    if (name.equalsIgnoreCase(names.get(0)))
                        num1 += food.getCount();
                    else if (name.equalsIgnoreCase(names.get(1)))
                        num2 += food.getCount();
                    else if (name.equalsIgnoreCase(names.get(2)))
                        num3 += food.getCount();
                    else if (name.equalsIgnoreCase(names.get(3)))
                        num4 += food.getCount();
                    else if (name.equalsIgnoreCase(names.get(4)))
                        num5 += food.getCount();
                    else if (name.equalsIgnoreCase(names.get(5)))
                        num6 += food.getCount();
                    else if (name.equalsIgnoreCase(names.get(6)))
                        num7 += food.getCount();
                }
            }

            label1.setText("Total " + names.get(0) + " " + num1);
            label2.setText("Total " + names.get(1) + " " + num2);
            label3.setText("Total " + names.get(2) + " " + num3);
            label4.setText("Total " + names.get(3) + " " + num4);
            label5.setText("Total " + names.get(4) + " " + num5);
            label6.setText("Total " + names.get(5) + " " + num6);
            label7.setText("Total " + names.get(6) + " " + num7);
        }







            label1.setStyle("-fx-font-weight: bold");
            label2.setStyle("-fx-font-weight: bold");
            label3.setStyle("-fx-font-weight: bold");
            label4.setStyle("-fx-font-weight: bold");
            label5.setStyle("-fx-font-weight: bold");
            label6.setStyle("-fx-font-weight: bold");
            label7.setStyle("-fx-font-weight: bold");
            label1.setFont(new Font(30));
            label2.setFont(new Font(30));
            label3.setFont(new Font(30));
            label4.setFont(new Font(30));
            label5.setFont(new Font(30));
            label6.setFont(new Font(30));
            label7.setFont(new Font(30));
            label1.setTextFill(Color.web("white"));
            label2.setTextFill(Color.web("white"));
            label3.setTextFill(Color.web("white"));
            label4.setTextFill(Color.web("white"));
            label5.setTextFill(Color.web("white"));
            label6.setTextFill(Color.web("white"));
            label7.setTextFill(Color.web("white"));


            Button logout = new Button("Log Out");
            logout.setFont(new Font("Arial",25));
            logout.setTextFill(Color.WHITE);
            logout.setStyle("-fx-background-radius: 15; -fx-background-color: red;");

            Button exit = new Button("Exit");
            exit.setFont(new Font("Arial",25));
            exit.setStyle("-fx-background-radius: 15;");

            HBox hBox = new HBox(15);
            hBox.getChildren().addAll(exit,logout);
            hBox.setAlignment(Pos.CENTER);

            vbox3.setBackground(background1);
            vbox3.setPadding(new Insets(20, 20, 20, 20));



            button.setOnAction(event -> {

                button.setDisable(true);
                        if (restaurant.getReservations() != null) {
                            table.setItems(getRes());
                            vbox3.getChildren().addAll(table, label1, label2, label3, label4, label5, label6, label7, hBox);
                        }
                        else
                        {
                            Label noReservations = new Label("No Reservations Today");
                            noReservations.setFont(new Font("Arial",50));
                            noReservations.setTextFill(Color.RED);
                            noReservations.setAlignment(Pos.CENTER);

                            VBox vBox = new VBox(15);
                            vBox.getChildren().add(noReservations);
                            vBox.setStyle("-fx-background-color: rgba(0, 100, 100, 0.8); -fx-background-radius: 15;");
                            vBox.setPadding(new Insets(200));
                            vBox.setMaxSize(1800,750);
                            vBox.setAlignment(Pos.CENTER);

                            vbox3.getChildren().addAll(vBox,hBox);
                        }
                    }
            );

        label.setFont(new Font("Arial", 60));
            label.setStyle("-fx-font-weight: bold");

        logout.setOnAction( e -> {
            boolean answer =  logOut();
            if(answer) {
                login.prepareScene();
                stage.setScene(login.getScene());
            }
        });

        exit.setOnAction( e -> {
            closeProgram();
        });

            scene = new Scene(vbox3, 1900, 1000);
        }


    public Scene getScene() {
        return scene;
    }

    public ObservableList<CustomerTable> getRes() {
        ObservableList<CustomerTable> reservationsList = FXCollections.observableArrayList();

        if(restaurant.getReservations()!=null) {
            for (Reservation reservation : restaurant.getReservations().getReservation()) {

                CustomerTable customerTable = new CustomerTable(reservation.getName(), reservation.getTableNumber(), reservation.getNumOfSeats(), reservation.getTotalPriceAfterTax());

                for(int i=0 ; i<reservation.getFood().size(); i++)
                {
                    count[i]=reservation.getFood().get(i).getCount();
                }
                customerTable.setNum(count);
                reservationsList.add(customerTable);
            }
        }
        return reservationsList;
    }

    public void setRestaurant (Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}

