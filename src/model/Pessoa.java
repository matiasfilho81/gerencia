package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import bancoDados.Banco;
import util.Contador;
import util.Formatadores;

public class Pessoa implements Banco {
	
	public Integer id;
	public String cpf;
	public String nome;
	public String telefone;
	public Date dataNascimento;
	public Date dataCadastro;
	public Date dataAlteracao;

	Date hoje = new Date();
	SimpleDateFormat sdf;
	
	public Pessoa(String cpf, String nome, String telefone, Date dataNascimento) {
		this.id = Contador.proximoId();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = hoje;
		this.dataAlteracao = hoje;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return this.id
				+ " | "	+ this.nome
				+ " | Telefone: " + Formatadores.formataFone(this.telefone)
				+ " | Data Nasc.: " + Formatadores.formataNascimento(this.dataNascimento)
				+ " | Cadastrado em: " + Formatadores.formataData(this.dataCadastro)
				+ " | Última Alteração: " + Formatadores.formataData(this.dataAlteracao);
	}

}
