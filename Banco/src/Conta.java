public class Conta {
    private String cpf;
    private int numeroBanco;
    private double saldo;
    private boolean clienteEspecial;

    public Conta(String cpf, int numeroBanco, double saldo, boolean clienteEspecial) {
        this.cpf = cpf;
        this.numeroBanco = numeroBanco;
        this.saldo = saldo;
        this.clienteEspecial = clienteEspecial;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean getClienteEspecial() {
        return clienteEspecial;
    }

    public void setClienteEspecial(boolean clienteEspecial) {
        this.clienteEspecial = clienteEspecial;
    }

    public double calcularBonificacao() {
        if (clienteEspecial) {
            return saldo * 0.10;
        } else {
            return saldo * 0.05;
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        }
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public void transferir(Conta destino, double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.sacar(valor);
            destino.depositar(valor);
        }
    }

    public void mostrarAtributos() {
        System.out.println("CPF do titular: " + cpf);
        System.out.println("Número do banco: " + numeroBanco);
        System.out.println("Saldo: R$" + saldo);
        System.out.println("Cliente especial: " + (clienteEspecial ? "Sim" : "Não"));
        System.out.println("Bonificação: R$" + calcularBonificacao());
    }
}