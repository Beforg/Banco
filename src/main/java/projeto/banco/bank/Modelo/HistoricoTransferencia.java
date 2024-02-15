package projeto.banco.bank.Modelo;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistoricoTransferencia {
    private Integer id;
    private LocalDateTime data;
    private String origem;
    private String destino;
    private double valor;

    public HistoricoTransferencia(Integer id, LocalDateTime data, String origem, String destino, double valor) {
        this.id = id;
        this.data = data;
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
