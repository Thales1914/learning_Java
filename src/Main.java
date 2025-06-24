import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scannerPrincipal = new Scanner(System.in);

    public static void main(String[] args) {
        Funcionario funcionarioManager = new Funcionario(null, null, 0, null);
        Hospede hospedeManager = new Hospede(null, null, 0, null, false);
        Quarto quartoManager = new Quarto(0, null);
        Produto produtoManager = new Produto(0, null, 0);
        Reserva reservaManager = new Reserva(0,0,null,null,false,false,null,null);
        Consumo consumoManager = new Consumo(0,null,null,0);

        int opcao;
        do {
            exibirMenuAjustado();
            try {
                opcao = scannerPrincipal.nextInt();
                scannerPrincipal.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- Inserindo Novo Hóspede ---");
                        hospedeManager.inserir(scannerPrincipal);
                        break;
                    case 2:
                        System.out.println("\n--- Lista de Hóspedes ---");
                        ArrayList<Hospede> hospedes = hospedeManager.listar();
                        if (hospedes.isEmpty()) System.out.println("Nenhum hóspede cadastrado.");
                        else hospedes.forEach(Hospede::mostrar);
                        break;
                    case 3:
                        System.out.println("\n--- Consultando Hóspede ---");
                        System.out.print("Digite o CPF do Hóspede: ");
                        String cpfHospede = scannerPrincipal.nextLine();
                        Hospede h = hospedeManager.consultar(cpfHospede);
                        if(h != null) h.mostrar();
                        else System.out.println("Hóspede não encontrado.");
                        break;
                    case 4:
                        System.out.println("\n--- Inserindo Novo Funcionário ---");
                        funcionarioManager.inserir(scannerPrincipal);
                        break;
                    case 5:
                        System.out.println("\n--- Lista de Funcionários ---");
                        ArrayList<Funcionario> funcionarios = funcionarioManager.listar();
                        if (funcionarios.isEmpty()) System.out.println("Nenhum funcionário cadastrado.");
                        else funcionarios.forEach(Funcionario::mostrar);
                        break;
                    case 6:
                        System.out.println("\n--- Consultando Funcionário ---");
                        System.out.print("Digite o CPF do Funcionário: ");
                        String cpfFunc = scannerPrincipal.nextLine();
                        Funcionario f = funcionarioManager.consultar(cpfFunc);
                        if(f != null) f.mostrar();
                        else System.out.println("Funcionário não encontrado.");
                        break;
                    case 7:
                        System.out.println("\n--- Inserindo Novo Quarto ---");
                        quartoManager.inserir(scannerPrincipal);
                        break;
                    case 8:
                        System.out.println("\n--- Lista de Quartos ---");
                        ArrayList<Quarto> quartos = quartoManager.listar();
                        if (quartos.isEmpty()) System.out.println("Nenhum quarto cadastrado.");
                        else quartos.forEach(Quarto::mostrar);
                        break;
                    case 9:
                        System.out.println("\n--- Consultando Quarto ---");
                        System.out.print("Digite o ID do Quarto: ");
                        int idQuarto = scannerPrincipal.nextInt(); scannerPrincipal.nextLine();
                        Quarto q = quartoManager.consultar(idQuarto);
                        if(q != null) q.mostrar();
                        else System.out.println("Quarto não encontrado.");
                        break;
                    case 10:
                        System.out.println("\n--- Inserindo Novo Produto ---");
                        produtoManager.inserir(scannerPrincipal);
                        break;
                    case 11:
                        System.out.println("\n--- Lista de Produtos ---");
                        ArrayList<Produto> produtos = produtoManager.listar();
                        if (produtos.isEmpty()) System.out.println("Nenhum produto cadastrado.");
                        else produtos.forEach(Produto::mostrar);
                        break;
                    case 12:
                        System.out.println("\n--- Consultando Produto ---");
                        System.out.print("Digite o ID do Produto: ");
                        int idProd = scannerPrincipal.nextInt(); scannerPrincipal.nextLine();
                        Produto p = produtoManager.consultar(idProd);
                        if(p != null) p.mostrar();
                        else System.out.println("Produto não encontrado.");
                        break;
                    case 13:
                        System.out.println("\n--- Inserindo Nova Reserva ---");
                        reservaManager.inserir(scannerPrincipal);
                        break;
                    case 14:
                        System.out.println("\n--- Lista de Reservas ---");
                        ArrayList<Reserva> reservas = reservaManager.listar();
                        if (reservas.isEmpty()) System.out.println("Nenhuma reserva cadastrada.");
                        else reservas.forEach(Reserva::mostrar);
                        break;
                    case 15:
                        System.out.println("\n--- Consultando Reserva ---");
                        System.out.print("Digite o ID da Reserva: ");
                        int idRes = scannerPrincipal.nextInt(); scannerPrincipal.nextLine();
                        Reserva r = reservaManager.consultar(idRes);
                        if(r != null) r.mostrar();
                        else System.out.println("Reserva não encontrada.");
                        break;
                    case 16:
                        System.out.println("\n--- Inserindo Novo Consumo ---");
                        consumoManager.inserir(scannerPrincipal);
                        break;
                    case 17:
                        System.out.println("\n--- Lista de Consumos ---");
                        ArrayList<Consumo> consumos = consumoManager.listar();
                        if (consumos.isEmpty()) System.out.println("Nenhum consumo registrado.");
                        else consumos.forEach(Consumo::mostrar);
                        break;
                    case 18:
                        System.out.println("\n--- Consultando Consumo ---");
                        System.out.print("Digite o ID do Consumo: ");
                        int idCons = scannerPrincipal.nextInt(); scannerPrincipal.nextLine();
                        Consumo c = consumoManager.consultar(idCons);
                        if(c != null) c.mostrar();
                        else System.out.println("Consumo não encontrado.");
                        break;
                    case 0:
                        System.out.println("Saindo do sistema... Obrigado!");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Por favor, digite um número.");
                scannerPrincipal.nextLine();
                opcao = -1;
            }
        } while (opcao != 0);

        scannerPrincipal.close();
    }

    private static void exibirMenuAjustado() {
        System.out.println("\n---------- MENU PRINCIPAL - HOTEL ----------");
        System.out.println("--- HÓSPEDES ---");
        System.out.println(" 1. Inserir Hóspede");
        System.out.println(" 2. Listar Hóspedes");
        System.out.println(" 3. Consultar Hóspede");
        System.out.println("--- FUNCIONÁRIOS ---");
        System.out.println(" 4. Inserir Funcionário");
        System.out.println(" 5. Listar Funcionários");
        System.out.println(" 6. Consultar Funcionário");
        System.out.println("--- QUARTOS ---");
        System.out.println(" 7. Inserir Quarto");
        System.out.println(" 8. Listar Quartos");
        System.out.println(" 9. Consultar Quarto");
        System.out.println("--- PRODUTOS ---");
        System.out.println("10. Inserir Produto");
        System.out.println("11. Listar Produtos");
        System.out.println("12. Consultar Produto");
        System.out.println("-- Obs: Recomendado está cadastrado para fazer o acesso as seguintes Abas (Reserva,Consumos) --");
        System.out.println("--- RESERVAS ---");
        System.out.println("13. Inserir Reserva");
        System.out.println("14. Listar Reservas");
        System.out.println("15. Consultar Reserva");
        System.out.println("--- CONSUMOS ---");
        System.out.println("16. Inserir Consumo");
        System.out.println("17. Listar Consumos");
        System.out.println("18. Consultar Consumo");
        System.out.println("----------------------------------------------------");
        System.out.println(" 0. Sair");
        System.out.print("Escolha uma opção: ");
    }
}