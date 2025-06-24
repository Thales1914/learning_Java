import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite sua nota da AV1: ");
        double nota1 = sc.nextDouble();
        System.out.print("Digite sua nota da AV2: ");
        double nota2 = sc.nextDouble();
        System.out.print("Digite sua nota da AV3: ");
        double nota3 = sc.nextDouble();
        sc.close();

        Pessoa aluno = new Pessoa(nota1,nota2,nota3);
        System.out.println("A sua média foi: " + aluno.calcularMedia());
    }
}