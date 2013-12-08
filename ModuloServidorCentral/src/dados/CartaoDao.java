package dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartaoDao implements ICartaoDao {

	@Override
	public void inserir(basicas.Cartao cartao) throws ClassNotFoundException, SQLException {
		if(!existe(cartao.getNumero())) {
			String sql = "INSERT INTO cartao VALUES (?, ?, ?, ?, ?);";
			
			PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
			stm.setString(1, cartao.getNumero());
			stm.setString(2, cartao.getCpf());
			stm.setString(3, cartao.getValidade());
			stm.setString(4, cartao.getCvv());
			stm.setString(5, cartao.getSenha());
			stm.execute();
			
			stm.close();
		}
	}

	@Override
	public boolean existe(String numero) throws ClassNotFoundException, SQLException {
		boolean existe;
		
		String sql = "SELECT * FROM cartao WHERE cartao.numero = ?;";
		
		PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
		stm.setString(1, numero);
		
		ResultSet rs = stm.executeQuery();
		
		existe = rs.next();
		
		stm.close();
		rs.close();
		
		return existe;
	}

	@Override
	public basicas.Cartao getCartao(String numeroCartao) throws ClassNotFoundException, SQLException {
		basicas.Cartao cartao = null;
		
		String sql = "SELECT * FROM cartao WHERE cartao.numero = ?;";
		
		PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
		stm.setString(1, numeroCartao);
		
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			cartao = new basicas.Cartao(rs.getString("numero"), rs.getString("cpf_cliente"), 
					rs.getString("validade"), rs.getString("cvv"), rs.getString("senha"));
		}
		
		stm.close();
		rs.close();
		
		return cartao;
	}

	@Override
	public void atualizar(basicas.Cartao cartaoAtualizado) throws ClassNotFoundException, SQLException {
		if(existe(cartaoAtualizado.getNumero())) {
			String sql = "UPDATE cartao SET cpf_cliente = ?, validade = ?, cvv = ?, senha = ?"
					+ " WHERE numero = ?;";
			
			PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
			stm.setString(1, cartaoAtualizado.getCpf());
			stm.setString(2, cartaoAtualizado.getValidade());
			stm.setString(3, cartaoAtualizado.getCvv());
			stm.setString(4, cartaoAtualizado.getSenha());
			stm.setString(5, cartaoAtualizado.getNumero());
			stm.executeUpdate();
			
			stm.close();
		}
	}

}
