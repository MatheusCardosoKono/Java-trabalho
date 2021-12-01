import java.io.Serializable;

public abstract class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    private   String marca;
    private   String modelo;
    private   int ano;
    protected String tipo;

	public Veiculo(String marca, int ano, String modelo) {
        this.marca = marca;
        this.ano = ano;
        this.modelo = modelo;
    }
    
    public String toString() {
        String retorno = "";
        retorno += "Marca: "     + this.marca     + "\n";
        retorno += "Ano: "    + this.ano    + "\n";
        retorno += "Modelo: "     + this.modelo     + "\n";
        retorno += "Tipo: "  + this.tipo  + "\n";
        retorno += "Atividade: "  + atividade()+ "\n";
        return retorno;
    }
    
    public abstract String atividade();
    
    public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}