package GUI;

import Logic.Food;
import Main.Reservation;
import Main.Reservations;
import Main.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class WaiterScene extends ParentScene{

    private Scene scene;
    private Stage stage;
    TableView<CustomerTable> table;
    private Restaurant restaurant;
    private Reservations reservations;
    private Login login;

    public WaiterScene(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public void prepareScene(){

        VBox vbox = new VBox(8);

        Label label = new Label("WELCOME BACK!");
        label.setTextFill(Color.web("white"));

        HBox hBox = new HBox(15);
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.CENTER);

        Image image = new Image("file:waiter.jpg");
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background background1 = new Background(background);

        table = new TableView<>();
        table.setStyle("-fx-background-color: transparent;");
        table.setPrefHeight(650);

        TableColumn<CustomerTable,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(300);
        nameColumn.setStyle("-fx-font-size: 25;");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<CustomerTable,Integer> tableNumberColumn = new TableColumn<>("Table Number");
        tableNumberColumn.setMinWidth(300);
        tableNumberColumn.setStyle("-fx-font-size: 25; -fx-alignment: CENTER;");
        tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

        TableColumn<CustomerTable,Integer> seatsColumn = new TableColumn<>("Number of Seats");
        seatsColumn.setMinWidth(200);
        seatsColumn.setStyle("-fx-font-size: 25; -fx-alignment: CENTER;");
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfSeats"));

        int count=0;
        int seats=0;

        if(restaurant.getReservations()!=null) {
            for (Reservation reservation : restaurant.getReservations().getReservation()) {
                count++;
                seats += reservation.getNumOfSeats();
            }
        }

        Label total = new Label("Number of Tables Reserved: " + count);
        total.setStyle(String.format("-fx-font-weight: bold"));
        total.setFont(new Font(40));
        total.setTextFill(Color.web("white"));

        Label seatsNo = new Label("Number of Seats Reserved: " + seats);
        seatsNo.setStyle(String.format("-fx-font-weight: bold"));
        seatsNo.setFont(new Font(40));
        seatsNo.setTextFill(Color.web("white"));

        Button button = new Button("Display Today's Work!");
        button.setMinWidth(300);
        button.setMinHeight(40);
        button.setTextFill(Color.RED);
        button.setStyle("-fx-background-radius: 15 ");
        button.setFont(new Font("Arial",25));

        VBox vBox3 = new VBox(15);
        vBox3.getChildren().add(button);

        Button logout = new Button("Log Out");
        logout.setFont(new Font("Arial",25));
        logout.setTextFill(Color.WHITE);
        logout.setStyle("-fx-background-radius: 15; -fx-background-color: red;");

        Button exit = new Button("Exit");
        exit.setFont(new Font("Arial",25));
        exit.setStyle("-fx-background-radius: 15;");

        HBox hBox1 = new HBox(15);
        hBox1.getChildren().addAll(exit,logout);
        hBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(15);
        vBox2.getChildren().addAll(total,seatsNo);

        table.getColumns().addAll(nameColumn,tableNumberColumn,seatsColumn);

        vbox.setBackground(background1);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox.getChildren().addAll(hBox,vBox3);

        button.setOnAction(event -> {
            button.setDisable(true);

                    if (restaurant.getReservations() != null) {
                        table.setItems(getRes());
                        vbox.getChildren().addAll(table, vBox2, hBox1);
                    }
                    else
                    {
                        Label noReservations = new Label("No Reservations Today");
                        noReservations.setFont(new Font("Arial",50));
                        noReservations.setTextFill(Color.RED);
                        noReservations.setAlignment(Pos.CENTER);

                        VBox vBox1 = new VBox(15);
                        vBox1.getChildren().add(noReservations);
                        vBox1.setStyle("-fx-background-color: rgba(0, 100, 100, 0.8); -fx-background-radius: 15;");
                        vBox1.setPadding(new Insets(200));
                        vBox1.setMaxSize(1700,750);
                        vBox1.setAlignment(Pos.CENTER);

                        vbox.getChildren().addAll(vBox1,hBox1);
                    }
                }
        );

        label.setFont(new Font("Arial",50));
        label.setStyle("-fx-font-weight: bold");
        button.setPadding(new Insets(10));
        table.setPadding(new Insets(50,50,50,50));

        vbox.setBackground(background1);
        vbox.setPadding(new Insets(20,20,20,20));

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

        scene = new Scene(vbox,1900,1000);
    }


    public Scene getScene() {
        return scene;
    }

    public ObservableList<CustomerTable> getRes() {
        ObservableList<CustomerTable> reservationsList = FXCollections.observableArrayList();

        if(restaurant.getReservations()!=null) {
            for (Reservation reservation : restaurant.getReservations().getReservation()) {

                CustomerTable customerTable = new CustomerTable(reservation.getName(), reservation.getTableNumber(), reservation.getNumOfSeats(), reservation.getTotalPriceAfterTax());

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

