import java.util.ArrayList;
import java.util.List;

public class Academia {
    public String nome;
    public ArrayList<Cliente> clientes;
    
    public Academia(String nome) {
        this.nome = nome;
        this.clientes = new ArrayList<>();
    }
    
    public void cadastrarCliente(Cliente novoCliente) {
        clientes.add(novoCliente);
    }
    
    public boolean removerClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.id == id) {
                clientes.remove(cliente);
                return true;
            }
        }
        return false;
    }
    
    public int getNumeroClientes() {
        return clientes.size();
    }
    
    public List<Cliente> getClientesPos2000() {
        List<Cliente> jovens = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.anoNascimento > 2000) {
                jovens.add(cliente);
            }
        }
        return jovens;
    }
    
    public void removerClientesAntigos() {
        clientes.removeIf(cliente -> cliente.anoNascimento < 1990);
    }
    
    public void listarClientes() {
        System.out.println("Clientes da academia " + nome + ":");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
    
    public Cliente buscarClientePorNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.nome.equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }
}