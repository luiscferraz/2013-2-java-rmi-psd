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
	private static String host = "172.16.181.128";
	private static Registry oRegistry;
	private static IServidorLoja oIServidorLoja;
	private static IServidorConsultaCadastroNegativo oIServidorConsultaCadastroNegativo;

	
	public static void main(String[] args) {
	
		try{
			SoftwareLojista.oRegistry = LocateRegistry.getRegistry(SoftwareLojista.host, Registry.REGISTRY_PORT);
			SoftwareLojista.oIServidorLoja = (IServidorLoja) SoftwareLojista.oRegistry.lookup("servidor_loja");
			SoftwareLojista.oIServidorConsultaCadastroNegativo = (IServidorConsultaCadastroNegativo) SoftwareLojista.oRegistry.lookup("servidor_consulta_cadastro_negativo");
			Integer ver = null;
			
			System.out.println("*** Software Lojista ***");		
			jRegistry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
			
		} catch (Exception e){
			System.out.println("Problemas ao executar o lookup: " + e.getMessage());
		}
		
	}
	
	private static Integer menuPrincipal(){		
		System.out.println("\nEscolha uma opção:");
		System.out.println("1 - Consulta SPC.");
		System.out.println("2 - Efetuar Venda.");
		System.out.println("3 - Consulta devedor.");
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

		System.out.print("\nInforme o numero do item a ser comprado: ");
		
		System.out.print("\n1. Revista - R$ 11.90\n2. DVD - R$ 29.90\n3. Porta-Retrato - R$ 45.20\n\n ");
		String opcao = SoftwareLojista.jScanner.next();
		
		try {
			float valor = 0;
			String moeda = "Real";
			
			if (opcao == "1") {
				valor = (float) 11.9;
				moeda = "Real";
			} else if (opcao == "2") {
				valor = (float) 29.9;
				moeda = "Real";
			} else if (opcao == "3") {
				valor = (float) 45.2;
				moeda = "Real";
			} else {
				System.out.print("\nOpção incorreta: ");
			}
			
			System.out.print("\nInforme o numero do cartao: ");
			String cartao = SoftwareLojista.jScanner.next();
			System.out.print("\nSenha: ");
			String senha = SoftwareLojista.jScanner.next();
			System.out.print("\nDescricao: ");
			String descricao = SoftwareLojista.jScanner.next();
			
			if (SoftwareLojista.oIServidorLoja.realizarCompra(cartao, senha, descricao, valor, moeda)) {
				System.out.println("Compra realizada com sucesso!");
			} else {
				System.out.println("*Erro na realização da compra!");
			}
			
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao tentar realizar a compra: " + e.getMessage());
		}

		System.out.print("\nInforme o numero do item a ser comprado: ");
		
		System.out.print("\n1. Revista - R$ 11.90\n2. DVD - R$ 29.90\n3. Porta-Retrato - R$ 45.20\n\n ");
		opcao = SoftwareLojista.jScanner.next();
		
		try {
			float valor = 0;
			String moeda = "Real";
			
			if (opcao == "1") {
				valor = (float) 11.9;
				moeda = "Real";
			} else if (opcao == "2") {
				valor = (float) 29.9;
				moeda = "Real";
			} else if (opcao == "3") {
				valor = (float) 45.2;
				moeda = "Real";
			} else {
				System.out.print("\nOpção incorreta: ");
			}
			
			System.out.print("\nInforme o numero do cartao: ");
			String cartao = SoftwareLojista.jScanner.next();
			System.out.print("\nSenha: ");
			String senha = SoftwareLojista.jScanner.next();
			System.out.print("\nDescricao: ");
			String descricao = SoftwareLojista.jScanner.next();
			
			if (SoftwareLojista.jIServidorLojista.realizarCompra(cartao, senha, descricao, valor, moeda)) {
				System.out.println("Compra realizada com sucesso!");
				return true;
			} else {
				System.out.println("*Erro na realização da compra!");
			}
			
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao tentar realizar a compra: " + e.getMessage());
		}
		return logado;
		
	}
	
}
