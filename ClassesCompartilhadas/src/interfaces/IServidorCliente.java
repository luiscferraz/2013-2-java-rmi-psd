/*
 * Teste
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorCliente extends Remote {
	public String consultarExtrato(String numeroDoCartao, String senhaCartao) throws RemoteException;
	public boolean efetuarPagamento(String numeroDoCartao) throws RemoteException;
	public boolean logar(String CPF, String senha) throws RemoteException;
}
