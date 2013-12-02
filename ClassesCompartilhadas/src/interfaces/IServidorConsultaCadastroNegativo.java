package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorConsultaCadastroNegativo extends Remote {
	public String consultarDevedores() throws RemoteException;
	public boolean eDevedor(String CPF) throws RemoteException;
}
