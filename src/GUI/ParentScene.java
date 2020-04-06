package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

abstract public class ParentScene {

    private Stage stage;
    private Scene scene;
    private Login login;

//    public ParentScene(Stage stage,Login login) {
//        this.stage = stage;
//        this.login = login;
//    }

    public ParentScene(Stage stage) {
        this.stage = stage;

    }

    public void setParentScene(Scene scene) {
        this.scene = scene;
    }

//    public Scene getScene() {
//        return scene;
//    }

    public void closeProgram(){
        Confirmbox confirmbox = new Confirmbox();
        boolean answer = confirmbox.message("Exit","Are You Sure You Want To Exit?");
        if (answer)
            stage.close();
    }

    public boolean logOut(){

        Confirmbox logOutBox = new Confirmbox();
        boolean answer = logOutBox.message("Log Out","Are You Sure You Want To Logout ?");
        return answer;
    }

}
