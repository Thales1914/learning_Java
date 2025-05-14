public class Cliente {
    public String nome;
    public int id;
    public int telefone;
    public int anoNascimento;

    public Cliente (String nome, int id, int telefone, int anoNascimento){
        this.nome = nome;
        this.id = id;
        this.telefone = telefone;
        this.anoNascimento = anoNascimento;
    }

    public String toString(){
        return "Nome: " + nome + "\n" +
                "Id: " + id + "\n" +
                "Telefone: " + telefone + "\n" +
                "Ano de Nascimento: " + anoNascimento;
    }
}