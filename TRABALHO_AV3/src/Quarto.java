import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Quarto {
    private int idQuarto;
    private String descQuarto;

    public Quarto(int idQuarto, String descQuarto) {
        this.idQuarto = idQuarto;
        this.descQuarto = descQuarto;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public String getDescQuarto() {
        return descQuarto;
    }

    public void setDescQuarto(String descQuarto) {
        this.descQuarto = descQuarto;
    }

    @Override
    public String toString() {
        return String.format("%d;%s", this.idQuarto, this.descQuarto);
    }

    public void mostrar() {
        System.out.println("ID: " + this.idQuarto + " | Descrição: " + this.descQuarto);
    }

    public boolean inserir(Scanner scanner) {
        try {
            System.out.print("Digite o ID (número) do novo quarto: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite a descrição do quarto (Ex: Suíte Master com Varanda): ");
            String desc = scanner.nextLine();

            Quarto novoQuarto = new Quarto(id, desc);

            try (BufferedWriter escrever = new BufferedWriter(new FileWriter("quartos.txt", true))) {
                escrever.write(novoQuarto.toString());
                escrever.newLine();
                System.out.println("Quarto inserido com sucesso!");
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo de quartos: " + e.getMessage());
                return false;
            }
        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada: O ID deve ser um número.");
            if(scanner.hasNextLine()) scanner.nextLine();
            return false;
        }
    }

    public ArrayList<Quarto> listar() {
        ArrayList<Quarto> listaDeQuartos = new ArrayList<>();
        try (BufferedReader ler = new BufferedReader(new FileReader("quartos.txt"))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    Quarto quartoDaLinha = new Quarto(
                            Integer.parseInt(partes[0]),
                            partes[1]
                    );
                    listaDeQuartos.add(quartoDaLinha);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao processar o arquivo de quartos: " + e.getMessage());
        }
        return listaDeQuartos;
    }

    public Quarto consultar(int id) {
        for (Quarto q : listar()) {
            if (q.getIdQuarto() == id) {
                return q;
            }
        }
        return null;
    }
}