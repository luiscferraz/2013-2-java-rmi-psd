package interfaces;

import java.util.ArrayList;

import basicas.Administrador;
import basicas.Cartao;
import basicas.Cliente;

public interface IBancoDeDadosServidorCentral {
	// metodos para cadastro de clientes
	public void inserirCliente(Cliente novoCliente);
	public Cliente getCliente(String cpf);
	public ArrayList<Cliente> getClientes(); 
	public void atualizarCliente(Cliente clienteAtualizado);
	// metodos para o cadastro de administrador
	Administrador getAdministrador(String cpf);
	// metodos para o cadastro de cartoes
	public void inserirCartao(Cartao novoCartao);
	public Cartao getCartao(String numeroCartao);
	public void atualizarCartao(Cartao cartaoAtualizado);
}
