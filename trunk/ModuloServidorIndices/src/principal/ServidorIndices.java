package principal;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import servidor.ServidorConsultaIndices;
import servidor.ServidorConsultaIndicesREST;

public class ServidorIndices {

	private ServidorConsultaIndices servidorConsultaIndices;
	// private ServidorConsultaIndicesREST servidorConsultaIndicesREST;
	
	public ServidorIndices() {
		try {
			this.servidorConsultaIndices = new ServidorConsultaIndices();
			// this.servidorConsultaIndicesREST = new ServidorConsultaIndicesREST();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
			// registro do servidor de indices que usa REST
			String nomeServidorIndicesREST = "servidor_indices_rest";
			System.out.println("registrando " + nomeServidorIndicesREST + "...");
			r.rebind(nomeServidorIndicesREST, servidorIndices.servidorConsultaIndicesREST);
			System.out.println(nomeServidorIndicesREST + " registrado!");
		}
		catch (Exception e) {
			System.err.println("erro na main()! " + e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("esperando requisicao...");
	}
}
