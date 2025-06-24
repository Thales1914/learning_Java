import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Reserva {
    private int idReserva;
    private double valor;
    private String dataEntrada;
    private String dataSaida;
    private boolean checkin;
    private boolean checkout;
    private Quarto quarto;
    private Hospede hospede;
    private static final String FILE_NAME = "reservas.txt";

    public Reserva(int idReserva, double valor, String dataEntrada, String dataSaida, boolean checkin, boolean checkout, Quarto quarto, Hospede hospede) {
        this.idReserva = idReserva;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.checkin = checkin;
        this.checkout = checkout;
        this.quarto = quarto;
        this.hospede = hospede;
    }

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(String dataEntrada) { this.dataEntrada = dataEntrada; }
    public String getDataSaida() { return dataSaida; }
    public void setDataSaida(String dataSaida) { this.dataSaida = dataSaida; }
    public boolean isCheckin() { return checkin; }
    public void setCheckin(boolean checkin) { this.checkin = checkin; }
    public boolean isCheckout() { return checkout; }
    public void setCheckout(boolean checkout) { this.checkout = checkout; }
    public Quarto getQuarto() { return quarto; }
    public void setQuarto(Quarto quarto) { this.quarto = quarto; }
    public Hospede getHospede() { return hospede; }
    public void setHospede(Hospede hospede) { this.hospede = hospede; }

    @Override
    public String toString() {
        return String.format("%d;%.2f;%s;%s;%b;%b;%d;%s",
                        this.idReserva, this.valor, this.dataEntrada, this.dataSaida,
                        this.checkin, this.checkout,
                        this.quarto.getIdQuarto(),
                        this.hospede.getCpf())
                .replace(",", ".");
    }

    public void mostrar() {
        System.out.println("--- DADOS DA RESERVA ---");
        System.out.println("ID Reserva: " + this.idReserva);
        System.out.println("Hóspede: " + (this.hospede != null ? this.hospede.getNome() + " (CPF: " + this.hospede.getCpf() + ")" : "N/A"));
        System.out.println("Quarto: " + (this.quarto != null ? this.quarto.getIdQuarto() + " - " + this.quarto.getDescQuarto() : "N/A"));
        System.out.println("Data Entrada: " + this.dataEntrada + " | Data Saída: " + this.dataSaida);
        System.out.println("Valor Total: R$ " + String.format("%.2f", this.valor));
        System.out.println("Check-in Realizado: " + (this.checkin ? "Sim" : "Não"));
        System.out.println("Check-out Realizado: " + (this.checkout ? "Sim" : "Não"));
        System.out.println("------------------------");
    }

    public boolean inserir(Scanner scanner) {
        try {
            Hospede hospedeManager = new Hospede(null,null,0,null,false);
            Quarto quartoManager = new Quarto(0,null);

            System.out.print("Digite o ID da nova reserva: ");
            int idReserva = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o CPF do Hóspede: ");
            String cpfHospede = scanner.nextLine();
            Hospede hospedeEncontrado = hospedeManager.consultar(cpfHospede);
            if (hospedeEncontrado == null) {
                System.err.println("Erro: Hóspede com CPF " + cpfHospede + " não encontrado. Cancele e cadastre o hóspede primeiro.");
                return false;
            }

            System.out.print("Digite o ID do Quarto: ");
            int idQuarto = scanner.nextInt();
            scanner.nextLine();
            Quarto quartoEncontrado = quartoManager.consultar(idQuarto);
            if (quartoEncontrado == null) {
                System.err.println("Erro: Quarto com ID " + idQuarto + " não encontrado. Cancele e cadastre o quarto primeiro.");
                return false;
            }

            System.out.print("Digite a Data de Entrada (dd/mm/aaaa): ");
            String dataEntrada = scanner.nextLine();
            System.out.print("Digite a Data de Saída (dd/mm/aaaa): ");
            String dataSaida = scanner.nextLine();
            System.out.print("Digite o valor total da reserva: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Check-in já realizado (true/false)? ");
            boolean checkin = scanner.nextBoolean();
            scanner.nextLine();
            System.out.print("Check-out já realizado (true/false)? ");
            boolean checkout = scanner.nextBoolean();
            scanner.nextLine();

            Reserva novaReserva = new Reserva(idReserva, valor, dataEntrada, dataSaida, checkin, checkout, quartoEncontrado, hospedeEncontrado);

            try (BufferedWriter escrever = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                escrever.write(novaReserva.toString());
                escrever.newLine();
                System.out.println("Reserva inserida com sucesso!");
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo de reservas: " + e.getMessage());
                return false;
            }

        } catch (InputMismatchException e) {
            System.err.println("Erro de entrada. ID, Valor, Checkin/Checkout devem estar nos formatos corretos.");
            if(scanner.hasNextLine()) scanner.nextLine();
            return false;
        }
    }

    public ArrayList<Reserva> listar() {
        ArrayList<Reserva> listaDeReservas = new ArrayList<>();
        Hospede hospedeManager = new Hospede(null,null,0,null,false);
        Quarto quartoManager = new Quarto(0,null);

        try (BufferedReader ler = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 8) {
                    Quarto quartoDaReserva = null;
                    Hospede hospedeDaReserva = null;

                    try {
                        int idQuarto = Integer.parseInt(partes[6]);
                        quartoDaReserva = quartoManager.consultar(idQuarto);
                    } catch (NumberFormatException nfe) {
                        System.err.println("Aviso: ID de quarto inválido na reserva " + partes[0] + " (valor: '" + partes[6] + "'). Não é um número válido.");
                    }

                    String cpfHospede = partes[7];
                    hospedeDaReserva = hospedeManager.consultar(cpfHospede);

                    if (quartoDaReserva != null && hospedeDaReserva != null) {
                        Reserva reservaDaLinha = new Reserva(
                                Integer.parseInt(partes[0]),
                                Double.parseDouble(partes[1]),
                                partes[2],
                                partes[3],
                                Boolean.parseBoolean(partes[4]),
                                Boolean.parseBoolean(partes[5]),
                                quartoDaReserva,
                                hospedeDaReserva
                        );
                        listaDeReservas.add(reservaDaLinha);
                    } else {
                        System.err.println("Aviso: Reserva com ID " + partes[0] + " não pôde ser carregada completamente pois o hóspede ou quarto associado não existe mais ou tem ID inválido no arquivo.");
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            System.err.println("Erro ao processar o arquivo de reservas: " + e.getMessage());
        }
        return listaDeReservas;
    }

    public Reserva consultar(int id) {
        for (Reserva r : listar()) {
            if (r.getIdReserva() == id) {
                return r;
            }
        }
        return null;
    }
}