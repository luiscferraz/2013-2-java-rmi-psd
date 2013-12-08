package dados;

import java.sql.SQLException;

public interface ICartaoDao {
	
	public void inserir(basicas.Cartao cartao) throws ClassNotFoundException, SQLException;
	
	public boolean existe(String numero) throws ClassNotFoundException, SQLException;
	
	public basicas.Cartao getCartao(String numeroCartao) throws ClassNotFoundException, SQLException;
	
	public void atualizar(basicas.Cartao cartaoAtualizado) throws ClassNotFoundException, SQLException;

}
