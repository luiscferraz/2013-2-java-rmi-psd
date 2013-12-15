package administrador;

import interfaces.IServidorAdministrador;
import interfaces.IServidorConsultaIndices;
import basicas.Administrador;
import basicas.Cartao;
import basicas.Cliente;
import dados.BancoDadosServidorCentral;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class ServidorAdministrador extends UnicastRemoteObject implements IServidorAdministrador {
	
	private static final long serialVersionUID = 1L;
	
	protected ServidorAdministrador() throws RemoteException {
		super();
	}
	
	@Override
	public String consultarCliente(String cpf){
		
		BancoDadosServidorCentral varBDServerCentral = BancoDadosServidorCentral.obterInstancia();
		
		try {
			Cliente cliente = varBDServerCentral.getCliente(cpf);
		} catch (Exception e) {
			System.out.println("O cliente de cpf " + cpf + "não foi encontrado.");		
			return null;
		} 
		return null;
	}

	@Override
	public String consultarClientes() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String consultarCartao(String numeroDoCartao) throws RemoteException {
		
		BancoDadosServidorCentral varBDServerCentral = BancoDadosServidorCentral.obterInstancia();
		
		try {
			Cartao cartao = varBDServerCentral.getCartao(numeroDoCartao);
		} catch (Exception e) {
			System.out.println("O cartão não foi encontrado.");		
			return null;
		} 
		return null;
	}

	@Override
	public boolean cadastrarCliente(String nome, String sobrenome, String cpf,
			String senha) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cadastrarCartao(String cpf, String numero, String validade,
			String cvv, String senha) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logar(String cpf, String senha) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
