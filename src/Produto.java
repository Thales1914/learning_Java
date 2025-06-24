import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Produto {
    private int idProduto;
    private String descProduto;
    private double valor;

    public Produto(int idProduto, String descProduto, double valor) {
        this.idProduto = idProduto;
        this.descProduto = descProduto;
        this.valor = valor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("%d;%s;%.2f", this.idProduto, this.descProduto, this.valor);
    }

    public void mostrar() {
        System.out.println("ID: " + this.idProduto + " | Descrição: " + this.descProduto + " | Valor: R$ " + String.format("%.2f", this.valor));
    }

    public boolean inserir(Scanner scanner) {
        int id;
        double valorProduto;
        try {
            System.out.print("Digite o ID do novo produto: ");
            id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite a descrição do produto: ");
            String desc = scanner.nextLine();
            System.out.print("Digite o valor do produto (ex: 12.50): ");
            valorProduto = scanner.nextDouble();
            scanner.nextLine();

            Produto novoProduto = new Produto(id, desc, valorProduto);

            try (BufferedWriter escrever = new BufferedWriter(new FileWriter("produtos.txt", true))) {
                escrever.write(novoProduto.toString().replace(",", "."));
                escrever.newLine();
                System.out.println("Produto inserido com sucesso!");
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo de produtos: " + e.getMessage());
                return false;
            }
        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada: ID deve ser número, valor deve ser número (use ponto para decimais).");
            if(scanner.hasNextLine()) scanner.nextLine();
            return false;
        }
    }

    public ArrayList<Produto> listar() {
        ArrayList<Produto> listaDeProdutos = new ArrayList<>();
        try (BufferedReader ler = new BufferedReader(new FileReader("produtos.txt"))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    Produto produtoDaLinha = new Produto(
                            Integer.parseInt(partes[0]),
                            partes[1],
                            Double.parseDouble(partes[2])
                    );
                    listaDeProdutos.add(produtoDaLinha);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao processar o arquivo de produtos: " + e.getMessage());
        }
        return listaDeProdutos;
    }

    public Produto consultar(int id) {
        for (Produto p : listar()) {
            if (p.getIdProduto() == id) {
                return p;
            }
        }
        return null;
    }
}