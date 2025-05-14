public class Elenco {
    Filme filme;
    Ator ator;
    boolean personagemPrincipal;

    public Elenco(Filme filme, Ator ator, boolean personagemPrincipal) {
        this.filme = filme;
        this.ator = ator;
        this.personagemPrincipal = personagemPrincipal;
    }

    public void exibirInformacoes() {
        System.out.println("Filme: " + filme.getNome());
        System.out.println("Ator: " + ator.nome);
        System.out.println("Personagem Principal: " + (personagemPrincipal ? "Sim" : "Não"));
        System.out.println("Gênero do filme: " + filme.getGenero());
    }
}