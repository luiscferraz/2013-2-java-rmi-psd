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
		 if(bancoSPC.eDevedor(CPF)){
             return true;
     }else{
             return false;
     }
}
	
	public boolean removerDevedor(String CPF) throws RemoteException{
		if(bancoSPC.removerDevedor(CPF)){
			return true;
			
		}else{
			return false;
		}
		
	}

}
