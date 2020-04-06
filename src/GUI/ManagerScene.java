package GUI;

import Logic.Food;
import Main.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ManagerScene extends ParentScene{

    private Scene scene;
    private Stage stage;
    private Login login;
    private Restaurant restaurant;
    private TableView<ManagerTable> staffClientTable;
    private TableView<CustomerTable> reservationTable;
    DecimalFormat df = new DecimalFormat("######.##");
    private List<String> names = new ArrayList<>();
    private int[] count = new int[10];

    public ManagerScene(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public void prepareScene(){

        ToggleButton staff = new ToggleButton("Staff");
        staff.setFont(new Font("Arial",25));
        staff.setStyle("-fx-background-radius: 15;");

        ToggleButton client = new ToggleButton("Clients");
        client.setFont(new Font("Arial",25));
        client.setStyle("-fx-background-radius: 15;");

        ToggleButton todayReservations = new ToggleButton("Today's Reservations");
        todayReservations.setFont(new Font("Arial",25));
        todayReservations.setStyle("-fx-background-radius: 15;");

        ToggleButton totalEarnedMoney = new ToggleButton("Today's Earnings");
        totalEarnedMoney.setFont(new Font("Arial",25));
        totalEarnedMoney.setStyle("-fx-background-radius: 15;");

        ToggleGroup group = new ToggleGroup();
        staff.setToggleGroup(group);
        client.setToggleGroup(group);
        todayReservations.setToggleGroup(group);
        totalEarnedMoney.setToggleGroup(group);

        Button logOut = new Button("Log Out");
        logOut.setFont(new Font("Arial",25));
        logOut.setTextFill(Color.WHITE);
        logOut.setStyle("-fx-background-radius: 15; -fx-background-color: red;");

        Button exit = new Button("Exit");
        exit.setFont(new Font("Arial",25));
        exit.setStyle("-fx-background-radius: 15;");

        HBox hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(25));
        hBox.getChildren().addAll(staff,client,todayReservations,totalEarnedMoney);

        HBox hBox1 = new HBox(15);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPadding(new Insets(25));
        hBox1.getChildren().addAll(exit,logOut);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(25));

        staffClientTable = new TableView<>();
        staffClientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        staffClientTable.setPadding(new Insets(50,50,200,50));
        staffClientTable.setStyle("-fx-font-size: 25; -fx-background-color: transparent; ");

        reservationTable = new TableView<>();
        reservationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        reservationTable.setStyle("-fx-font-size: 19; -fx-background-color: transparent;");
        reservationTable.setPadding(new Insets(50,50,200,50));

        TableColumn<ManagerTable, String> nameColumn = new TableColumn<>();
        nameColumn.setMinWidth(200);

        TableColumn<ManagerTable, String> roleColumn = new TableColumn<>();
        roleColumn.setMinWidth(200);

        TableColumn<CustomerTable, String> customerNameColumn = new TableColumn<>();
        customerNameColumn.setMinWidth(200);

        TableColumn<CustomerTable, String> column1 = new TableColumn<>();
        column1.setMinWidth(200);

        TableColumn<CustomerTable, String> column2 = new TableColumn<>();
        column2.setMinWidth(200);

        TableColumn<CustomerTable, String> column3 = new TableColumn<>();
        column3.setMinWidth(200);

        TableColumn<CustomerTable, String> column4 = new TableColumn<>();
        column4.setMinWidth(200);

        TableColumn<CustomerTable, String> column5 = new TableColumn<>();
        column5.setMinWidth(200);

        TableColumn<CustomerTable, String> column6 = new TableColumn<>();
        column6.setMinWidth(200);

        TableColumn<CustomerTable, String> column7 = new TableColumn<>();
        column7.setMinWidth(200);

        TableColumn<CustomerTable, String> tableNumberColumn = new TableColumn<>();
        tableNumberColumn.setMinWidth(200);

        TableColumn<CustomerTable, String> totalColumn = new TableColumn<>();
        totalColumn.setMinWidth(200);


        try {
            FileInputStream input = new FileInputStream("manager.jpg");
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


        staff.setOnAction( e -> {
            staffClientTable.getColumns().clear();
            reservationTable.getColumns().clear();

            nameColumn.setText("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<ManagerTable, String>("name"));
            roleColumn.setText("Role");
            roleColumn.setCellValueFactory(new PropertyValueFactory<ManagerTable, String>("role"));

            staffClientTable.setItems(getManagerTable(1));
            staffClientTable.getColumns().addAll(nameColumn,roleColumn);

            borderPane.setCenter(staffClientTable);
            BorderPane.setAlignment(staffClientTable, Pos.CENTER);
            BorderPane.setMargin(staffClientTable, new Insets(12,12,12,12));
        });

        client.setOnAction( e -> {
            staffClientTable.getColumns().clear();
            reservationTable.getColumns().clear();

            nameColumn.setText("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<ManagerTable, String>("name"));
            roleColumn.setText("Role");
            roleColumn.setCellValueFactory(new PropertyValueFactory<ManagerTable, String>("role"));

            staffClientTable.setItems(getManagerTable(2));
            staffClientTable.getColumns().addAll(nameColumn, roleColumn);

            borderPane.setCenter(staffClientTable);
            BorderPane.setAlignment(staffClientTable, Pos.CENTER);
            BorderPane.setMargin(staffClientTable, new Insets(12,12,12,12));
        });

        todayReservations.setOnAction( e -> {

            if(restaurant.getReservations()!=null) {

                for (Food food : restaurant.getReservations().getReservation().get(0).getFood())
                {
                    names.add(food.getName());
                }

                reservationTable.getColumns().clear();
                staffClientTable.getColumns().clear();

                customerNameColumn.setText("Customer Name");
                customerNameColumn.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("name"));
                customerNameColumn.setMinWidth(150);
                customerNameColumn.setPrefWidth(150);

                tableNumberColumn.setText("Table No.");
                tableNumberColumn.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("tableNumber"));
                tableNumberColumn.setMinWidth(150);
                tableNumberColumn.setPrefWidth(150);
                tableNumberColumn.setStyle("-fx-alignment: CENTER;");

                column1.setText(names.get(0));
                column1.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("num1"));
                column1.setMinWidth(150);
                column1.setPrefWidth(150);
                column1.setStyle("-fx-alignment: CENTER;");

                column2.setText(names.get(1));
                column2.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("num2"));
                column2.setMinWidth(150);
                column2.setPrefWidth(150);
                column2.setStyle("-fx-alignment: CENTER;");

                column3.setText(names.get(2));
                column3.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("num3"));
                column3.setMinWidth(150);
                column3.setPrefWidth(150);
                column3.setStyle("-fx-alignment: CENTER;");

                column4.setText(names.get(3));
                column4.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("num4"));
                column4.setMinWidth(150);
                column4.setPrefWidth(150);
                column4.setStyle("-fx-alignment: CENTER;");

                column5.setText(names.get(4));
                column5.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("num5"));
                column5.setMinWidth(150);
                column5.setPrefWidth(150);
                column5.setStyle("-fx-alignment: CENTER;");

                column6.setText(names.get(5));
                column6.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("num6"));
                column6.setMinWidth(150);
                column6.setPrefWidth(150);
                column6.setStyle("-fx-alignment: CENTER;");

                column7.setText(names.get(6));
                column7.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("num7"));
                column7.setMinWidth(150);
                column7.setPrefWidth(150);
                column7.setStyle("-fx-alignment: CENTER;");

                totalColumn.setText("Total");
                totalColumn.setCellValueFactory(new PropertyValueFactory<CustomerTable, String>("amountToBePaid"));
                totalColumn.setMinWidth(150);
                totalColumn.setPrefWidth(150);
                totalColumn.setStyle("-fx-alignment: CENTER;");



                if (restaurant.getReservations() != null) {
                    reservationTable.setItems(getReservationTable());
                    reservationTable.getColumns().addAll(customerNameColumn, tableNumberColumn,totalColumn, column1, column2, column3, column4, column5, column6, column7);
                }

                borderPane.setCenter(reservationTable);
                BorderPane.setAlignment(reservationTable, Pos.CENTER);
                BorderPane.setMargin(reservationTable, new Insets(12, 12, 12, 12));
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
                vBox.setMaxSize(1700,750);
                vBox.setAlignment(Pos.CENTER);

                borderPane.setCenter(vBox);
                BorderPane.setMargin(noReservations, new Insets(12));
            }


        });

        totalEarnedMoney.setOnAction( e -> {
            if(restaurant.getReservations()!=null) {

                double total=0;
                for(Reservation reservation : restaurant.getReservations().getReservation())
                {
                    total += reservation.getTotalPriceAfterTax();
                }

                Label todayEarnings = new Label("Today's Earnings = " + df.format(total) + " LE");
                todayEarnings.setFont(new Font("Arial", 50));
                todayEarnings.setTextFill(Color.RED);
                todayEarnings.setAlignment(Pos.CENTER);

                VBox vBox = new VBox(15);
                vBox.getChildren().add(todayEarnings);
                vBox.setStyle("-fx-background-color: rgba(0, 100, 100, 0.8); -fx-background-radius: 15;");
                vBox.setPadding(new Insets(200));
                vBox.setMaxSize(1700, 750);
                vBox.setAlignment(Pos.CENTER);

                borderPane.setCenter(vBox);
                BorderPane.setMargin(todayEarnings, new Insets(12));
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
                vBox.setMaxSize(1700,750);
                vBox.setAlignment(Pos.CENTER);

                borderPane.setCenter(vBox);
                BorderPane.setMargin(noReservations, new Insets(12));
            }
        });

        borderPane.setBottom(hBox1);
        BorderPane.setAlignment(hBox1, Pos.CENTER);
        BorderPane.setMargin(hBox1, new Insets(12,12,12,12));

        logOut.setOnAction( e -> {
            boolean answer =  logOut();
            if(answer) {
                login.prepareScene();
                stage.setScene(login.getScene());
            }
        });

        exit.setOnAction( e -> {
            closeProgram();
        });

            scene = new Scene(borderPane,1900,1000);
    }

    public ObservableList<ManagerTable> getManagerTable(int type){
        ObservableList<ManagerTable> managerTables = FXCollections.observableArrayList();

        for(User user : restaurant.getUsers().getUser())
        {
        switch(type) {
            case 1:
                if(user.getRole().equalsIgnoreCase("manager") || user.getRole().equalsIgnoreCase("waiter") || user.getRole().equalsIgnoreCase("cooker"))
            managerTables.add(new ManagerTable(user.getName(), user.getRole()));
            break;
            case 2:
                if (user.getRole().equalsIgnoreCase("client"))
                managerTables.add(new ManagerTable(user.getName(), user.getRole()));
                break;
        }
        }
        return managerTables;
    }

    public ObservableList<CustomerTable> getReservationTable() {

        ObservableList<CustomerTable> reservationTables = FXCollections.observableArrayList();

        if(restaurant.getReservations()!=null) {
            for (Reservation reservation : restaurant.getReservations().getReservation()) {
                CustomerTable customerTable = new CustomerTable(reservation.getName(), reservation.getTableNumber(), reservation.getNumOfSeats(), Double.parseDouble(df.format(reservation.getTotalPriceAfterTax())));

                for(int i=0 ; i<reservation.getFood().size(); i++)
                {
                    count[i]=reservation.getFood().get(i).getCount();
                }
                customerTable.setNum(count);
                reservationTables.add(customerTable);
            }
        }
        return reservationTables;
    }

    public Scene getScene() {
        return scene;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
