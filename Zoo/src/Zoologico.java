import java.util.ArrayList;

public class Zoologico {
    public ArrayList<Animal> animais;

    public Zoologico(){
        this.animais = new ArrayList<>();
    }

    public void adicionarAnimal(Animal a){
        animais.add(a);
    }

    public void listarAnimais(){
        for (Animal animal : animais) {
            animal.exibirDados();
        }
    }

    public void emitirSom(){
        for (Animal animal : animais){
            animal.emitirSom();
        }
    }
}
