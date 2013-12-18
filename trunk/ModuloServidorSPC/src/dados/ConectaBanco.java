package dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Formatter;


public class ConectaBanco {
	
	private String rank;
	
	/**
	 * Nome do arquivo.
	 */
	private String nomeArq = "banco.txt"; 
	
	/**
	 * Construtor, inicialmente sem função. 
	 */
	public ConectaBanco(){
		// #code
	}
	
	/**
	 * Verificar se a pontuação foi suficiente para entrar no top cinco de recordes, 
	 * se for será adicionada ao arquivo de recorde.
	 * @param nome
	 * @param pontos
	 * @throws IOException 
	 */
	public void insert (String cpf, String valor) {
		
		String[] new_point = {cpf, valor}; //Novos pontos adiconados ao sistema
		String novos_devedores = null;
		
		File arq =  new File(this.nomeArq);		
 
		if(arq.exists()){
			
			ArrayList<String[]> devedores = this.openArq(); //Devedores atual em ArrayList
		
			//Adicionar 
			devedores.add( new_point );
			
			//Criar a lista dos cinco melhores para ser gravado no arquivo
			for (int i = 0; i < devedores.size(); i = i+1){
				if(i == 0){
					novos_devedores = devedores.get(i)[0] + "-" + devedores.get(i)[1];
				}
				else{
					novos_devedores = novos_devedores +"%n"+ devedores.get(i)[0] + "-" +devedores.get(i)[1];
				}
			}			
			this.createArq(novos_devedores);
			
		}else{
			this.createArq(cpf +"-"+ valor);
		}
	}
	
	public boolean remove(String cpf) {

		String novos_devedores = null;
		
		File arq =  new File(this.nomeArq);		
 
		if(arq.exists()){
			
			ArrayList<String[]> devedores = this.openArq(); //Devedores atual em ArrayList
		
			//Adicionar 
			//devedores.add( new_point );
			boolean flag = false;
			//Criar lista de devedores
			for (int i = 0; i < devedores.size(); i = i+1){
				if(devedores.get(i)[0].equals(cpf)){
					//Faz nada :)
				}
				else{
					if(flag == false){
						novos_devedores = devedores.get(i)[0] + "-" + devedores.get(i)[1];
						flag = true;
					}
					else{
						novos_devedores = novos_devedores +"%n"+ devedores.get(i)[0] + "-" +devedores.get(i)[1];
					}
				}
			}			
			this.createArq(novos_devedores);
			
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * Responsavel por obter o rank atual do jogo, retornando em formato de string a classificação
	 * dos cinco primeiros colocados
	 * @return Rank dos cinco primeiros em string
	 * @throws IOException 
	 */
	public  ArrayList<String[]> select() {
		File arq =  new File(this.nomeArq);		
		 
		if(arq.exists()){
			
			ArrayList<String[]> result = this.openArq(); //Rank Atual
			
			return result;
		}
		return null;
	}
	
	/**
	 * Responsavel por abrir o arquivo e ler as linhas com a string 'cpf - valor',
	 * depois quebra a string em uma 'String Array' tendo como 'StringArray[0] = cpf' 
	 * e 'StringArray[1] = valor'. 
	 * Por fim, adiciona as 'Strings Array' em uma ArrayList.
	 * 
	 * @return Retorna uma ArrayList de Strings array.
	 * @throws IOException 
	 */
	private ArrayList<String[]> openArq() {
		
		String linha;
		ArrayList<String[]> array_rank = new ArrayList<String[]>();
		
		try {
			FileReader ler = new FileReader(this.nomeArq);
			BufferedReader leitor = new BufferedReader(ler);
			
			//Ficar em loop até acabar as linhas escritas do arquivo
			while(true){
				linha = leitor.readLine();
				if(linha == null)
					break;
				
				//Adicionar na ArrayList a String Array
				array_rank.add(linha.split("-"));
			}
		}catch(Exception erro){
			String e[] = {"0","Erro"};
			array_rank.add(e);
		}
		return array_rank;
	}
	
	
	/**
	 * Caso o arquivo não exista, criar o banco.
	 * @param conteudo
	 */
	private  void createArq(String conteudo){
		try {
			 Formatter saida = new Formatter(this.nomeArq);
		     saida.format(conteudo);
		     saida.close();
		}catch(Exception erro){
			System.out.println("Error ao criar Arquivo");
		}
	}

}
