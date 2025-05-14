public class Passaro extends Animal {
    boolean voa;

    public Passaro(String nome, int idade, boolean voa) {
        super(nome, idade);
        this.voa = voa;
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " diz: Piu piu!");
    }

    public boolean isVoa() {
        return voa;
    }

    public void setVoa(boolean voa) {
        this.voa = voa;
    }
}