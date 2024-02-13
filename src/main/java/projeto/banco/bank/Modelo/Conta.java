package projeto.banco.bank.Modelo;

import java.math.BigDecimal;

public class Conta {
    private String nome;
    private String datas;
    private String cpf;
    private String endereco;
    private String id;
    private String conta;
    private BigDecimal saldo;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
