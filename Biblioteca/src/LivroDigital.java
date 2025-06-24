public class LivroDigital extends Livro implements Emprestavel {
    private String formato;
    private boolean emprestado;

    public LivroDigital(String titulo, String autor, String formato) {
        super(titulo, autor);
        this.formato = formato;
        this.emprestado = false;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

        public boolean isEmprestado() {
        return emprestado;
    }

    @Override
    public String resumo() {
        return super.resumo() + " [Formato: " + formato + "]" +
                (emprestado ? " (EMPRESTADO)" : " (DISPONÍVEL)");
    }


    @Override
    public void emprestar() {
        if (!emprestado) {
            emprestado = true;
            System.out.println(titulo + " foi emprestado com sucesso!");
        } else {
            System.out.println(titulo + " já está emprestado!");
        }
    }

    @Override
    public void devolver() {
        if (emprestado) {
            emprestado = false;
            System.out.println(titulo + " foi devolvido com sucesso!");
        } else {
            System.out.println(titulo + " não estava emprestado!");
        }
    }
}