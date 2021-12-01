
public class Onibus extends Veiculo {

    private static final long serialVersionUID = 1L;
    private int assentos;
    private String categoria;

    public String atividade() {
        return "Transporte público";
    }
    public Onibus(String marca, int ano, String modelo, int assentos, String categoria) {
        super(marca, ano, modelo);
        this.tipo = "Onibus";
        this.assentos = assentos;
        this.categoria = categoria;
    }
    
	public int getAssentos() {
		return assentos;
	}

	public void setAssentos(int assentos) {
		this.assentos = assentos;
	}
	
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String toString() {
		String retorno = super.toString();
		retorno += "Assentos: "     + this.assentos     + "\n";
		retorno += "Categoria: "     + this.categoria     + "\n";
		return retorno;
	}
}