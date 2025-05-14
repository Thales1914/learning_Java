public class App {
    public static void main(String[] args) throws Exception {
        Genero g1 = new Genero(1, "Ação",true);
        Genero g2 = new Genero(2,"suspense",true);

        Filme f1 = new Filme(1,"Matrix", 1999 ,g1);
        Filme f2 = new Filme(2,"Advogado do Diabo", 1997 ,g2);

        Ator a1 = new Ator(1,"Keanu Reeves",60);

        Elenco E1 = new Elenco(f1, a1, true);
        Elenco E2 = new Elenco(f2, a1, false);

        System.out.println("Filme 1: ");
        E1.exibirInformacoes();


        System.out.println("Filme 2: ");
        E2.exibirInformacoes();
    }
}
