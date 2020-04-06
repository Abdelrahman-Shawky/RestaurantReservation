package GUI;

import Main.Restaurant;
import Main.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SignUp extends ParentScene {

    private Scene scene;
    private Stage stage;
    private Restaurant restaurant;
    private Login loginScene;

    public SignUp(Stage stage) {
        super(stage);
        this.stage = stage;
    }


    public void prepareScene() {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(7);
        grid.setPadding(new Insets(25));

        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20,20,20,20));

        BorderPane borderPane = new BorderPane();

        try {
            FileInputStream input = new FileInputStream("restaurant.jpg");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.fitWidthProperty().bind(stage.widthProperty());
            imageView.fitHeightProperty().bind(stage.heightProperty());
            borderPane.setCenter(imageView);
            BorderPane.setAlignment(grid, Pos.CENTER);
            BorderPane.setMargin(grid, new Insets(12,12,12,12));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Label clientLabel = new Label("Welcome");
        clientLabel.setFont(new Font("Arial",40));

        Label login = new Label("Client Sign Up");
        login.setFont(new Font("Arial",35));

        Label nameLabel = new Label("Name");
        nameLabel.setFont(new Font("Arial",21));

        TextField nameTextfield = new TextField();
        nameTextfield.setPromptText("name");
        nameTextfield.setFont(new Font("Arial",18));

        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(new Font("Arial",21));

        TextField usernameTextfield = new TextField();
        usernameTextfield.setPromptText("Username");
        usernameTextfield.setFont(new Font("Arial",18));

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(new Font("Arial",21));

        PasswordField passwordTextfield = new PasswordField();
        passwordTextfield.setFont(new Font("Arial",18));

        Button signUp = new Button("Sign UP");
        signUp.setFont(new Font("Arial",20));
        signUp.setStyle("-fx-background-radius: 15;");

        Label input = new Label();
        input.setFont(new Font("Arial",25));
        input.setTextFill(Color.RED);

        Button back = new Button("Back To Login");
        back.setFont(new Font("Arial",22));
        back.setStyle("-fx-background-radius: 15;");

        signUp.setOnAction( e -> {

            boolean isSameUsername = false;
            for(User user : restaurant.getUsers().getUser())
            {
                if (usernameTextfield.getText().equalsIgnoreCase(user.getUsername()))
                {
                    isSameUsername = true;
                    break;
                }
            }

            User newUser = new User();
            newUser.setName(nameTextfield.getText());
            newUser.setUsername(usernameTextfield.getText());
            newUser.setPassword(passwordTextfield.getText());
            newUser.setRole("Client");

            if(!nameTextfield.getText().isEmpty() && !usernameTextfield.getText().isEmpty() && !passwordTextfield.getText().isEmpty() && !isSameUsername) {

                signUp.setDisable(true);
                restaurant.getUsers().getUser().add(newUser);

                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
                    Marshaller marshaller = jaxbContext.createMarshaller();
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                    marshaller.marshal(restaurant, new File("input.xml"));
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
                input.setText("Sign Up Successful !");
            }
            else if (isSameUsername)
            {
                input.setText("Username Taken");
            }
            else
            {
                input.setText("Missing Fields");
            }
        });

        vBox.getChildren().addAll(clientLabel,new Label(""),login,new Label(""),nameLabel,nameTextfield,usernameLabel,usernameTextfield,passwordLabel,passwordTextfield,signUp,back,input);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginScene.prepareScene();
                stage.setScene(loginScene.getScene());
            }
        });

        borderPane.setLeft(vBox);
        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setMargin(vBox, new Insets(12,12,12,12));
        borderPane.setStyle("-fx-background-color: orange");

        scene = new Scene(borderPane,1900,1000);

    }


    public Scene getScene() {
        return scene;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setLoginScene(Login loginScene) {
        this.loginScene = loginScene;
    }
}
