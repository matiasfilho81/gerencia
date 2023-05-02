package service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exception.SistemaException;
import model.Cadastros;
import model.Funcionario;
import model.Pessoa;
import repository.Repository;
import util.Formatadores;
import util.Strings;

public class Service {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Repository<Pessoa> repository = new Repository<>();
	Cadastros cadastros = new Cadastros(repository);
	Scanner sc;

	public Service(Scanner sc) {
		this.sc = sc;
	}

	public void criarCadastro() {
		System.out.println(Strings.CADASTRAMENTO.toString());
		String nome = receberNome();
		String telefone = receberTelefone();
		Date dataNascimento = receberDataNascimento();
		String cpf = null;
		Pessoa pessoa = null;
		String temSalario = "";
		do {
			System.out.println(Strings.TEM_SALARIO.toString());
			temSalario = sc.nextLine().toUpperCase();
			if (temSalario.equals("N")) {
				pessoa = new Pessoa(cpf, nome, telefone, dataNascimento);
				break;
			} else if (temSalario.equals("S")) {
				Double salario = receberSalario();
				pessoa = new Funcionario(cpf, nome, telefone, dataNascimento, salario);
				break;
			}
		} while (!temSalario.equals("N") && !temSalario.equals("S"));
		this.repository.salvar(pessoa);
		System.out.println(Strings.CADASTRADO.toString() + pessoa);
	}

	private String receberNome() {
		System.out.println(Strings.NOME.toString());
		String nome = sc.nextLine();
		return nome;
	}

	private String receberTelefone() {
		String telefone = "";
		while (!telefone.matches("[0-9]+")
				|| telefone.length() > 11
				|| telefone.length() < 11) {
			System.out.println(Strings.TELEFONE.toString());
			telefone = sc.nextLine().replaceFirst("0", "");
		}
		return telefone;
	}

	private Date receberDataNascimento() {
		String data = "";
		Date dataNascimento = null;
		boolean continua = true;
		while (continua == true)
			try {
				dataNascimento = sdf.parse(data);
				continua = false;
			} catch (Exception e) {
				System.out.println(Strings.NASCIMENTO.toString());
				data = sc.next();
			}
		return dataNascimento;
	}

	private Double receberSalario() {
		String salarioString = "";
		double salarioDouble = -1;
		while (salarioDouble == -1) {
			if (Formatadores.isNumeric(salarioString)
					&& (Double.parseDouble(salarioString) >= 0
							&& Double.parseDouble(salarioString) <= 10)) {
				salarioDouble = Double.parseDouble(salarioString);
			} else {
				System.out.println(Strings.SALARIO.toString());
				salarioString = sc.nextLine().replace(",", ".");
			}
		}
		return salarioDouble;
	}

	public void listarCadastros() throws SistemaException {
		List<Pessoa> pessoas = this.ordenarStream(this.repository.buscarTodos())
				.collect(Collectors.toList());

		System.out.println(Strings.MENU_CADASTROS.toString());
		int opcao = sc.nextInt();
		if (opcao < 0 || opcao > 3) {
			throw new SistemaException(Strings.OPCAO_INVALIDA.toString());
		} else if (opcao == 0) {
			throw new SistemaException(Strings.RETORNANDO_MENU.toString());
		}
		if (opcao != 0) {
			System.out.println(Strings.LISTAGEM.toString());
		}
		if (opcao == 1) {
			pessoas.stream().forEach(pessoa -> System.out.println(pessoa));
		} else if (opcao == 2) {
			pessoas.stream().filter(pessoa -> pessoa instanceof Funcionario)
					.forEach(funcionario -> System.out.println(funcionario));
		} else if (opcao == 3) {
			pessoas.stream().filter(pessoa -> !(pessoa instanceof Funcionario))
					.forEach(pessoa -> System.out.println(pessoa));
		}
		this.atualizarDados(this.receberId());
	}

	private Stream<Pessoa> ordenarStream(List<Pessoa> list) {
		Stream<Pessoa> ordenadas = this.repository.buscarTodos()
				.stream().sorted(Comparator.comparing(Pessoa::getNome, Comparator
						.comparing((String s) -> !s.equals("None"))
						.thenComparing(Comparator.naturalOrder())));

		return ordenadas;
	}

	public int receberId() throws SistemaException {
		System.out.println(Strings.INFORME_ID.toString());
		int id = sc.nextInt();
		if (id == 0) {
			throw new SistemaException(Strings.RETORNANDO_MENU.toString());
		}
		return id;
	}

	public void pesquisarPorNome() throws SistemaException {
		System.out.println(Strings.INFORME_NOME.toString());
		String fragmentoNome = sc.next().toLowerCase();

		List<Pessoa> pessoas = this.ordenarStream(this.repository.buscarTodos())
				.filter(pessoa -> pessoa.getNome().toLowerCase().contains(fragmentoNome))
				.collect(Collectors.toList());

		if (pessoas.size() > 1) {
			pessoas.forEach(pessoa -> System.out.println(pessoa));
			this.atualizarDados(this.receberId());
		} else if (pessoas.size() == 1) {
			this.atualizarDados(pessoas.get(0).getId());
		} else if (pessoas.isEmpty()) {
			throw new SistemaException(Strings.SEM_OCORRENCIA.toString()
					+ fragmentoNome + Strings.SEM_OCORRENCIA_2);
		}
	}

	public void atualizarDados(int id) throws SistemaException {
		Pessoa pessoa = this.verificarIdRepository(id);

		int opcaoMenu;
		boolean continua = true;
		boolean virouFuncionario = false;
		while (continua == true) {
			System.out.println(Strings.CABECALHO_ATUALIZAR.toString()
					+ pessoa
					+ Strings.OPCOES_ATUALIZAR);
			opcaoMenu = sc.nextInt();
			sc.nextLine();
			if (opcaoMenu == 1) {
				pessoa.setNome(receberNome());
			} else if (opcaoMenu == 2) {
				pessoa.setTelefone(receberTelefone());
			} else if (opcaoMenu == 3) {
				pessoa.setDataNascimento(receberDataNascimento());
			} else if (opcaoMenu == 4) {
				if (pessoa instanceof Funcionario) {
					Funcionario funcionario = (Funcionario) pessoa;
					funcionario.setSalario(receberSalario());
				} else {
					Funcionario funcionario = this.pessoaParaFuncionario(pessoa, this.receberSalario());
					virouFuncionario = true;
					pessoa = funcionario;
				}
			} else if (opcaoMenu == 5) {
				this.deletarPessoa(pessoa.getId());
				break;
			} else if (opcaoMenu == 0) {
				continua = false;
				throw new SistemaException(Strings.RETORNANDO_MENU.toString());
			} else if (opcaoMenu < 0 || opcaoMenu > 5) {
				System.out.println(Strings.OPCAO_DESEJADA.toString());
			}
			if (virouFuncionario == true) {
				System.out.println(pessoa.getNome() + Strings.VIROU_funcionario.toString());
				virouFuncionario = false;
			} else {
				System.out.println(Strings.ATUALIZADO.toString());
			}
			pessoa.setDataAlteracao(new Date());
		}
	}

	private Pessoa verificarIdRepository(int id) throws SistemaException {
		Pessoa pessoa = this.repository.buscarPorId(id);
		if (pessoa == null) {
			throw new SistemaException(Strings.NAO_ENCONTRADO.toString());
		}
		return pessoa;
	}

	private Funcionario pessoaParaFuncionario(Pessoa pessoa, double salario) {
		Pessoa funcionario = new Funcionario(pessoa.getCpf(), pessoa.getNome(), pessoa.getTelefone(), pessoa.getDataNascimento(), salario);
		this.repository.salvar(funcionario);
		funcionario.setDataCadastro(pessoa.getDataCadastro());
		this.repository.removerPorId(pessoa.getId());
		System.out.println(Strings.NOVO_FUNCIONARIO.toString());
		return (Funcionario) funcionario;
	}

	private void deletarPessoa(int id) throws SistemaException {
		Pessoa pessoa = this.verificarIdRepository(id);

		System.out.println(Strings.REMOVER_CADASTRO.toString()
				+ pessoa.getNome() + Strings.REMOVER_CADASTRO_2.toString());
		String remover = sc.next().toUpperCase();
		if (remover.equals("N")) {
			System.out.println(Strings.RETORNANDO_MENU.toString());
		} else if (remover.equals("S")) {
			repository.removerPorId(id);
			System.out.println(Strings.REMOVIDO.toString());
		} else {
			throw new SistemaException(Strings.OPCAO_INVALIDA_RETORNANDO.toString());
		}
	}

}