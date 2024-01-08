package projeto.banco.bank.Model;

import java.math.BigDecimal;

public class Conta {
    private String nome;
    private String datas;
    private String cpf;
    private String endereco;
    private String password;
    private Integer conta;
    private BigDecimal saldo;

    public Conta(String nome, String datas, String cpf, String endereco, String password, Integer conta, BigDecimal saldo) {
        this.nome = nome;
        this.datas = datas;
        this.cpf = cpf;
        this.endereco = endereco;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
