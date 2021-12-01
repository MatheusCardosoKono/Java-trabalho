import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class LocacaoVeiculos {

	private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
	
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Automovel leAutomovel (){

		String [] valores = new String [4];
		String [] marcaVal = {"Marca", "Ano", "Modelo", "Motoriza��o", "Autonomia"};
		valores = leValores (marcaVal);

		Automovel automovel = new Automovel (valores[0],retornaInteiro(valores[1]),valores[2],retornaFloat(valores[3]),retornaInteiro(valores[4]));
		return automovel;
	}



	public Onibus leOnibus (){

		String [] valores = new String [4];
		String [] marcaVal = {"Marca", "Ano", "Modelo", "Assentos", "Categoria"};
		valores = leValores (marcaVal);
		
		return new Onibus (valores[0],retornaInteiro(valores[1]),valores[2],retornaInteiro(valores[3]),valores[4]);
	}
	
	public Caminhao leCaminhao (){

		String [] valores = new String [4];
		String [] marcaVal = {"Marca", "Ano", "Modelo", "Carga", "Eixos"};
		valores = leValores (marcaVal);

		Caminhao caminhao = new Caminhao (valores[0],retornaInteiro(valores[1]),valores[2],retornaInteiro(valores[3]),retornaInteiro(valores[4]));
		return caminhao;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // M�todo est�tico, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // N�o conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		if (entrada == null)
			return -1;
		//Enquanto n�o for poss�vel converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um n�mero inteiro.");
		}
		return Integer.parseInt(entrada);
	}
	

	private boolean floatValido(String s) {
		try {
			Float.parseFloat(s); // M�todo est�tico, que tenta tranformar uma string em float
			return true;
		} catch (NumberFormatException e) { // N�o conseguiu tranformar em float e gera erro
			return false;
		}
	}
	public float retornaFloat(String entrada) { // retorna um valor inteiro

		//Enquanto n�o for poss�vel converter o valor de entrada para FLOAT, permanece no loop
		while (!this.floatValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um n�mero decimal.");
		}
		return Float.parseFloat(entrada);
	}

	public void salvaVeiculos (ArrayList<Veiculo> veiculos){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("veiculos.db"));
			for (int i=0; i < veiculos.size(); i++)
				outputStream.writeObject(veiculos.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Imposs�vel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Veiculo> recuperaVeiculos (){
		ArrayList<Veiculo> veiculosTemp = new ArrayList<Veiculo>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("veiculos.db"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Veiculo) {
					veiculosTemp.add((Veiculo) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com ve�culos n�o existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return veiculosTemp;
		}
	}

	public void menuLocadora (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Locadora de Ve�culos\n" +
					"Op��es:\n" + 
					"1. Entrar Ve�culo\n" +
					"2. Exibir Ve�culo\n" +
					"3. Excluir Ve�culo\n" +
					"4. Gravar Ve�culo\n" +
					"5. Recuperar Ve�culo\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Ve�culos\n" +
						"Op��es:\n" + 
						"1. Autom�vel\n" +
						"2. �nibus\n" +
						"3. Caminh�o";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: veiculos.add((Veiculo)leAutomovel());
				break;
				case 2: veiculos.add((Veiculo)leOnibus());
				break;
				case 3: veiculos.add((Veiculo)leCaminhao());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Ve�culo para entrada n�o escolhido!");
				}

				break;
				
			case 2: // Exibir dados
				if (veiculos.size() == 0) {
					JOptionPane.showMessageDialog(null,"N�o h� ve�culos em mem�ria. Entre com ve�culos primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < veiculos.size(); i++)	{
					dados += veiculos.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
				
			case 3: // Excluir Dados
				if (veiculos.size() == 0) {
					JOptionPane.showMessageDialog(null,"N�o h� ve�culos em mem�ria. Entre com ve�culos primeiramente");
					break;
				}
				veiculos.clear();
				JOptionPane.showMessageDialog(null,"Dados Limpos com sucesso!");
				break;
				
			case 4: // Grava Dados
				if (veiculos.size() == 0) {
					JOptionPane.showMessageDialog(null,"N�o h� ve�culos em mem�ria. Entre com ve�culos primeiramente");
					break;
				}
				salvaVeiculos(veiculos);
				JOptionPane.showMessageDialog(null,"Dados Salvos com sucesso!");
				break;
				
			case 5: // Recupera Dados
				veiculos = recuperaVeiculos();
				if (veiculos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados Recuperados com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo.");
				break;
			case -1: default:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo.");
				break;
			}
				
		} while (opc1 != 9 && opc1 != -1);
	}
	
	public static void main(String[] args) {

		LocacaoVeiculos ce = new LocacaoVeiculos();
		
		ce.menuLocadora();
		

	}

}