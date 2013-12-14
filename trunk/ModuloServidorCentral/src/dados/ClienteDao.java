package dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basicas.Cliente;

public class ClienteDao implements IClienteDao {

	@Override
	public void inserir(Cliente cliente) throws ClassNotFoundException, SQLException {
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
	public Cliente getCliente(String cpf) throws ClassNotFoundException, SQLException {
		Cliente cliente = null;
		
		String sql = "SELECT * FROM cliente WHERE cliente.cpf = ?;";
		PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
		stm.setString(1, cpf);
		
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			cliente = new Cliente(rs.getString("nome"), rs.getString("sobrenome"), 
					rs.getString("cpf"), rs.getString("senha"));
		}
		
		stm.close();
		rs.close();
		
		return cliente;
	}
	
	@Override
	public ArrayList<Cliente> getClientes() throws ClassNotFoundException, SQLException {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		String sql = "SELECT * FROM cliente;";
		
		ResultSet rs = FabricaConexao.obterConexao().prepareStatement(sql).executeQuery();
		
		while(rs.next()) {
			clientes.add(new Cliente(rs.getString("nome"), rs.getString("sobrenome"), 
					rs.getString("cpf"), rs.getString("senha")));
		}
		
		rs.close();
		
		return clientes;
	}

	@Override
	public void atualizar(Cliente clienteAtualizado) throws ClassNotFoundException, SQLException {
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
