package projeto.banco.bank.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.banco.bank.View.ViewControl;

public class LogoutController {
    @FXML
    private Label ref_window;
    ViewControl viewControl = new ViewControl();;
    public void login() {
        viewControl.showLogin();
        Stage stage = (Stage) ref_window.getScene().getWindow();
        stage.close();
    }
    public void exit() {
        Stage stage = (Stage) ref_window.getScene().getWindow();
        stage.close();
    }

}
