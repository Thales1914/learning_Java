public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
         Cachorro cachorro = new Cachorro("Joe", 5, true);
        Passaro passaro = new Passaro("Pardal", 2, true);

        cachorro.corre = false;
        passaro.voa = false;

        cachorro.setCorre(true);
        passaro.setVoa(true);

        System.out.println("Rex corre? " + cachorro.corre);
        System.out.println("Pardal voa? " + passaro.voa);

        Animal[] animais = {cachorro, passaro};
        int somaIdades = 0;

        for (Animal animal : animais) {
            animal.emitirSom();
            somaIdades += animal.getIdade();
        }

        System.out.println("Média de idades: " + ((double)somaIdades/animais.length));
    }
}
