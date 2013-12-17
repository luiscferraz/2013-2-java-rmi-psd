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
	private static IServidorAdministrador jIServidorAdministrador = null; 
	
	private static Registry jRegistry = null;
	
	private static boolean logado = false;
	private static String host = "172.16.181.128";

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//exatamente igual ao que estava no slide
			String objName = "rmi://localhost/administradorServer"; //TODO Confirmar nome do Server
			jIServidorAdministrador = (IServidorAdministrador) Naming.lookup(objName);
			
			System.out.println("*** Software Lojista ***");		
			jRegistry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
			
		} catch (Exception e){
			System.out.println("Problemas ao executar o lookup: " + e.getMessage());
		}
		
		
		
	}
	
	private static Integer menuPrincipal(){		
		System.out.println("\nEscolha uma opção:");
		System.out.println("1 - Efetuar Venda.");
		System.out.println("2 - Consultar SPC.");
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
	
	private static boolean menuLogar(){
		System.out.println("CPF: ");
		String vCPF = SoftwareLojista.jScanner.next();
		System.out.print("Senha: ");
		String vSenha= SoftwareLojista.jScanner.next();
		
		try {			
			if (SoftwareLojista.jIServidorAdministrador.logar(vCPF, vSenha)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar efetuar login: " + e.getMessage());
			return false;
		}
		
	}
	
	private static boolean ehDevedor(){
		return true;		
	}
	
	private static ArrayList<Cliente> consultarDevedores(){
	
	}
	
	
	
	private static boolean efetuarVenda(){
		return true;
	}
	
	

}
