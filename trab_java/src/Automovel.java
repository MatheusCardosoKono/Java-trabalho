
public class Automovel extends Veiculo {

    private static final long serialVersionUID = 1L;
    private float motorizacao;
    private int autonomia;

    public String atividade() {
        return "Transporte particular";
    }
    public Automovel(String marca, int ano, String modelo, float motorizacao, int autonomia) {
        super(marca, ano, modelo);
        this.tipo = "Automovel";
        this.motorizacao = motorizacao;
        this.autonomia = autonomia;
    }
    
	public float getMotorizacao() {
		return motorizacao;
	}

	public void setMotorizacao(float motorizacao) {
		this.motorizacao = motorizacao;
	}
	
	
	public int getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(int autonomia) {
		this.autonomia = autonomia;
	}
	
	public String toString() {
		String retorno = super.toString();
		retorno += "Motorização: Motor "     + this.motorizacao     + "\n";
		retorno += "Autonomia: "     + this.autonomia     + "km \n";
		return retorno;
	}
}
