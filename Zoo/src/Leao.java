public class Leao extends Animal implements Som{
    public Leao(String nome, int idade) {
        super(nome, idade);
    }

    public void emitirSom(){
        System.out.println("O Leão Rugiu");
    }
}
