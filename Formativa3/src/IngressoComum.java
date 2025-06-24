public class IngressoComum extends Ingresso{
    public IngressoComum(String nome, boolean meiaEntrada, double valor, int lot) {
        super(nome, meiaEntrada, valor, lot);
    }
    public double calcularReembolso() {
        if(meiaEntrada && lot == 1){
            return valor * 0.5;
        }else {
            return valor * 0.3;
        }
    }
  }
