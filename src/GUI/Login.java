package GUI;

import Logic.Authentication;
import Main.Restaurant;
import Main.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Login extends ParentScene{
    private Scene scene;
    private Stage stage;
    private ManagerScene managerScene;
    private CustomerScene customerScene;
    private Restaurant restaurant;
    private ChefScene chefScene;
    private WaiterScene waiterScene;
    private SignUp signUpScene;

    public Login(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public void prepareScene(){

        chefScene.prepareScene();
        customerScene.prepareScene();
        waiterScene.prepareScene();
        managerScene.prepareScene();
        signUpScene.prepareScene();


        Authentication authentication = new Authentication();
        authentication.setRestaurant(restaurant);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(7);
        grid.setPadding(new Insets(25));

        Label login = new Label("Login");
        login.setFont(new Font("Arial",40));

        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(new Font("Arial",21));

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(new Font("Arial",21));

        TextField usernameTextfield = new TextField();
        usernameTextfield.setFont(new Font("Arial",18));

        PasswordField passwordTextfield = new PasswordField();
        passwordTextfield.setFont(new Font("Arial",18));

        Button signInButton = new Button("SIGN IN");
        signInButton.setAlignment(Pos.CENTER);
        signInButton.setFont(new Font("Arial",19));
        signInButton.setStyle("-fx-background-radius: 15;");

        Button exitButton = new Button("Exit");
        exitButton.setFont(new Font("Arial",19));
        exitButton.setStyle("-fx-background-radius: 15;");

        Button signUpButton = new Button("Sign Up");
        signUpButton.setFont(new Font("Arial",19));
        signUpButton.setStyle("-fx-background-radius: 15;");

        Label invalid = new Label();
        invalid.setFont(new Font("Arial",21));
        invalid.setTextFill(Color.RED);

        BorderPane borderPane = new BorderPane();

        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.getChildren().addAll(login,new Label(""),usernameLabel,usernameTextfield,passwordLabel,passwordTextfield,signInButton,signUpButton,exitButton,invalid);

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

        borderPane.setLeft(vBox);
        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setMargin(vBox, new Insets(12,12,12,12));
        borderPane.setStyle("-fx-background-color: orange");

        stage.setOnCloseRequest(e ->{
            e.consume();
            closeProgram();
        });

        signUpButton.setOnAction( e -> {
            signUpScene.prepareScene();
            stage.setScene(signUpScene.getScene());
        });

        signInButton.setOnAction(e -> {
            String username = usernameTextfield.getText();
            String password = passwordTextfield.getText();

            boolean isValid = authentication.validate(username,password);
            if(isValid) {
                String role = "invalid";
                String name = "";

                for (User user : restaurant.getUsers().getUser())
                {
                    if(usernameTextfield.getText().equalsIgnoreCase(user.getUsername()))
                    {
                        role = user.getRole();
                        name = user.getName();
                        break;
                    }
                }
                switch (role.toLowerCase()){
                    case "manager":
                        stage.setScene(managerScene.getScene());
                        break;
                    case "client":
                        customerScene.setName(name);
                        stage.setScene(customerScene.getScene());
                        break;
                    case "waiter":
                        stage.setScene(waiterScene.getScene());
                        break;
                    case "cooker":
                        stage.setScene(chefScene.getScene());
                        break;
                    case "invalid":
                        break;
                }
            }
            else
            {
                invalid.setText("Invalid ");
            }
        });

        exitButton.setOnAction(e -> {
          closeProgram();
        });

        scene = new Scene(borderPane,1900,1000);

    }
    public Scene getScene() {
        return scene;
    }

    public void setManagerScene(ManagerScene managerScene) {
        this.managerScene = managerScene;
    }

    public void setCustomerScene(CustomerScene customerScene) {
        this.customerScene = customerScene;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setChefScene(ChefScene chefScene) {
        this.chefScene = chefScene;
    }

    public void setWaiterScene(WaiterScene waiterScene) {
        this.waiterScene = waiterScene;
    }

    public void setSignUpScene(SignUp signUpScene) {
        this.signUpScene = signUpScene;
    }
}
