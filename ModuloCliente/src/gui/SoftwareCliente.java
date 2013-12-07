package src.gui;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import src.interfaces.IServidorCliente;

public class SoftwareCliente {
	static Scanner vScanner = new Scanner(System.in);
	static IServidorCliente vServidor = null;
	static Registry vRegistry = null;

	public static void main(String[] args) {
		System.out.println("*** Software Clente ***");
		
		try {
			vRegistry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
		
			while (true) {
				try {
					int vMenuPrincipal = menuPrincipal();
					if (vMenuPrincipal == 1) {
						menuConsultarExtrato();
					} else if (vMenuPrincipal == 2) {
						vServidor = (IServidorCliente) vRegistry.lookup("efetuarPagamento");
					} else if (vMenuPrincipal == 3) {
						vServidor = (IServidorCliente) vRegistry.lookup("logar");
					} else if (vMenuPrincipal == 4) {
						System.out.println("Bye!");
						break;
					} else {
						System.out.println("*Escolha uma opção válida");
					}
				} catch (NumberFormatException e) {
					System.out.println("*Formato inválido");
				}
			}
		} catch (Exception e) {
			System.out.println("*Ocorreu um erro inesperado: " + e.getMessage());
		}
		vScanner.close();
	}
	
	private static void menuConsultarExtrato() {
		System.out.print("\nNumero do cartão: ");
		String vNumeroCartao = vScanner.next();
		System.out.print("Senha: ");
		String vSenha = vScanner.next();
		try {
			vServidor = (IServidorCliente) vRegistry.lookup("consultarExtrato");
			String vConsultarExtrato = vServidor.consultarExtrato(vNumeroCartao, vSenha);
			
			if (vConsultarExtrato == null) {
				System.out.println("*Erro: Número do cartão ou senha inválidos");
			} else {
				System.out.println("Extrato: " + vConsultarExtrato);
			}
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao consultar extrato" + e.getMessage());
		}
	}

	private static int menuPrincipal() throws NumberFormatException
	{
		System.out.println("\nEscolha uma opção:");
		System.out.println("1- Consultar Extrato");
		System.out.println("2- Efetuar Pagamento");
		System.out.println("3- Logar");
		System.out.println("4- Finalizar Software");
		
		String vTexto = vScanner.next();
		return Integer.parseInt(vTexto);
	}
}
