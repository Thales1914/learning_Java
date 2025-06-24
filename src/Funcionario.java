import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Funcionario extends Pessoa {
    private String funcao;

    public Funcionario(String cpf, String nome, int idade, String funcao) {
        super(cpf, nome, idade);
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%d;%s", getCpf(), getNome(), getIdade(), getFuncao());
    }

    @Override
    public void mostrar() {
        System.out.println("--- DADOS DO FUNCIONÁRIO ---");
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Função: " + getFuncao());
        System.out.println("----------------------------");
    }

    @Override
    public boolean inserir(Scanner scanner) {
        try {
            System.out.print("Digite o CPF do funcionário: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite o nome do funcionário: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a idade do funcionário: ");
            int idade = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite a função do funcionário: ");
            String funcao = scanner.nextLine();

            Funcionario novoFuncionario = new Funcionario(cpf, nome, idade, funcao);

            try (BufferedWriter escrever = new BufferedWriter(new FileWriter("funcionarios.txt", true))) {
                escrever.write(novoFuncionario.toString());
                escrever.newLine();
                System.out.println("Funcionário inserido com sucesso!");
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
                return false;
            }
        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada: A idade deve ser um número.");
            if(scanner.hasNextLine()) scanner.nextLine();
            return false;
        }
    }

    @Override
    public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (BufferedReader ler = new BufferedReader(new FileReader("funcionarios.txt"))) {
            String line;
            while ((line = ler.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    Funcionario f = new Funcionario(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
                    funcionarios.add(f);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler ou formatar o arquivo de funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    @Override
    public Funcionario consultar(String cpf) {
        ArrayList<Funcionario> todos = listar();
        for (Funcionario f : todos) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }
}