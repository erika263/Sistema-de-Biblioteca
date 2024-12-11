package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author erika263
 * @description An library application management system for control books inventory
 * email: erika.contax@gmail.com
 */
public class AplicacaoLivros {
	private static final String FILE_NAME = "livros.txt";
	private static List<Livro> livros = new ArrayList<>();

	public static void main(String[] args) {
		try {
			carregarLivros();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n1. Adicionar | 2. Listar | 3. Editar | 4. Excluir | 5. Sair");
			String opcao = scanner.nextLine();

			switch (opcao) {
			case "1":
				adicionarLivro(scanner);
				break;
			case "2":
				listarLivros();
				break;
			case "3":
				editarLivro(scanner);
				break;
			case "4":
				excluirLivro(scanner);
				break;
			case "5":
				salvarLivros();
				return;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		}
	}

	private static void adicionarLivro(Scanner scanner) {
		System.out.print("Título: ");
		String titulo = scanner.nextLine();
		System.out.print("Autor: ");
		String autor = scanner.nextLine();
		livros.add(new Livro(titulo, autor));
		System.out.println("Livro adicionado.");
	}

	private static void listarLivros() {
		if (livros.isEmpty()) {
			System.out.println("Nenhum livro cadastrado.");
		} else {
			for (int i = 0; i < livros.size(); i++) {
				System.out.println((i + 1) + ". " + livros.get(i));
			}
		}
	}

	private static void editarLivro(Scanner scanner) {
		listarLivros();
		System.out.print("Número do livro para editar: ");
		int index = Integer.parseInt(scanner.nextLine()) - 1;
		if (index >= 0 && index < livros.size()) {
			System.out.print("Novo título: ");
			livros.get(index).setTitulo(scanner.nextLine());
			System.out.print("Novo autor: ");
			livros.get(index).setAutor(scanner.nextLine());
			System.out.println("Livro atualizado.");
		}
	}

	private static void excluirLivro(Scanner scanner) {
		listarLivros();
		System.out.print("Número do livro para excluir: ");
		int index = Integer.parseInt(scanner.nextLine()) - 1;
		if (index >= 0 && index < livros.size()) {
			livros.remove(index);
			System.out.println("Livro excluído.");
		}
	}

	private static void salvarLivros() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Livro livro : livros) {
				writer.write(livro.getTitulo() + "," + livro.getAutor());
				writer.newLine();
			}
			System.out.println("Livros salvos.");
		} catch (IOException e) {
			System.out.println("Erro ao salvar livros.");
		}
	}

	private static void carregarLivros() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				String[] dados = linha.split(",");
				livros.add(new Livro(dados[0], dados[1]));
			}
		} catch (IOException e) {
			// Se o arquivo não existir, não há problema
			throw new IOException("Arquivo txt não existe");
		}
	}
}
