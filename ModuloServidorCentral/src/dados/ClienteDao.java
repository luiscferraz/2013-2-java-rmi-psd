package dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDao implements IClienteDao {

	@Override
	public void inserir(basicas.Cliente cliente) throws ClassNotFoundException, SQLException {
		if(!existe(cliente.getCpf())) {
			String sql = "INSERT INTO cliente (cpf, nome, sobrenome, senha) VALUES (?, ?, ?, ?);";
			
			PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
			stm.setString(1, cliente.getCpf());
			stm.setString(2, cliente.getNome());
			stm.setString(3, cliente.getSobrenome());
			stm.setString(4, cliente.getSenha());
			stm.execute();
			
			stm.close();
		} 
	}

	@Override
	public boolean existe(String cpf) throws ClassNotFoundException, SQLException {
		boolean existe;
		
		String sql = "SELECT * FROM cliente WHERE cliente.cpf = ?;";
		PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
		stm.setString(1, cpf);
		
		ResultSet rs = stm.executeQuery();
		
		existe = rs.next();
		
		stm.close();
		rs.close();
		
		return existe;
	}
	
	@Override
	public basicas.Cliente getCliente(String cpf) throws ClassNotFoundException, SQLException {
		basicas.Cliente cliente = null;
		
		String sql = "SELECT * FROM cliente WHERE cliente.cpf = ?;";
		PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
		stm.setString(1, cpf);
		
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			cliente = new basicas.Cliente(rs.getString("nome"), rs.getString("sobrenome"), 
					rs.getString("cpf"), rs.getString("senha"));
		}
		
		stm.close();
		rs.close();
		
		return cliente;
	}

	@Override
	public void atualizar(basicas.Cliente clienteAtualizado) throws ClassNotFoundException, SQLException {
		if(existe(clienteAtualizado.getCpf())) {
			String sql = "UPDATE cliente SET nome = ?, sobrenome = ?, senha = ? WHERE cpf = ?;";
			
			PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
			stm.setString(1, clienteAtualizado.getNome());
			stm.setString(2, clienteAtualizado.getSobrenome());
			stm.setString(3, clienteAtualizado.getSenha());
			stm.setString(4, clienteAtualizado.getCpf());
			stm.executeUpdate();
			
			stm.close();
		} 
	}

}
