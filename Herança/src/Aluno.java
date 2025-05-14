public class Aluno extends Pessoa {
    private int matricula;

    public void estudar(){
        System.out.println("Estudando");
    }

    public void mostrarAluno(){
        super.mostrar();
        System.out.println(matricula);
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Aluno(String nome, String cpf, int matricula){
        super(cpf, nome);
        this.matricula = matricula;
    }
}