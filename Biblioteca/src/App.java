public class App {
    public static void main(String[] args) throws Exception {
          Bibliotca biblioteca = new Bibliotca();

        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis");
        LivroDigital livro2 = new LivroDigital("Clean Code", "Robert Martin", "PDF");

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        biblioteca.listarLivros();

        // Usando a interface Emprestavel
        ((Emprestavel)livro2).emprestar();
        ((Emprestavel)livro2).devolver();
    }
}
