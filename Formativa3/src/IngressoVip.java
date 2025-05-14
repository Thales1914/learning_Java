public class IngressoVip extends Ingresso {
    public IngressoVip(String nome, boolean meiaEntrada, double valor, int lot) {
        super(nome, meiaEntrada, valor, lot);
    }

    public double calcularReembolso() {
        if(meiaEntrada && lot == 1 ){
            return valor * 0.10;
        }else{
            return valor * 0.6;
        }
    }  
}

