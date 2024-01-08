module projeto.banco.banco {
    requires javafx.controls;
    requires javafx.fxml;


    opens projeto.banco.banco to javafx.fxml;
    exports projeto.banco.banco;
}