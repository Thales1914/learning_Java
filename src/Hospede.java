import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hospede extends Pessoa {
    private String rg;
    private boolean fidelidade;

    public Hospede(String cpf, String nome, int idade, String rg, boolean fidelidade) {
        super(cpf, nome, idade);
        this.rg = rg;
        this.fidelidade = fidelidade;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public boolean isFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(boolean fidelidade) {
        this.fidelidade = fidelidade;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%d;%s;%b", getCpf(), getNome(), getIdade(), getRg(), isFidelidade());
    }

    @Override
    public void mostrar() {
        System.out.println("--- DADOS DO HÓSPEDE ---");
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("RG: " + getRg());
        System.out.println("Programa de Fidelidade: " + (isFidelidade() ? "Ativo" : "Inativo"));
        System.out.println("------------------------");
    }

    @Override
    public boolean inserir(Scanner scanner) {
        try {
            System.out.print("Digite o CPF do hóspede: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite o nome do hóspede: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a idade do hóspede: ");
            int idade = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o RG do hóspede: ");
            String rg = scanner.nextLine();
            System.out.print("É cliente fidelidade (true/false)? ");
            boolean fidelidade = scanner.nextBoolean();
            scanner.nextLine();

            Hospede novoHospede = new Hospede(cpf, nome, idade, rg, fidelidade);

            try (BufferedWriter escrever = new BufferedWriter(new FileWriter("hospedes.txt", true))) {
                escrever.write(novoHospede.toString());
                escrever.newLine();
                System.out.println("Hóspede inserido com sucesso!");
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao salvar hóspede: " + e.getMessage());
                return false;
            }
        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada. Verifique os tipos de dados (idade deve ser número, fidelidade 'true' ou 'false').");
            if(scanner.hasNextLine()) scanner.nextLine();
            return false;
        }
    }

    @Override
    public ArrayList<Hospede> listar() {
        ArrayList<Hospede> hospedes = new ArrayList<>();
        try (BufferedReader ler = new BufferedReader(new FileReader("hospedes.txt"))) {
            String line;
            while ((line = ler.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    Hospede h = new Hospede(
                            parts[0],
                            parts[1],
                            Integer.parseInt(parts[2]),
                            parts[3],
                            Boolean.parseBoolean(parts[4])
                    );
                    hospedes.add(h);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Erro ao ler ou processar arquivo de hóspedes: " + e.getMessage());
        }
        return hospedes;
    }

    @Override
    public Hospede consultar(String cpf) {
        for (Hospede h : listar()) {
            if (h.getCpf().equals(cpf)) {
                return h;
            }
        }
        return null;
    }
}