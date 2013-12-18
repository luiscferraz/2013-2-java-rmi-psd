package principal;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import servidor.ServidorCadastroNegativo;
import servidor.ServidorConsultaCadastroNegativo;

public class ServidorSPC {
	
	private ServidorCadastroNegativo servidorCadastroNegativo;
	private ServidorConsultaCadastroNegativo servidorConsultaCadastroNegativo;
	
	ServidorSPC() {
		//try {
			this.servidorCadastroNegativo = new ServidorCadastroNegativo();
		//} catch (RemoteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		//try {
			this.servidorConsultaCadastroNegativo = new ServidorConsultaCadastroNegativo();
		//} catch (RemoteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
	}

	public static void main(String[] args) {
		ServidorSPC servidorSPC = new ServidorSPC();
		try {
			Registry r = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			// registro do servidor SPC de cadastro negativo
			String nomeServidorCadastroNegativo = "servidor_cadastro_negativo";
			System.out.println("registrando " + nomeServidorCadastroNegativo + "...");
			r.rebind(nomeServidorCadastroNegativo, servidorSPC.servidorCadastroNegativo);
			System.out.println(nomeServidorCadastroNegativo + " registrado!");
			// registro do servidor de consulta de cadastro negativo
			String nomeServidorConsultaCadastroNegativo = "servidor_consulta_cadastro_negativo";
			System.out.println("registrando " + nomeServidorConsultaCadastroNegativo + "...");
			r.rebind(nomeServidorConsultaCadastroNegativo, servidorSPC.servidorConsultaCadastroNegativo);
			System.out.println(nomeServidorConsultaCadastroNegativo + " registrado!");
		}
		catch (Exception e) {
			System.err.println("erro na main()! " + e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("esperando requisicao...");
	}

}
