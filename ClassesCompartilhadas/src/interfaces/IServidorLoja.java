package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorLoja extends Remote {
	public boolean realizarCompra(String numero, String senha, String descricao, float valor, String moeda) throws RemoteException, ClassNotFoundException;
}