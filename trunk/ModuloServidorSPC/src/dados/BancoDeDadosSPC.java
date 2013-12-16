package dados;

import interfaces.IBancoDeDadosSPC;

import java.util.ArrayList;

public class BancoDeDadosSPC implements IBancoDeDadosSPC{
	
	ConectaBanco banco = new ConectaBanco();

	
    /**
     * Inserir um devedor no banco de dados.
     * @param cpf
     * @return boolean;
     */
	public boolean inserirDevedor(String cpf, float valor){
		banco.insert(cpf, String.valueOf(valor));
		return true;
	};
	/**
	 * Remover um devedor do banco de dados via cpf
	 * @param String cpf
	 * @return Boolean
	 */
	public boolean removerDevedor(String cpf){
		return banco.remove(cpf);
	};
	/**
	 * Buscar se cpf é devedor
	 * @param String cpf
	 * @return Boolean
	 */
	public boolean eDevedor(String cpf){
		ArrayList<String[]> result = banco.select();
		for (int i = 0; i < result.size(); i = i+1){
			if(result.get(i)[0].equals(cpf)){
				return true;
			}
		}
		return false;
	};

	/**
	 * Retornar lista de devedores do banco
	 * @return String
	 */
	public String getDevedores(){
		ArrayList<String[]> result = banco.select();
		String result_print = null;
		for (int i = 0; i < result.size(); i = i+1){
			if(i == 0){
				result_print = (i+1)+") "+result.get(i)[0] + " - R$" + result.get(i)[1];
			}
			else{
				result_print = result_print +"\n"+ (i+1)+") "+ result.get(i)[0] + " - R$" + result.get(i)[1];
			}
		}
		return result_print;
	};
}
