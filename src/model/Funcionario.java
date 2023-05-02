package model;

import java.util.Date;

public class Funcionario extends Pessoa {

	Date hoje = new Date();
	double salario;

	public Funcionario(String cpf, String nome, String telefone, Date dataNascimento, double salario) {
		super(cpf, nome, telefone, dataNascimento);
		this.salario = salario;
		this.dataCadastro = hoje;
		this.dataAlteracao = hoje;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		return super.toString()
				+ " | Salário: R$" + String.format("%.2f", this.salario);
	}	

}
