package gui;

import interfaces.IServidorAdministrador;
import interfaces.IServidorCliente;
import interfaces.IServidorConsultaCadastroNegativo;
import interfaces.IServidorLoja;

import java.lang.reflect.Array;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;


public class SoftwareLojista {

	private static final String IServidorLoja = null;
	private static Scanner jScanner = new Scanner(System.in);
	private static IServidorLoja jIServidorLojista = null; 
	
	private static Registry jRegistry = null;
	
	private static boolean logado = false;
	private static String servidorCentral = "172.16.181.128";
	private static String servidorSPC = "172.16.183.68";
	private static String servidorAdmin = "172.16.183.68";
	private static Registry registroCentral;
	private static Registry registroSPC;
	private static Registry registroAdmin;
	private static IServidorLoja oIServidorLoja;
	private static IServidorConsultaCadastroNegativo oIServidorConsultaCadastroNegativo;
	private static IServidorAdministrador oIServidorAdministrador;

	
	public static void main(String[] args) {
	
		try{
			SoftwareLojista.registroCentral = LocateRegistry.getRegistry(SoftwareLojista.servidorCentral, Registry.REGISTRY_PORT);
			SoftwareLojista.registroSPC = LocateRegistry.getRegistry(SoftwareLojista.servidorSPC, Registry.REGISTRY_PORT);
			SoftwareLojista.registroAdmin = LocateRegistry.getRegistry(SoftwareLojista.servidorAdmin, Registry.REGISTRY_PORT);
			
			SoftwareLojista.oIServidorLoja = (IServidorLoja) SoftwareLojista.registroCentral.lookup("servidor_loja");
			SoftwareLojista.oIServidorConsultaCadastroNegativo = (IServidorConsultaCadastroNegativo) SoftwareLojista.registroSPC.lookup("servidor_consulta_cadastro_negativo");
			SoftwareLojista.oIServidorAdministrador = (IServidorAdministrador) SoftwareLojista.registroAdmin.lookup("servidor_administrador");
			Integer ver = null;
			
			
			System.out.println("*** Software Lojista ***");		

			jRegistry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
			

			while(true){
				System.out.println("*** Software Logista ***");
				while(!SoftwareLojista.logado){
					SoftwareLojista.logado = menuLogar();
				}
				while(SoftwareLojista.logado){
					switch(ver){
						case 1:
							SoftwareLojista.eDevedor();
							ver = null;
							break;
						case 2:
							SoftwareLojista.efetuarVenda();
							ver = null;
							break;
						case 3:
							SoftwareLojista.consultarDevedores();
							ver = null;
							break;
						case 0:
							SoftwareLojista.logado = false;
							break;
						default:
							ver = SoftwareLojista.menuPrincipal();
					}
				}
			}
			

		} catch (Exception e){
			System.out.println("Problemas ao executar o lookup: " + e.getMessage());
		}
		
	}
	
	private static Integer menuPrincipal(){		
		System.out.println("\nEscolha uma opção:");
		System.out.println("1 - Consultar SPC.");
		System.out.println("2 - Efetuar Venda.");
		System.out.println("3 - Consultar Devedores.");
		System.out.println("0 - Sair.");
		System.out.println("Digite:");
		
		String vTexto = SoftwareLojista.jScanner.next();
		
		try{
			return Integer.parseInt(vTexto);
		}catch (Exception e){
			System.out.println("Opção inválida!\n");
			return null;
		}
	}
	
	private static boolean eDevedor(){
		
		System.out.print("\nInforme o numero do CPF: ");
		String cpf = SoftwareLojista.jScanner.next();
		
		try{
			if (SoftwareLojista.oIServidorConsultaCadastroNegativo.eDevedor(cpf)){
				System.out.println("O CPF" + cpf + "está na lista de cadastros negativos.");
				return true;
			}
			else{
				System.out.println("Não é devedor.");
				return false;
			}
			
		}
		catch(Exception e){
			throw new RuntimeException("*Erro." + e.getMessage());
		}
		
	}	
	
	private static String consultarDevedores(){
		
		try{
			String devedores = SoftwareLojista.oIServidorConsultaCadastroNegativo.consultarDevedores();
			return devedores;
		}
		catch(Exception e){
			throw new RuntimeException("*Erro ao listar devedores." + e.getMessage());
		}
	}
	
	private static boolean efetuarVenda(){
		
		try {
			System.out.print("\nInforme o valor da compra: ");
			String valor = SoftwareLojista.jScanner.next();
			float valorCompra = Float.parseFloat(valor);
			System.out.print("\nInforme a moeda da compra: ");
			String moeda = SoftwareLojista.jScanner.next();
			System.out.print("\nInforme o numero do cartao: ");
			String cartao = SoftwareLojista.jScanner.next();
			System.out.print("\nSenha: ");
			String senha = SoftwareLojista.jScanner.next();
			System.out.print("\nDescricao: ");
			String descricao = SoftwareLojista.jScanner.next();
			
			if (SoftwareLojista.oIServidorLoja.realizarCompra(cartao, senha, descricao, valorCompra, moeda)) {
				System.out.println("Compra realizada com sucesso!");
				return true;
			} else {
				System.out.println("*Erro na realização da compra!");
				return false;
			}
			
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao tentar realizar a compra: " + e.getMessage());
		}
		
		
	}
	
	private static boolean menuLogar(){
		System.out.print("\nCPF: ");
		String verCPF = SoftwareLojista.jScanner.next();
		System.out.print("Senha: ");
		String verSenha= SoftwareLojista.jScanner.next();
		
		try {
			
			if (SoftwareLojista.oIServidorAdministrador.logar(verCPF, verSenha)) {
				System.out.println("Login efetuado com sucesso!");
				return true;
			} else {
				System.out.println("*Nao foi possível efetuar login!");
				return false;
			}
		} catch (Exception e) {
			System.out.println("*Erro ao logar: " + e.getMessage());
			return false;
		}
		
	}
	
}
