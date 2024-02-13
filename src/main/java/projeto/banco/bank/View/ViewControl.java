package projeto.banco.bank.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewControl {
    /*Controle das telas*/
    public boolean isOpen = true;
    public ViewControl() {
    }
    /*Mostra a tela principal*/
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
    /*Mostra a tela de cadastro*/
    public void showCadastro(){
        Stage stage = new Stage();
        if (isOpen) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/banco/banco/view/cadastro.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }

            stage.setScene(scene);
            stage.setTitle("Bank: Cadastro");
            stage.show();
            stage.setOnCloseRequest(e -> isOpen = true);

        }
    }
    public void showDepositar(){
        Stage stage = new Stage();
        if (isOpen) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/banco/banco/view/depositar.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }

            stage.setScene(scene);
            stage.setTitle("Bank: Depositar");
            stage.show();
            stage.setResizable(false);
            stage.setOnCloseRequest(e -> isOpen = true);

        }
    }

        public void showTranferir(){
        Stage stage = new Stage();
        if (isOpen) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/banco/banco/view/transfer.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }

            stage.setScene(scene);
            stage.setTitle("Bank: Transferir");
            stage.show();
            stage.setResizable(false);
            stage.setOnCloseRequest(e -> isOpen = true);

        }
    }

    public void showLogin(){
        Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/banco/banco/view/login.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }

            stage.setScene(scene);
            stage.setTitle("Bank: Cadastro");
            stage.show();
            stage.setOnCloseRequest(e -> isOpen = true);


    }
    public void showLogout() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/banco/banco/view/logout.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle("Bank: Logout");
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(e -> isOpen = true);
    }
}
