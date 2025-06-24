public class Pessoa {
    public Double nota1;
    public Double nota2;
    public Double nota3;

    public Pessoa(double nota1, double nota2, double nota3){
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public void mostrar(){
        System.out.println("Nota AV1: " + nota1);
        System.out.println("Nota AV2: " + nota2);
        System.out.println("Nota AV3: " + nota3);
    }
    public double calcularMedia(){
        return (nota1 + nota2 + nota3) / 3;
    }
}
