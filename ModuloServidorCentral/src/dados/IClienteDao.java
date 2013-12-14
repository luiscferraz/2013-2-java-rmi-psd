package dados;

import java.sql.SQLException;
import java.util.ArrayList;

import basicas.Cliente;

public interface IClienteDao {
	
	public void inserir(Cliente cliente) throws ClassNotFoundException, SQLException;
	
	public boolean existe(String cpf) throws ClassNotFoundException, SQLException;
	
	public Cliente getCliente(String cpf) throws ClassNotFoundException, SQLException;
	
	public ArrayList<Cliente> getClientes() throws ClassNotFoundException, SQLException;
	
	public void atualizar(basicas.Cliente clienteAtualizado) throws ClassNotFoundException, SQLException;
	
}
