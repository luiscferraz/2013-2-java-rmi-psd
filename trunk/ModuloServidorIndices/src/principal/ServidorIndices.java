package principal;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import servidor.ServidorConsultaIndices;

public class ServidorIndices {

	private ServidorConsultaIndices servidorConsultaIndices;
	
	public ServidorIndices() {
		
			this.servidorConsultaIndices = new ServidorConsultaIndices();
		
	}
	
	public static void main(String[] args) {
		ServidorIndices servidorIndices = new ServidorIndices();
		try {
			Registry r = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			// registro do servidor de indices
			String nomeServidorIndices = "servidor_indices";
			System.out.println("registrando " + nomeServidorIndices + "...");
			r.rebind(nomeServidorIndices, servidorIndices.servidorConsultaIndices);
			System.out.println(nomeServidorIndices + " registrado!");
		}
		catch (Exception e) {
			System.err.println("erro na main()! " + e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("esperando requisicao...");
	}
}
