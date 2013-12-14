package dados;

import java.sql.SQLException;

import basicas.Cartao;

public interface ICartaoDao {
	
	public void inserir(Cartao cartao) throws ClassNotFoundException, SQLException;
	
	public boolean existe(String numero) throws ClassNotFoundException, SQLException;
	
	public Cartao getCartao(String numeroCartao) throws ClassNotFoundException, SQLException;
	
	public void atualizar(Cartao cartaoAtualizado) throws ClassNotFoundException, SQLException;

}
