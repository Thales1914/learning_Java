public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Conta conta1 = new Conta("123.456.789-00", 001, 1000.0, true);
        Conta conta2 = new Conta("987.654.321-00", 002, 500.0, false);

        System.out.println("Informações da conta 1:");
        conta1.mostrarAtributos();

        System.out.println("Informações da conta 2:");
        conta2.mostrarAtributos();

        System.out.println("Realizando transferência de R$300 da conta 1 para a conta 2:");
        conta1.transferir(conta2, 300.0);

        System.out.println("Informações da conta 1 após transferência:");
        conta1.mostrarAtributos();

        System.out.println("Informações da conta 2 após transferência:");
        conta2.mostrarAtributos();
    }
}
