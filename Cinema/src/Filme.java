public class Filme {
    private int id;
    private String nome;
    private int ano;
    private Genero genero;

    public Filme() {
        this.id = 0;
        this.nome = "";
        this.ano = 0;
        this.genero = null;
    }

    public Filme(int id, String nome, int ano, Genero genero) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public boolean ehFilmeAntigo(int anoLimite) {
        return this.ano < anoLimite;
    }


    public void exibirInformacoes() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Ano: " + ano);
        System.out.println("Gênero: " + genero.descricao);
    }
}