package servidor;

import interfaces.IServidorConsultaCadastroNegativo;

import java.rmi.RemoteException;

import dados.BancoDeDadosSPC;

public class ServidorConsultaCadastroNegativo implements
		IServidorConsultaCadastroNegativo {
	BancoDeDadosSPC bancoSPC = new BancoDeDadosSPC();
	@Override
	public String consultarDevedores() throws RemoteException {
		return bancoSPC.getDevedores();		
	}

	@Override
	public boolean eDevedor(String CPF) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
