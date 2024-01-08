package projeto.banco.banco.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewControl {
    /*Controle das telas*/

    public ViewControl() {
    }
    public void showMain() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/banco/banco/view/main.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

        loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Bank Application");
        stage.show();


    }

}
