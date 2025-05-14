public class Cachorro extends Animal {
    boolean corre;

    public Cachorro(String nome, int idade, boolean corre) {
        super(nome, idade);
        this.corre = corre;
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " diz: Au au!");
    }

    public boolean isCorre() {
        return corre;
    }

    public void setCorre(boolean corre) {
        this.corre = corre;
    }
}