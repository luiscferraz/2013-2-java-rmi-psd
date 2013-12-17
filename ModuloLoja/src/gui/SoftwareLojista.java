package gui;

import interfaces.IServidorAdministrador;
import interfaces.IServidorCliente;
import interfaces.IServidorLoja;

import java.lang.reflect.Array;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;


public class SoftwareLojista {

	private static Scanner jScanner = new Scanner(System.in);
	private static IServidorLoja jIServidorLojista = null; 
	
	private static Registry jRegistry = null;
	
	private static boolean logado = false;
	private static String host = "172.16.181.128";

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//exatamente igual ao que estava no slide
			String objName = "rmi://localhost/lojistaServer"; //TODO Confirmar nome do Server
			jIServidorLojista = (IServidorLoja) Naming.lookup(objName);
			
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
	
	private static boolean ehDevedor(){
		return true;		
	}
	
	private static ArrayList<Cliente> consultarDevedores(){
	
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
			
			if (SoftwareLojista.jIServidorLojista.realizarCompra(cartao, senha, descricao, valor, moeda)) {
				System.out.println("Compra realizada com sucesso!");
			} else {
				System.out.println("*Erro na realização da compra!");
			}
			
		} catch (Exception e) {
			throw new RuntimeException("*Erro ao tentar realizar a compra: " + e.getMessage());
		}
	}
	
}
