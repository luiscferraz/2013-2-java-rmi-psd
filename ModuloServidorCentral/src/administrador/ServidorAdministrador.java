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
import java.util.ArrayList;


public class ServidorAdministrador extends UnicastRemoteObject implements IServidorAdministrador {
	
	private static final long serialVersionUID = 1L;
	
	public ServidorAdministrador() throws RemoteException {
		super();
	}
	
	public String consultarCliente(String cpf) throws RemoteException {
		Cliente cliente = new Cliente();
		BancoDadosServidorCentral varBDServerCentral = BancoDadosServidorCentral.obterInstancia();
		
		try {
			cliente = varBDServerCentral.getCliente(cpf);
			return cliente.toString();
		} catch (Exception e) {
			System.out.println("O cliente nao foi encontrado.");		
			return null;
		} 
	}


	public String consultarClientes() throws RemoteException {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		BancoDadosServidorCentral varBDServerCentral = BancoDadosServidorCentral.obterInstancia();
		
		try {
			clientes = varBDServerCentral.getClientes();
			return clientes.toString();
		} catch (Exception e) {
			System.out.println("Nao ha clientes cadastrados.");		
			return null;
		} 
	}

	public String consultarCartao(String numeroDoCartao) throws RemoteException {
		Cartao cartao = new Cartao();
		BancoDadosServidorCentral varBDServerCentral = BancoDadosServidorCentral.obterInstancia();
		
		try {
			cartao = varBDServerCentral.getCartao(numeroDoCartao);
			return cartao.toString();
		} catch (Exception e) {
			System.out.println("O cartao nao foi encontrado.");		
			return null;
		} 
	}

	public boolean cadastrarCliente(String nome, String sobrenome, String cpf,
			String senha) throws RemoteException {
		BancoDadosServidorCentral varBDServerCentral = BancoDadosServidorCentral.obterInstancia();
		
		try {
			Cliente cliente = new Cliente(nome, sobrenome, cpf, senha);
			varBDServerCentral.inserirCliente(cliente);
			System.out.println("Cliente cadastrado com sucesso.");
			return true;
		} catch (Exception e) {
			System.out.println("Nao foi possivel cadastrar o cliente.");		
			return false;
		} 
	}

	
	public boolean cadastrarCartao(String cpf, String numero, String validade,
			String cvv, String senha) throws RemoteException {
		BancoDadosServidorCentral varBDServerCentral = BancoDadosServidorCentral.obterInstancia();
		
		
		
		try {
			Cartao cartao = new Cartao(cpf, numero, validade, cvv, senha);
			varBDServerCentral.inserirCartao(cartao);
			
			Cliente cliente = varBDServerCentral.getCliente(cpf);
			cliente.setNumeroDoCartao(numero);
			
			varBDServerCentral.atualizarCliente(cliente);
			System.out.println("Cartao cadastrado com sucesso.");
			return true;
		} catch (Exception e) {
			System.out.println("Nao foi possivel cadastrar o cartao.");		
			return false;
		} 
	}

	
	public boolean logar(String cpf, String senha) throws RemoteException {
		BancoDadosServidorCentral varBDServerCentral = BancoDadosServidorCentral.obterInstancia();
	
		try {
			Administrador admin = new Administrador();
			admin= varBDServerCentral.getAdministrador(cpf);
			if (admin!=null){
				if(senha.equals(admin.getSenha())){
					System.out.println("Usuario logado com sucesso.");		
					return true;
				}else{
					System.out.println("Senha incorreta.");		
					return false;
					
				}
			}else{
				System.out.println("Usuario inexistente.");		
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("Nao foi possivel acesso ao sistema: ");		
			return false;
		} 
			
	}
	
	

}