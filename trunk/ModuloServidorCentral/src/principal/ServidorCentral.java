package principal;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import loja.ServidorLoja;
import administrador.ServidorAdministrador;
import cliente.ServidorCliente;

public class ServidorCentral {

	private ServidorCliente servidorCliente;
	private ServidorAdministrador servidorAdministrador;
	private ServidorLoja servidorLoja;
	// TODO falta instaciar o banco
	// TODO falta instanciar os outros servicos
	
	public ServidorCentral() {
		try {
			this.servidorCliente = new ServidorCliente();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.servidorAdministrador = new ServidorAdministrador();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.servidorLoja = new ServidorLoja();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServidorCentral servidorCentral = new ServidorCentral();
		try {
			Registry r = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			// registro do servidor para os clientes
			String nomeServidorCliente = "servidor_cliente";
			System.out.println("registrando " + nomeServidorCliente + "...");
			r.rebind(nomeServidorCliente, servidorCentral.servidorCliente);
			System.out.println(nomeServidorCliente + " registrado!");
			// registro do servidor para administradores
			String nomeServidorAdministrador = "servidor_administrador";
			System.out.println("registrando " + nomeServidorAdministrador + "...");
			r.rebind(nomeServidorAdministrador, servidorCentral.servidorAdministrador);
			System.out.println(nomeServidorAdministrador + " registrado!");
			// registro do servidor para os logistas
			String nomeServidorLoja = "servidor_loja";
			System.out.println("registrando " + nomeServidorLoja + "...");
			r.rebind(nomeServidorLoja, servidorCentral.servidorLoja);
			System.out.println(nomeServidorLoja + " registrado!");
			
		}
		catch (Exception e) {
			System.err.println("erro na main()! " + e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("esperando requisicao...");
	} 

}
