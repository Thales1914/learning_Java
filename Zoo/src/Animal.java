public class Animal implements Som {
    public String nome;
    public int idade;

    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome + " | Idade: " + idade);
    }

    public void emitirSom() {
    }
}