
public class Caminhao extends Veiculo {

    private static final long serialVersionUID = 1L;
    private int carga;
    private int eixos;

    public String atividade() {
        return "Transporte de cargas";
    }
    public Caminhao(String marca, int ano, String modelo, int carga, int eixos) {
        super(marca, ano, modelo);
        this.tipo = "Caminhão";
        this.carga = carga;
        this.eixos = eixos;
    }
    
	public int getCarga() {
		return carga;
	}

	public void setCarga(int carga) {
		this.carga = carga;
	}
	
	
	public int getEixos() {
		return eixos;
	}

	public void setEixos(int eixos) {
		this.eixos = eixos;
	}
	
	public String toString() {
		String retorno = super.toString();
		retorno += "Carga: "     + this.carga     + " toneladas \n";
		retorno += "Eixos: "     + this.eixos     + "\n";
		return retorno;
	}
}