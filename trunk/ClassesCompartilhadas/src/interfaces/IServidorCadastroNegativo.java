package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorCadastroNegativo extends Remote {
	public boolean cadastrarDevedor(String cpf, float valor) throws RemoteException;
}
