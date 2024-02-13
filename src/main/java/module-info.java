module projeto.banco.banco {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens projeto.banco.bank to javafx.fxml;
    exports projeto.banco.bank;
    exports projeto.banco.bank.View;
    exports projeto.banco.bank.Controller;
    opens projeto.banco.bank.Controller to javafx.fxml;
    opens projeto.banco.bank.View to javafx.fxml;
    exports projeto.banco.bank.Connection;
    opens projeto.banco.bank.Connection to javafx.fxml;
    opens projeto.banco.banco.img to javafx.fxml;



}