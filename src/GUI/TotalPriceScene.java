package GUI;

import Logic.Reserve;
import Logic.ReservedTables;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import static java.lang.System.exit;

public class TotalPriceScene extends ParentScene{

    private Scene scene;
    private Stage stage;
    private CustomerScene customerScene;
    private ReservedTables reservedTables;
    private Reserve reserve;
    private Login loginScene;
    DecimalFormat df = new DecimalFormat("######.##");

    public TotalPriceScene(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public void prepareScene(){

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(7);
        grid.setPadding(new Insets(25));

        Label label1 = new Label();
        label1.setFont(new Font("Arial", 25));
        label1.setTextFill(Color.WHITE);
        grid.add(label1,0,0);

        Label label11 = new Label();
        label11.setFont(new Font("Arial", 25));
        label11.setTextFill(Color.WHITE);
        grid.add(label11,1,0);

        grid.addRow(1,new Text(""));

        Label label2 = new Label();
        label2.setFont(new Font("Arial", 25));
        label2.setTextFill(Color.WHITE);
        grid.add(label2,0,2);

        Label label22 = new Label();
        label22.setFont(new Font("Arial", 25));
        label22.setTextFill(Color.WHITE);
        grid.add(label22,1,2);

        grid.addRow(3,new Text(""));

        Label label3 = new Label();
        label3.setFont(new Font("Arial", 25));
        label3.setTextFill(Color.WHITE);
        grid.add(label3,0,4);

        Label label33 = new Label();
        label33.setFont(new Font("Arial", 25));
        label33.setTextFill(Color.WHITE);
        grid.add(label33,1,4);

        grid.addRow(5,new Text(""));

        Label label4 = new Label();
        label4.setFont(new Font("Arial", 25));
        label4.setTextFill(Color.WHITE);
        grid.add(label4,0,6);

        Label label44 = new Label();
        label44.setFont(new Font("Arial", 25));
        label44.setTextFill(Color.WHITE);
        grid.add(label44,1,6);

        grid.addRow(7,new Text(""));

        Label label5 = new Label();
        label5.setFont(new Font("Arial", 25));
        label5.setTextFill(Color.WHITE);
        grid.add(label5,0,8);

        Label label55 = new Label();
        label55.setFont(new Font("Arial", 25));
        label55.setTextFill(Color.WHITE);
        grid.add(label55,1,8);

        grid.addRow(9,new Text(""));

        Label label6 = new Label();
        label6.setFont(new Font("Arial", 25));
        label6.setTextFill(Color.WHITE);
        grid.add(label6,0,10);

        Label label66 = new Label();
        label66.setFont(new Font("Arial", 25));
        label66.setTextFill(Color.WHITE);
        grid.add(label66,1,10);

        grid.addRow(11,new Text(""));

        Label label7 = new Label();
        label7.setFont(new Font("Arial", 25));
        label7.setTextFill(Color.WHITE);
        grid.add(label7,0,12);

        Label label77 = new Label();
        label77.setFont(new Font("Arial", 25));
        label77.setTextFill(Color.WHITE);
        grid.add(label77,1,12);

        grid.addRow(13,new Text(""));

        Label label8 = new Label();
        label8.setFont(new Font("Arial", 25));
        label8.setTextFill(Color.WHITE);
        grid.add(label8,0,14);

        Label label88 = new Label();
        label88.setFont(new Font("Arial", 25));
        label88.setTextFill(Color.WHITE);
        grid.add(label88,1,14);

        grid.addRow(15,new Text(""));

        Label label9 = new Label();
        label9.setFont(new Font("Arial", 25));
        label9.setTextFill(Color.WHITE);
        grid.add(label9,0,16);

        Label label99 = new Label();
        label99.setFont(new Font("Arial", 25));
        label99.setTextFill(Color.WHITE);
        grid.add(label99,1,16);

        grid.addRow(17,new Text(""));

        Label label10 = new Label();
        label10.setFont(new Font("Arial", 25));
        label10.setTextFill(Color.WHITE);
        grid.add(label10,0,18);

        Label label1010 = new Label();
        label1010.setFont(new Font("Arial", 25));
        label1010.setTextFill(Color.WHITE);
        grid.add(label1010,1,18);

        boolean[] isLabel = new boolean[7];

        if(reservedTables.getMainCoursesMenu().get(0).getCount()!=0)
        {
            label1.setText(reservedTables.getMainCoursesMenu().get(0).getCount() + "x " + reservedTables.getMainCoursesMenu().get(0).getName());
            label11.setText(String.valueOf(reservedTables.getMainCoursesMenu().get(0).getTotalPrice()));
            isLabel[0]=true;
        }
        else if (reservedTables.getMainCoursesMenu().get(1).getCount()!=0)
        {
            label1.setText(reservedTables.getMainCoursesMenu().get(1).getCount() + "x " + reservedTables.getMainCoursesMenu().get(1).getName());
            label11.setText(String.valueOf(reservedTables.getMainCoursesMenu().get(1).getTotalPrice()));
            isLabel[1]=true;
        }
        else if (reservedTables.getMainCoursesMenu().get(2).getCount()!=0)
        {
            label1.setText(reservedTables.getMainCoursesMenu().get(2).getCount() + "x " + reservedTables.getMainCoursesMenu().get(2).getName());
            label11.setText(String.valueOf(reservedTables.getMainCoursesMenu().get(2).getTotalPrice()));
            isLabel[2]=true;
        }
        else if (reservedTables.getAppetizerMenu().get(0).getCount()!=0)
        {
            label1.setText(reservedTables.getAppetizerMenu().get(0).getCount() + "x " + reservedTables.getAppetizerMenu().get(0).getName());
            label11.setText(String.valueOf(reservedTables.getAppetizerMenu().get(0).getTotalPrice()));
            isLabel[3]=true;
        }
        else if (reservedTables.getAppetizerMenu().get(1).getCount()!=0)
        {
            label1.setText(reservedTables.getAppetizerMenu().get(1).getCount() + "x " + reservedTables.getAppetizerMenu().get(1).getName());
            label11.setText(String.valueOf(reservedTables.getAppetizerMenu().get(1).getTotalPrice()));
            isLabel[4]=true;
        }
        else if (reservedTables.getDessertMenu().get(0).getCount()!=0)
        {
            label1.setText(reservedTables.getDessertMenu().get(0).getCount() + "x " + reservedTables.getDessertMenu().get(0).getName());
            label11.setText(String.valueOf(reservedTables.getDessertMenu().get(0).getTotalPrice()));
            isLabel[5]=true;
        }
        else if (reservedTables.getDessertMenu().get(1).getCount()!=0)
        {
            label1.setText(reservedTables.getDessertMenu().get(1).getCount() + "x " + reservedTables.getDessertMenu().get(1).getName());
            label11.setText(String.valueOf(reservedTables.getDessertMenu().get(1).getTotalPrice()));
            isLabel[6]=true;
        }



        if (reservedTables.getMainCoursesMenu().get(1).getCount()!=0 && !isLabel[1])
        {
            label2.setText(reservedTables.getMainCoursesMenu().get(1).getCount() + "x " + reservedTables.getMainCoursesMenu().get(1).getName());
            label22.setText(String.valueOf(reservedTables.getMainCoursesMenu().get(1).getTotalPrice()));
            isLabel[1]=true;
        }
        else if (reservedTables.getMainCoursesMenu().get(2).getCount()!=0 && !isLabel[2])
        {
            label2.setText(reservedTables.getMainCoursesMenu().get(2).getCount() + "x " + reservedTables.getMainCoursesMenu().get(2).getName());
            label22.setText(String.valueOf(reservedTables.getMainCoursesMenu().get(2).getTotalPrice()));
            isLabel[2]=true;
        }
        else if (reservedTables.getAppetizerMenu().get(0).getCount()!=0 && !isLabel[3])
        {
            label2.setText(reservedTables.getAppetizerMenu().get(0).getCount() + "x " + reservedTables.getAppetizerMenu().get(0).getName());
            label22.setText(String.valueOf(reservedTables.getAppetizerMenu().get(0).getTotalPrice()));
            isLabel[3]=true;
        }
        else if (reservedTables.getAppetizerMenu().get(1).getCount()!=0 && !isLabel[4])
        {
            label2.setText(reservedTables.getAppetizerMenu().get(1).getCount() + "x " + reservedTables.getAppetizerMenu().get(1).getName());
            label22.setText(String.valueOf(reservedTables.getAppetizerMenu().get(1).getTotalPrice()));
            isLabel[4]=true;
        }
        else if (reservedTables.getDessertMenu().get(0).getCount()!=0 && !isLabel[5])
        {
            label2.setText(reservedTables.getDessertMenu().get(0).getCount() + "x " + reservedTables.getDessertMenu().get(0).getName());
            label22.setText(String.valueOf(reservedTables.getDessertMenu().get(0).getTotalPrice()));
            isLabel[5]=true;
        }
        else if (reservedTables.getDessertMenu().get(1).getCount()!=0 && !isLabel[6])
        {
            label2.setText(reservedTables.getDessertMenu().get(1).getCount() + "x " + reservedTables.getDessertMenu().get(1).getName());
            label22.setText(String.valueOf(reservedTables.getDessertMenu().get(1).getTotalPrice()));
            isLabel[6]=true;
        }


        if (reservedTables.getMainCoursesMenu().get(2).getCount()!=0  && !isLabel[2])
        {
            label3.setText(reservedTables.getMainCoursesMenu().get(2).getCount() + "x " + reservedTables.getMainCoursesMenu().get(2).getName());
            label33.setText(String.valueOf(reservedTables.getMainCoursesMenu().get(2).getTotalPrice()));
            isLabel[2]=true;
        }
        else if (reservedTables.getAppetizerMenu().get(0).getCount()!=0  && !isLabel[3])
        {
            label3.setText(reservedTables.getAppetizerMenu().get(0).getCount() + "x " + reservedTables.getAppetizerMenu().get(0).getName());
            label33.setText(String.valueOf(reservedTables.getAppetizerMenu().get(0).getTotalPrice()));
            isLabel[3]=true;
        }
        else if (reservedTables.getAppetizerMenu().get(1).getCount()!=0 && !isLabel[4])
        {
            label3.setText(reservedTables.getAppetizerMenu().get(1).getCount() + "x " + reservedTables.getAppetizerMenu().get(1).getName());
            label33.setText(String.valueOf(reservedTables.getAppetizerMenu().get(1).getTotalPrice()));
            isLabel[4]=true;
        }
        else if (reservedTables.getDessertMenu().get(0).getCount()!=0 && !isLabel[5])
        {
            label3.setText(reservedTables.getDessertMenu().get(0).getCount() + "x " + reservedTables.getDessertMenu().get(0).getName());
            label33.setText(String.valueOf(reservedTables.getDessertMenu().get(0).getTotalPrice()));
            isLabel[5]=true;
        }
        else if (reservedTables.getDessertMenu().get(1).getCount()!=0 && !isLabel[6])
        {
            label3.setText(reservedTables.getDessertMenu().get(1).getCount() + "x " + reservedTables.getDessertMenu().get(1).getName());
            label33.setText(String.valueOf(reservedTables.getDessertMenu().get(1).getTotalPrice()));
            isLabel[6]=true;
        }


        if (reservedTables.getAppetizerMenu().get(0).getCount()!=0 && !isLabel[3])
        {
            label4.setText(reservedTables.getAppetizerMenu().get(0).getCount() + "x " + reservedTables.getAppetizerMenu().get(0).getName());
            label44.setText(String.valueOf(reservedTables.getAppetizerMenu().get(0).getTotalPrice()));
            isLabel[3]=true;
        }
        else if (reservedTables.getAppetizerMenu().get(1).getCount()!=0 && !isLabel[4])
        {
            label4.setText(reservedTables.getAppetizerMenu().get(1).getCount() + "x " + reservedTables.getAppetizerMenu().get(1).getName());
            label44.setText(String.valueOf(reservedTables.getAppetizerMenu().get(1).getTotalPrice()));
            isLabel[4]=true;
        }
        else if (reservedTables.getDessertMenu().get(0).getCount()!=0 && !isLabel[5])
        {
            label4.setText(reservedTables.getDessertMenu().get(0).getCount() + "x " + reservedTables.getDessertMenu().get(0).getName());
            label44.setText(String.valueOf(reservedTables.getDessertMenu().get(0).getTotalPrice()));
            isLabel[5]=true;
        }
        else if (reservedTables.getDessertMenu().get(1).getCount()!=0 && !isLabel[6])
        {
            label4.setText(reservedTables.getDessertMenu().get(1).getCount() + "x " + reservedTables.getDessertMenu().get(1).getName());
            label44.setText(String.valueOf(reservedTables.getDessertMenu().get(1).getTotalPrice()));
            isLabel[6]=true;
        }


        if (reservedTables.getAppetizerMenu().get(1).getCount()!=0 && !isLabel[4])
        {
            label5.setText(reservedTables.getAppetizerMenu().get(1).getCount() + "x " + reservedTables.getAppetizerMenu().get(1).getName());
            label55.setText(String.valueOf(reservedTables.getAppetizerMenu().get(1).getTotalPrice()));
            isLabel[4]=true;
        }
        else if (reservedTables.getDessertMenu().get(0).getCount()!=0 && !isLabel[5])
        {
            label5.setText(reservedTables.getDessertMenu().get(0).getCount() + "x " + reservedTables.getDessertMenu().get(0).getName());
            label55.setText(String.valueOf(reservedTables.getDessertMenu().get(0).getTotalPrice()));
            isLabel[5]=true;
        }
        else if (reservedTables.getDessertMenu().get(1).getCount()!=0 && !isLabel[6])
        {
            label5.setText(reservedTables.getDessertMenu().get(1).getCount() + "x " + reservedTables.getDessertMenu().get(1).getName());
            label55.setText(String.valueOf(reservedTables.getDessertMenu().get(1).getTotalPrice()));
            isLabel[6]=true;
        }


        if (reservedTables.getDessertMenu().get(0).getCount()!=0 && !isLabel[5])
        {
            label6.setText(reservedTables.getDessertMenu().get(0).getCount() + "x " + reservedTables.getDessertMenu().get(0).getName());
            label66.setText(String.valueOf(reservedTables.getDessertMenu().get(0).getTotalPrice()));
            isLabel[5]=true;
        }
        else if (reservedTables.getDessertMenu().get(1).getCount()!=0 && !isLabel[6])
        {
            label6.setText(reservedTables.getDessertMenu().get(1).getCount() + "x " + reservedTables.getDessertMenu().get(1).getName());
            label66.setText(String.valueOf(reservedTables.getDessertMenu().get(1).getTotalPrice()));
            isLabel[6]=true;
        }


        if (reservedTables.getDessertMenu().get(1).getCount()!=0 && !isLabel[6])
        {
            label7.setText(reservedTables.getDessertMenu().get(1).getCount() + "x " + reservedTables.getDessertMenu().get(1).getName());
            label77.setText(String.valueOf(reservedTables.getDessertMenu().get(1).getTotalPrice()));
            isLabel[6]=true;
        }


        if (label1.getText().isEmpty())
        {
            label1.setText("Subtotal: ");
            label11.setText("0.0 LE");
            label2.setText("Tax: ");
            label22.setText("0.0 LE");
            label3.setText("Total Amount After Tax: ");
            label33.setText("0.0 LE");
        }
        else if (label2.getText().isEmpty())
        {
            label2.setText("Subtotal: ");
            label22.setText(String.valueOf(reservedTables.getTotalPriceBeforeTax()) + " LE");
            label3.setText("Tax: ");
            label33.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax() - reservedTables.getTotalPriceBeforeTax())) + " LE");
            label4.setText("Total Amount After Tax: ");
            label44.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax())) + " LE");
        }
        else if (label3.getText().isEmpty())
        {
            label3.setText("Subtotal: ");
            label33.setText(String.valueOf(reservedTables.getTotalPriceBeforeTax()) + " LE");
            label4.setText("Tax: ");
            label44.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax() - reservedTables.getTotalPriceBeforeTax())) + " LE");
            label5.setText("Total Amount After Tax: ");
            label55.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax())) + " LE");
        }
        else if (label4.getText().isEmpty())
        {
            label4.setText("Subtotal: ");
            label44.setText(String.valueOf(reservedTables.getTotalPriceBeforeTax()) + " LE");
            label5.setText("Tax: ");
            label55.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax() - reservedTables.getTotalPriceBeforeTax())) + " LE");
            label6.setText("Total Amount After Tax: ");
            label66.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax())) + " LE");
        }
        else if (label5.getText().isEmpty())
        {
            label5.setText("Subtotal: ");
            label55.setText(String.valueOf(reservedTables.getTotalPriceBeforeTax()) + " LE");
            label6.setText("Tax: ");
            label66.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax() - reservedTables.getTotalPriceBeforeTax())) + " LE");
            label7.setText("Total Amount After Tax: ");
            label77.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax())) + " LE");
        }
        else if (label6.getText().isEmpty())
        {
            label6.setText("Subtotal: ");
            label66.setText(String.valueOf(reservedTables.getTotalPriceBeforeTax()));
            label7.setText("Tax: ");
            label77.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax() - reservedTables.getTotalPriceBeforeTax())) + " LE");
            label8.setText("Total Amount After Tax: ");
            label88.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax())) + " LE");
        }
        else if (label7.getText().isEmpty())
        {
            label7.setText("Subtotal: ");
            label77.setText(String.valueOf(reservedTables.getTotalPriceBeforeTax()) + " LE");
            label8.setText("Tax: ");
            label88.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax() - reservedTables.getTotalPriceBeforeTax())) + " LE");
            label9.setText("Total Amount After Tax: ");
            label99.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax())) + " LE");
        }
        else if (label8.getText().isEmpty())
        {
            label8.setText("Subtotal: ");
            label88.setText(String.valueOf(reservedTables.getTotalPriceBeforeTax()) + " LE");
            label9.setText("Tax: ");
            label99.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax() - reservedTables.getTotalPriceBeforeTax())) + " LE");
            label10.setText("Total Amount After Tax: ");
            label1010.setText(String.valueOf( df.format(reservedTables.getTotalPriceAfterTax())) + " LE");
        }

        Button confirm = new Button("Confirm");
        confirm.setFont(new Font("Arial",25));
        confirm.setStyle("-fx-background-radius: 15;");

        Button exit = new Button("Exit");
        exit.setFont(new Font("Arial",25));
        exit.setStyle("-fx-background-radius: 15;");

        Button logOut = new Button("Log Out");
        logOut.setFont(new Font("Arial",25));
        logOut.setTextFill(Color.WHITE);
        logOut.setStyle("-fx-background-radius: 15; -fx-background-color: red;");

        confirm.setOnAction( e -> {

            Confirmbox confirmReservation = new Confirmbox();
            Boolean answer = confirmReservation.message("Confirm Reservation","Are You Sure You Want To Make A Reservation ?");
            if(answer) {
                try {
                    reserve.finalReserve(reservedTables);
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
                confirm.setDisable(true);
            }

        });

        exit.setOnAction(e -> {
            closeProgram();
        });

        logOut.setOnAction(e -> {
            boolean answer =  logOut();
            if(answer) {
                loginScene.prepareScene();
                stage.setScene(loginScene.getScene());
            }
        });

        HBox hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20,20,20,20));
        hBox.getChildren().addAll(confirm,exit,logOut);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(grid);
        BorderPane.setAlignment(grid, Pos.CENTER);
        BorderPane.setMargin(grid, new Insets(12,12,12,12));

        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(12,12,12,12));

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

        scene = new Scene(borderPane,1900,1000);

    }

    public Scene getScene() {
        return scene;
    }

    public void setReservedTables(ReservedTables reservedTables) {
        this.reservedTables = reservedTables;
    }

    public void setCustomerScene(CustomerScene customerScene) {
        this.customerScene = customerScene;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public void setLoginScene(Login loginScene) {
        this.loginScene = loginScene;
    }
}
