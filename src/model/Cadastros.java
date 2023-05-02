package model;

import repository.Repository;
import util.Formatadores;

public class Cadastros {
	Repository<Pessoa> repository;	
	
	public Cadastros(Repository<Pessoa> repository) {
		repository.salvar(new Pessoa("123.456.789-10","Luana Castro", "15777777777", Formatadores.stringParaData("12/12/2002")));
		repository.salvar(new Funcionario("123.456.789-30", "Tamires Carvalho", "42333333333", Formatadores.stringParaData("10/10/1990"), 1900.65));
		repository.salvar(new Gerente("123.456.789-20", "Tamires Carvalho", "42333333333", Formatadores.stringParaData("10/10/1990"), 3900.65, 1254, 10));
	}

}
