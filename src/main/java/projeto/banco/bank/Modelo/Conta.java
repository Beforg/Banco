package projeto.banco.bank.Modelo;

import java.math.BigDecimal;

public class Conta {
    private final String nome;
    private final String datas;
    private final String cpf;
    private final String endereco;
    private String id;
    private String conta;
    private final BigDecimal saldo;

    public Conta(String nome, String datas, String cpf, String endereco, String id, String conta, BigDecimal saldo) {
        this.nome = nome;
        this.datas = datas;
        this.cpf = cpf;
        this.endereco = endereco;
        this.id = id;
        this.conta = conta;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }


    public String getDatas() {
        return datas;
    }

    public String getCpf() {
        return cpf;
    }


    public String getEndereco() {
        return endereco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

}
