package dados;

import java.sql.SQLException;

public interface IClienteDao {
	
	public void inserir(basicas.Cliente cliente) throws ClassNotFoundException, SQLException;
	
	public boolean existe(String cpf) throws ClassNotFoundException, SQLException;
	
	public basicas.Cliente getCliente(String cpf) throws ClassNotFoundException, SQLException;
	
	public void atualizar(basicas.Cliente clienteAtualizado) throws ClassNotFoundException, SQLException;
	
}
