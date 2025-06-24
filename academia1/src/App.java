public class App {
    public static void main(String[] args) throws Exception {
    Academia academia = new Academia("Academia Greenlife");
    
    Cliente cliente1 = new Cliente("João", 1, 942472378, 1990);
    Cliente cliente2 = new Cliente("Maria", 2, 942342424, 1995);

    academia.cadastrarCliente(cliente1);
    academia.cadastrarCliente(cliente2);

    System.out.println("Número de clientes: " + academia.getNumeroClientes());
    academia.listarClientes();

    academia.removerClientePorId(1);
    System.out.println("Número de clientes após remoção: " + academia.getNumeroClientes());

    academia.listarClientes();

    System.out.println("Clientes com ano de nascimento após 2000:");
    for (Cliente cliente : academia.getClientesPos2000()) {
        System.out.println(cliente);
    }

   }
}
