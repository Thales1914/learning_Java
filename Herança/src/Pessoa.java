import java.sql.Struct;

public class Pessoa {
    private String nome;
    private String cpf;

    public Pessoa(String nome, String cpf){
        this.cpf = cpf;
        this.nome = nome;

    }

    public void andar(){
        System.out.println("Andando");
    }

    public void mostrar(){
        System.out.println(this.cpf + " - " + this.nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}