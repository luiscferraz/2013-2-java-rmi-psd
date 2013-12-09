package src.gui;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import src.interfaces.IServidorCliente;

public class SoftwareCliente {
	static Scanner aScanner = new Scanner(System.in);
	static IServidorCliente aIServidorCliente = null; 
	static Registry aRegistry = null;
	static boolean aLogado = false;

	public static void main(String[] args) {
		try {
			String objName = "rmi://localhost/clienteServer"; //TODO Confirmar nome do Server
			aIServidorCliente = (IServidorCliente) Naming.lookup(objName);
			
			System.out.println("*** Software Clente ***");
		
			aRegistry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
		
			while (true) {
				try {
					int vMenuPrincipal = menuPrincipal();
					if (vMenuPrincipal == 1) {
						if (aLogado) {
							menuConsultarExtrato();
						} else {
							System.out.println("*Precisa estar logado");
						}
					} else if (vMenuPrincipal == 2) {
						if (aLogado) {
							menuEfetuarPagamento();
						} else {
							System.out.println("*Precisa estar logado");
						}
					} else if (vMenuPrincipal == 3) {
						if (!aLogado) {
							menuLogar();
						} else {
							System.out.println("*Login já foi efetuado");
						}
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
			System.out.println(e.getMessage());
		}
		aScanner.close();
	}
	
	private static int menuPrincipal() throws NumberFormatException
	{
		System.out.println("\nEscolha uma opção:");
		System.out.println("1- Consultar Extrato");
		System.out.println("2- Efetuar Pagamento");
		System.out.println("3- Logar");
		System.out.println("4- Finalizar Software");
		
		String vTexto = aScanner.next();
		return Integer.parseInt(vTexto);
	}
	
	private static void menuConsultarExtrato() {
		System.out.print("\nNúmero do cartão: ");
		String vNumeroCartao = aScanner.next();
		System.out.print("Senha: ");
		String vSenha = aScanner.next();
		try {
			String vConsultarExtrato = aIServidorCliente.consultarExtrato(vNumeroCartao, vSenha);
			
			if (vConsultarExtrato == null) {
				System.out.println("*Número do cartão ou senha inválidos");
			} else {
				System.out.println("Extrato: " + vConsultarExtrato);
			}
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao consultar extrato: " + e.getMessage());
		}
	}
	
	private static void menuEfetuarPagamento() {
		System.out.print("\nNúmero do cartão: ");
		String vNumeroCartao = aScanner.next();

		try {
			boolean vPagamentoEfetuado = aIServidorCliente.efetuarPagamento(vNumeroCartao);
			
			if (vPagamentoEfetuado) {
				System.out.println("Pagamento efetuado com sucesso!");
			} else {
				System.out.println("*Não foi possível efetuar pagamento");
			}
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao efetuar pagamento: " + e.getMessage());
		}
	}
	
	private static void menuLogar() {
		System.out.print("\nCPF: ");
		String vCPF = aScanner.next();
		System.out.print("Senha: ");
		String vSenha= aScanner.next();
		
		try {
			aLogado = aIServidorCliente.logar(vCPF, vSenha);
			
			if (aLogado) {
				System.out.println("Login efetuado com sucesso!");
			} else {
				System.out.println("*Não foi possível efetuar login");
			}
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao logar: " + e.getMessage());
		}
	}
}
