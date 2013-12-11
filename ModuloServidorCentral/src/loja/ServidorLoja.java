package loja;

import interfaces.IServidorConsultaIndices;
import interfaces.IServidorLoja;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import basicas.Cartao;
import dados.BancoDadosServidorCentral;

public class ServidorLoja extends UnicastRemoteObject implements IServidorLoja{
	protected ServidorLoja() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Autor: Flaviano Dias
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean realizarCompra(String numero, String senha, String descricao, float valor, String moeda){
		BancoDadosServidorCentral varBDServerCentral = BancoDadosServidorCentral.obterInstancia();
		Cartao varcartao = null;
		IServidorConsultaIndices servidorConsultaIndice = null;
		
		try {
			varcartao = varBDServerCentral.getCartao(numero);
		} catch (Exception e) {
			//N‹o foi encontrado cart‹o no banco			
			return false;
		} 
		
		if (!senha.equals(varcartao.getSenha())) {
			//Senha Inv‡lida
			return false;
		}
		if (moeda.equals("dolar")){
			String objname =  "rmi://localhost/servidor_indices";
			try {
				servidorConsultaIndice = (IServidorConsultaIndices) Naming.lookup(objname);
			} catch (Exception e1) {
				// N‹o foi possivel buscar o objeto
				return false;
			} 
			try {
				valor = servidorConsultaIndice.converterDolarReal(valor);
			} catch (RemoteException e) {
				// N‹o consegue converter o valor
				return false;
			}
		} 
		else if (moeda.equals("euro")) {
			String objname =  "rmi://localhost/servidor_indices";
			try {
				servidorConsultaIndice = (IServidorConsultaIndices) Naming.lookup(objname);
			} catch (Exception e) {
				// N‹o consegue achar o objeto
				return false;
			} 
			try {
				valor = servidorConsultaIndice.converterEuroReal(valor);
			} catch (RemoteException e) {
				// N‹o consegue converter o valor
				return false;
			}
		}
		varcartao.inserirCompra(descricao, valor);
		try {
			varBDServerCentral.atualizarCartao(varcartao);
		} catch (Exception e) {
			//N‹o foi possivel atualizar o cart‹o
			return false;
		} 
		
		return true;

	}

}
