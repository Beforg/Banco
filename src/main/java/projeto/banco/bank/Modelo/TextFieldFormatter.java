package projeto.banco.bank.Modelo;
import javafx.scene.control.TextField;

public class TextFieldFormatter {
    public static void cpfField(TextField cpf) {
        cpf.setOnKeyTyped(event -> {
            String string = cpf.getText();
            if (string.length() == 3 || string.length() == 7) {
                cpf.setText(string + ".");
                cpf.positionCaret(string.length() + 1);
            }
            if (string.length() == 11) {
                cpf.setText(string + "-");
                cpf.positionCaret(string.length() + 1);
            }
            if (string.length() > 14) {
                cpf.setText(string.substring(0, 14));
                cpf.positionCaret(14);
            }
        });
    }
    public static void dataField(TextField data){
        data.setOnKeyTyped(event -> {
            String string = data.getText();
            if (string.length() == 2 || string.length() == 5) {
                data.setText(string + "/");
                data.positionCaret(string.length() + 1);
            }
            if (string.length() > 9) {
                data.setText(string.substring(0, 10));
                data.positionCaret(10);
            }
        });
    }
    public static void numContaField(TextField numConta){
        numConta.setOnKeyTyped(event -> {
            String string = numConta.getText();
            if (string.length() == 4) {
                numConta.setText(string + " ");
                numConta.positionCaret(string.length() + 1);
            }
            if (string.length() == 9) {
                numConta.setText(string + " ");
                numConta.positionCaret(string.length() + 1);
            }
            if (string.length() == 14) {
                numConta.setText(string + " ");
                numConta.positionCaret(string.length() + 1);
            }
            if (string.length() > 19) {
                numConta.setText(string.substring(0, 19));
                numConta.positionCaret(19);
            }
        });
    }
    public static void formatarMonetario(TextField tf) {
        tf.setOnKeyTyped(event -> {
            String string = tf.getText();
            if (string.length() == 1) {
                tf.setText("R$ " + string);
                tf.positionCaret(string.length() + 3);
            }
            if (string.length() > 14) {
                tf.setText(string.substring(0, 14));
                tf.positionCaret(14);
            }
        });
    }
}
