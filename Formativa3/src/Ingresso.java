public abstract class Ingresso {
    public String nome;
    public boolean meiaEntrada;
    public double valor;
    public int lot;

    public Ingresso(String nome, boolean meiaEntrada, double valor, int lot) {
        this.nome = nome;
        this.meiaEntrada = meiaEntrada;
        this.valor = valor;
        this.lot = lot;
    }
    public abstract double calcularReembolso();
}