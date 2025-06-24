import java.util.ArrayList;

public class Bibliotca {
    private ArrayList<Livro> acervo;

    public Bibliotca() {
        acervo = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        acervo.add(livro);
        System.out.println("Livro adicionado: " + livro.resumo());
    }

    public void listarLivros() {
        System.out.println("\n=== ACERVO ===");
        for (Livro livro : acervo) {
            System.out.println("- " + livro.resumo());
        }
    }
}