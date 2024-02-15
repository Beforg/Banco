package projeto.banco.bank.Modelo;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.View.ViewControl;
import projeto.banco.bank.Connection.UpdateDAO;
import javafx.collections.FXCollections;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class InitializeConfig {
    public static void apagarContaUser(Conta contaInfos, ViewControl viewControl, UpdateDAO updateDAO, Label nome_cliente) {
        if (!contaInfos.getSaldo().equals(BigDecimal.ZERO)) {
            ShowMessage.error("Atenção", "Erro", "Não é possível apagar a conta com saldo positivo!");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Apagar conta");
            alert.setContentText("Deseja realmente apagar a conta?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                updateDAO.apagar_conta(contaInfos.getCpf());
                viewControl.showLogout();
                ViewControl.isOpen = false;
                Stage stage = (Stage) nome_cliente.getScene().getWindow();
                stage.close();

            }

        }

    }
    public static void configuraTabelaHistorico(TableColumn<HistoricoTransferencia, LocalDateTime> data,
                                                TableColumn<HistoricoTransferencia, String> origem,
                                                TableColumn<HistoricoTransferencia, String> destino,
                                                TableColumn<HistoricoTransferencia, Double> valor,
                                                TableColumn<HistoricoTransferencia, Integer> id,
                                                TableView<HistoricoTransferencia> hist_transf,
                                                UpdateDAO updateDAO, Conta contaInfos) {
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        origem.setCellValueFactory(new PropertyValueFactory<>("origem"));
        destino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        valor.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {
                    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                    setText(nf.format(item));
                }
            }
        });
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        ObservableList<HistoricoTransferencia> historico = FXCollections.observableArrayList(updateDAO.getTransferencias(contaInfos.getConta()));
        hist_transf.setItems(historico);
    }
    public static void configuraInformacoesConta(Conta contaInfos,
                                                 Label nome_cliente,
                                                 Label cliente_conta,
                                                 Label cpf_conta,
                                                 Label data_conta,
                                                 Label ender_conta,
                                                 Label id_conta,
                                                 Label num_conta,
                                                 Label saldo_cliente,
                                                 Label numero_card_conta,
                                                 Label nome_card_conta) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String valorFormatado = nf.format(contaInfos.getSaldo());

        nome_cliente.setText(contaInfos.getNome());
        cliente_conta.setText(contaInfos.getNome());
        cpf_conta.setText(contaInfos.getCpf());
        data_conta.setText(contaInfos.getDatas());
        ender_conta.setText(contaInfos.getEndereco());
        id_conta.setText(contaInfos.getId());
        num_conta.setText(contaInfos.getConta());
        saldo_cliente.setText(valorFormatado);
        numero_card_conta.setText(contaInfos.getConta());
        nome_card_conta.setText(contaInfos.getNome());
    }
    public static void imgBotao(Image image, Button bt_att) {
        bt_att.setGraphic(new ImageView(image));
    }
}
