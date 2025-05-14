public class Bolsista extends Aluno{
    public boolean isAtleta;

    public Bolsista(String nome, String cpf, int matricula, boolean isAtleta){
        super(cpf,nome, matricula);
        this.isAtleta = isAtleta;

    }

    public String getAtletaFormatado(){
        return isAtleta ? "Sim" : "Não";
    }

    public void mostrarBolsista(){
        super.mostrar();
        System.out.println("É atleta? " + getAtletaFormatado());
    }
}
