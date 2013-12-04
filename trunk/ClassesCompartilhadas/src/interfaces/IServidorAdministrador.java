package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorAdministrador extends Remote {
	public String consultarCliente(String cpf) throws RemoteException;;
	public String consultarClientes() throws RemoteException;
	public String consultarCartao(String numeroDoCartao) throws RemoteException;
	public boolean cadastrarCliente(String nome, String sobrenome, String cpf, String senha) throws RemoteException;
	public boolean cadastrarCartao(String cpf, String numero, String validade, String cvv, String senha) throws RemoteException;
	public boolean logar(String cpf, String senha) throws RemoteException;
}
