package servidor;

import interfaces.IServidorCadastroNegativo;

import java.rmi.RemoteException;

import dados.BancoDeDadosSPC;

public class ServidorCadastroNegativo implements IServidorCadastroNegativo {
	BancoDeDadosSPC bancoSPC = new BancoDeDadosSPC();
	@Override
	public boolean cadastrarDevedor(String cpf, float valor)
			throws RemoteException {
		if(bancoSPC.inserirDevedor( cpf, valor)){
			return true;
		}else{
			return false;
		}
	}	
	
}
