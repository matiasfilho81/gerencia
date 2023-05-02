package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Gerente extends Funcionario {

    private int senha;
    private int numeroDeFuncionarios;

    Date hoje = new Date();
	SimpleDateFormat sdf;

    public Gerente(String cpf, String nome, String telefone, Date dataNascimento, double salario, int senha, int numero) {
        super(cpf, nome, telefone, dataNascimento, salario);
        this.senha = senha;
        this.numeroDeFuncionarios = numero;
		this.dataCadastro = hoje;
		this.dataAlteracao = hoje;
    }

    public boolean autenticar(int senha){
        if(this.senha == senha){
            System.out.println("Acesso permitido!");
            return true;
        } else {
            System.out.println("Acesso negado!");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Gerente [numeroDeFuncionarios=" + numeroDeFuncionarios + "]";
    }

    public int getSenha() {
        return senha;
    }
    public void setSenha(int senha) {
        this.senha = senha;
    }
    public int getNumeroDeFuncionarios() {
        return numeroDeFuncionarios;
    }
    public void setNumeroDeFuncionarios(int numeroDeFuncionarios) {
        this.numeroDeFuncionarios = numeroDeFuncionarios;
    }
}
