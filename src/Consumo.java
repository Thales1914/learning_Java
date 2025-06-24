import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Consumo {
    private int idConsumo;
    private Produto produto;
    private Reserva reserva;
    private double quantidade;
    private static final String FILE_NAME = "consumos.txt";

    public Consumo(int idConsumo, Produto produto, Reserva reserva, double quantidade) {
        this.idConsumo = idConsumo;
        this.produto = produto;
        this.reserva = reserva;
        this.quantidade = quantidade;
    }

    public int getIdConsumo() { return idConsumo; }
    public void setIdConsumo(int idConsumo) { this.idConsumo = idConsumo; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }
    public double getQuantidade() { return quantidade; }
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        String produtoIdStr = (this.produto != null) ? String.valueOf(this.produto.getIdProduto()) : "null";
        String reservaIdStr = (this.reserva != null) ? String.valueOf(this.reserva.getIdReserva()) : "null";

        return String.format("%d;%s;%s;%.2f",
                        this.idConsumo, produtoIdStr, reservaIdStr, this.quantidade)
                .replace(",", ".");
    }

    public void mostrar() {
        System.out.println("--- DADOS DO CONSUMO ---");
        System.out.println("ID Consumo: " + this.idConsumo);
        System.out.println("Reserva Associada (ID): " + (this.reserva != null ? this.reserva.getIdReserva() + " - Hóspede: " + (this.reserva.getHospede() != null ? this.reserva.getHospede().getNome() : "N/A") : "N/A"));
        System.out.println("Produto: " + (this.produto != null ? this.produto.getDescProduto() : "N/A"));
        System.out.println("Quantidade: " + this.quantidade);
        System.out.println("Valor do Item: R$ " + String.format("%.2f", (this.produto != null ? (this.quantidade * this.produto.getValor()) : 0.0)));
        System.out.println("------------------------");
    }

    public boolean inserir(Scanner scanner) {
        try {
            Reserva reservaManager = new Reserva(0,0,null,null,false,false,null,null);
            Produto produtoManager = new Produto(0,null,0);

            System.out.print("Digite o ID do novo consumo: ");
            int idConsumo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o ID da Reserva associada: ");
            int idReserva = scanner.nextInt();
            scanner.nextLine();
            Reserva reservaEncontrada = reservaManager.consultar(idReserva);
            if (reservaEncontrada == null) {
                System.err.println("Erro: Reserva com ID " + idReserva + " não encontrada.");
                return false;
            }
            if (reservaEncontrada.isCheckout()) {
                System.err.println("Erro: Não é possível adicionar consumo a uma reserva com check-out já realizado.");
                return false;
            }

            System.out.print("Digite o ID do Produto consumido: ");
            int idProduto = scanner.nextInt();
            scanner.nextLine();
            Produto produtoEncontrado = produtoManager.consultar(idProduto);
            if (produtoEncontrado == null) {
                System.err.println("Erro: Produto com ID " + idProduto + " não encontrado.");
                return false;
            }

            System.out.print("Digite a quantidade: ");
            double quantidade = scanner.nextDouble();
            scanner.nextLine();

            Consumo novoConsumo = new Consumo(idConsumo, produtoEncontrado, reservaEncontrada, quantidade);

            try (BufferedWriter escrever = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                escrever.write(novoConsumo.toString());
                escrever.newLine();
                System.out.println("Consumo registrado com sucesso!");
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo de consumos: " + e.getMessage());
                return false;
            }

        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada. IDs e quantidade devem ser números.");
            if(scanner.hasNextLine()) scanner.nextLine();
            return false;
        }
    }

    public ArrayList<Consumo> listar() {
        ArrayList<Consumo> listaDeConsumos = new ArrayList<>();
        Reserva reservaManager = new Reserva(0,0,null,null,false,false,null,null);
        Produto produtoManager = new Produto(0,null,0);

        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    Produto produtoDoConsumo = null;
                    Reserva reservaDoConsumo = null;

                    try {
                        int idProduto = Integer.parseInt(partes[1]);
                        produtoDoConsumo = produtoManager.consultar(idProduto);
                    } catch (NumberFormatException nfe) {
                        System.err.println("Aviso: ID de produto inválido no consumo " + partes[0] + " (valor: '" + partes[1] + "'). Não é um número válido.");
                    }

                    try {
                        int idReserva = Integer.parseInt(partes[2]);
                        reservaDoConsumo = reservaManager.consultar(idReserva);
                    } catch (NumberFormatException nfe) {
                        System.err.println("Aviso: ID de reserva inválido no consumo " + partes[0] + " (valor: '" + partes[2] + "'). Não é um número válido.");
                    }

                    if (produtoDoConsumo != null && reservaDoConsumo != null) {
                        Consumo consumoDaLinha = new Consumo(
                                Integer.parseInt(partes[0]),
                                produtoDoConsumo,
                                reservaDoConsumo,
                                Double.parseDouble(partes[3])
                        );
                        listaDeConsumos.add(consumoDaLinha);
                    } else {
                        System.err.println("Aviso: Consumo com ID " + partes[0] + " não pôde ser carregado completamente pois o produto ou reserva associada não existe mais ou tem ID inválido no arquivo.");
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            System.err.println("Erro ao processar o arquivo de consumos: " + e.getMessage());
        }
        return listaDeConsumos;
    }

    public Consumo consultar(int id) {
        for (Consumo c : listar()) {
            if (c.getIdConsumo() == id) {
                return c;
            }
        }
        return null;
    }
}