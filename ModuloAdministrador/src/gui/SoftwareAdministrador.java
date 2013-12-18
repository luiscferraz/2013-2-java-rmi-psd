package gui;

import interfaces.IServidorAdministrador;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class SoftwareAdministrador {
	
	private static Scanner oScanner = new Scanner(System.in);
	private static IServidorAdministrador oIServidorAdministrador = null; 
	private static Registry oRegistry = null;
	private static boolean logado = false;
	private static String host = "localhost";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SoftwareAdministrador.oRegistry = LocateRegistry.getRegistry(SoftwareAdministrador.host, Registry.REGISTRY_PORT);
		SoftwareAdministrador.oIServidorAdministrador = (IServidorAdministrador) SoftwareAdministrador.oRegistry.lookup("servidor_administrador");
		int ver = -1;
		
		while(true){
			System.out.println("*** Software Administrador ***");
			while(!SoftwareAdministrador.logado){
				SoftwareAdministrador.logado = menuLogar();
			}
			while(SoftwareAdministrador.logado){
				switch(ver){
					case 1:
						SoftwareAdministrador.menuConsultarClientes();
						ver = -1;
						break;
					case 2:
						SoftwareAdministrador.menuConsultarCartoes();
						ver = -1;
						break;
					case 3:
						SoftwareAdministrador.menuCadastrarCliente();
						ver = -1;
						break;
					case 4:
						SoftwareAdministrador.menuCadastrarCartao();
						ver = -1;
						break;
					case 0:
						SoftwareAdministrador.logado = false;
						ver = -1;
						break;
					default:
						ver = SoftwareAdministrador.menuPrincipal();
				}
			}
		}

	}
	private static boolean menuLogar(){
		System.out.print("\nCPF: ");
		String verCPF = SoftwareAdministrador.oScanner.next();
		System.out.print("Senha: ");
		String verSenha= SoftwareAdministrador.oScanner.next();
		
		try {
			
			if (SoftwareAdministrador.oIServidorAdministrador.logar(verCPF, verSenha)) {
				System.out.println("Login efetuado com sucesso!");
				return true;
			} else {
				System.out.println("*Nao foi possovel efetuar login!");
				return false;
			}
		} catch (Exception e) {
			System.out.println("*Erro ao logar: " + e.getMessage());
			return false;
		}
		
	}
	private static Integer menuPrincipal(){
		
		System.out.println("\nEscolha uma opcao:");
		System.out.println("1 - Consultar Clientes");
		System.out.println("2 - Consultar Cartoes");
		System.out.println("3 - Cadastrar Cliente");
		System.out.println("4 - Cadastrar Cartao");
		System.out.println("0 - Sair");
		System.out.println("Digite:");
		
		String verTexto = SoftwareAdministrador.oScanner.next();
		try{
			return Integer.parseInt(verTexto);
		}catch (Exception e){
			System.out.println("*Erro: valor invalido!\n");
			return null;
		}
		
		
	}
	private static void menuConsultarClientes(){
		System.out.print("\n1 - Todos");
		System.out.print("\n1 - Especifico");
		String ver = SoftwareAdministrador.oScanner.next();
		
		switch(Integer.parseInt(ver)){
		
			case 1:
				try {
					String verConsultarClientes = SoftwareAdministrador.oIServidorAdministrador.consultarClientes();

					System.out.println("Clientes: \n" + verConsultarClientes);
				} catch (Exception e) {
					throw new RuntimeException("*Erro ao consultar os clientes: " + e.getMessage());
				}
				break;
			case 2:
				System.out.print("\nCPF do cliente: ");
				String verCpf = SoftwareAdministrador.oScanner.next();
				try {
					String verConsultarCliente = SoftwareAdministrador.oIServidorAdministrador.consultarCliente(verCpf);
					
					if (verConsultarCliente == null) {
						System.out.println("*CPF invalido!");
					} else {
						System.out.println("Cliente: \n" + verConsultarCliente);
					}
				} catch (Exception e) {
					System.out.println("*Erro ao consultar cliente: " + e.getMessage());
				}
				break;
			default:
				System.out.print("Valor invalido!");
		}
	}
	private static void menuConsultarCartoes(){
		System.out.print("\nNumero do Cartao: ");
		String verNumCartao = SoftwareAdministrador.oScanner.next();
		try {
			String verConsultarCartao = SoftwareAdministrador.oIServidorAdministrador.consultarCartao(verNumCartao);
			
			if (verConsultarCartao == null) {
				System.out.println("*Numero do cartao invalido!");
			} else {
				System.out.println("Cartao: \n" + verConsultarCartao);
			}
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao consultar cartao: " + e.getMessage());
		}
	}
	private static void menuCadastrarCliente(){
		System.out.print("\nNome: ");
		String verNome = SoftwareAdministrador.oScanner.next();
		System.out.print("\nSobrenome: ");
		String verSobrenome = SoftwareAdministrador.oScanner.next();
		System.out.print("\nCPF: ");
		String verCpf = SoftwareAdministrador.oScanner.next();
		System.out.print("\nSenha: ");
		String verSenha = SoftwareAdministrador.oScanner.next();
		try {
			if (SoftwareAdministrador.oIServidorAdministrador.cadastrarCliente(verNome, verSobrenome, verCpf, verSenha)) {
				System.out.println("Cliente Casdatrado com sucesso!");
			} else {
				System.out.println("*Erro: cliente ja existe!");
			}
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao tentar cadastrar um cliente: " + e.getMessage());
		}
	}
	private static void menuCadastrarCartao(){
		System.out.print("\nCPF: ");
		String verCpf = SoftwareAdministrador.oScanner.next();
		System.out.print("\nNumero do cartao: ");
		String verNum = SoftwareAdministrador.oScanner.next();
		System.out.print("\nValidade: ");
		String verValidade = SoftwareAdministrador.oScanner.next();
		System.out.print("\nCVV: ");
		String verCvv = SoftwareAdministrador.oScanner.next();
		System.out.print("\nSenha: ");
		String verSenha = SoftwareAdministrador.oScanner.next();
		try {
			if (SoftwareAdministrador.oIServidorAdministrador.cadastrarCartao(verCpf, verNum, verValidade, verCvv, verSenha)) {
				System.out.println("Cartao Casdatrado com sucesso!");
			} else {
				System.out.println("*Erro: cartao ja existe!");
			}
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao tentar cadastrar um cartao: " + e.getMessage());
		}
	}

}
