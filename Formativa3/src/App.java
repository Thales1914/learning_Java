public class App {
    public static void main(String[] args) throws Exception {
        Ingresso[] ingressos = new Ingresso[4];
        ingressos[0] = new IngressoComum("Ingresso Comum", true, 100.0, 1);
        ingressos[1] = new IngressoVip("Ingresso VIP", false, 200.0, 2);
        ingressos[2] = new IngressoVip("Ingresso Vip", true, 200.0, 1);
        ingressos[3] = new IngressoComum("Ingresso Comum", false, 100.0, 1);

        for (Ingresso ingresso : ingressos) {
            System.out.println("Nome: " + ingresso.nome);
            System.out.println("Meia Entrada: " + ingresso.meiaEntrada);
            System.out.println("Valor: " + ingresso.valor);
            System.out.println("Lote: " + ingresso.lot);
            System.out.println("Reembolso: " + ingresso.calcularReembolso());
            System.out.println();
        }
    }
}
