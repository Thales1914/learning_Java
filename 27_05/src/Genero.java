import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Genero {
    public int idGenero;
    public String descGenero;

    public Genero(int idGenero, String descGenero){
        this.idGenero = idGenero;
        this.descGenero = descGenero;
    }

    public boolean isnerir(){
        try{
            FileWriter fw =  new FileWriter("Genero.txt" , true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(idGenero + ";" + descGenero);
            bw.newLine();
            bw.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    //public ArrayList<Genero> listar(){

    //}

   // public Genero consultar(int )
}
