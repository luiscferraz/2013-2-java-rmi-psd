package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import basicas.Administrador;
import basicas.Cartao;
import basicas.Cliente;

public interface IBancoDeDadosServidorCentral {
	// metodos para cadastro de clientes
	public void inserirCliente(Cliente novoCliente) throws ClassNotFoundException, SQLException;
	public Cliente getCliente(String cpf) throws ClassNotFoundException, SQLException;
	public ArrayList<Cliente> getClientes() throws ClassNotFoundException, SQLException; 
	public void atualizarCliente(Cliente clienteAtualizado) throws ClassNotFoundException, SQLException;
	// metodos para o cadastro de administrador
	Administrador getAdministrador(String cpf) throws ClassNotFoundException, SQLException;
	// metodos para o cadastro de cartoes
	public void inserirCartao(Cartao novoCartao) throws ClassNotFoundException, SQLException;
	public Cartao getCartao(String numeroCartao) throws ClassNotFoundException, SQLException;
	public void atualizarCartao(Cartao cartaoAtualizado) throws ClassNotFoundException, SQLException;
}
