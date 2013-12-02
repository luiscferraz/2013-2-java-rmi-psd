package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorConsultaIndices extends Remote {
	public float converterDolarReal(float valor) throws RemoteException;
	public float converterEuroReal(float valor) throws RemoteException;
	public float consultarIOF() throws RemoteException;
}
