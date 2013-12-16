package cliente;

import java.rmi.RemoteException;

import basicas.Cliente;
import dados.BancoDadosServidorCentral;
import interfaces.IServidorCliente;

public class ServidorCliente implements IServidorCliente{

	@Override
	public String consultarExtrato(String numeroDoCartao, String senhaCartao)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean efetuarPagamento(String numeroDoCartao)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logar(String CPF, String senha) throws RemoteException {
		BancoDadosServidorCentral bncD = BancoDadosServidorCentral.obterInstancia(); 
		Cliente cltns = bncD.getCliente(CPF);
		if (cltns.getSenha() == senha) {
			return true;
		}
		return false;
	}

}
