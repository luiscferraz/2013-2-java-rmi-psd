package cliente;

import java.rmi.RemoteException;

import basicas.Cartao;
import basicas.Cliente;
import dados.BancoDadosServidorCentral;
import interfaces.IServidorCliente;

public class ServidorCliente implements IServidorCliente{

	@Override
	public String consultarExtrato(String numeroDoCartao, String senhaCartao)
			throws RemoteException {
		BancoDadosServidorCentral bncD = BancoDadosServidorCentral.obterInstancia();
		Cartao ctr = bncD.getCartao(numeroDoCartao);
		return ctr.getExtratoCompras();
		
	}

	@Override
	public boolean efetuarPagamento(String numeroDoCartao)
			throws RemoteException {
		BancoDadosServidorCentral bncD = BancoDadosServidorCentral.obterInstancia();
		Cartao ctr = bncD.getCartao(numeroDoCartao);
		ctr.efetuarPagamento();
		return ctr.isPagamentoEfetuado();
		
		
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
