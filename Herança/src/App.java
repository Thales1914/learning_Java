public class App {
    public static void main(String[] args) throws Exception {
        Aluno aluno = new Aluno("Thales", "08822192354", 2422709);
        aluno.mostrarAluno();

        Bolsista bolsista = new Bolsista("Maria", "12345678901", 2023001, true);
        bolsista.mostrarBolsista();

        Bolsista bolsista2 = new Bolsista("João", "98765432100", 2023002, false);
        bolsista2.mostrarBolsista();
    }
}
