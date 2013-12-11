package dados;

import java.sql.SQLException;

public class BancoDadosServidorCentral {

	private static BancoDadosServidorCentral instancia;
	private IClienteDao clienteDao;
	private ICartaoDao cartaoDao;
	private IAdministradorDao administradorDao;
	
	private BancoDadosServidorCentral() {
		clienteDao = new ClienteDao();
		cartaoDao = new CartaoDao();
		administradorDao = new AdministradorDao();
	}
	
	public static BancoDadosServidorCentral obterInstancia() {
		if(instancia == null) {
			instancia = new BancoDadosServidorCentral();
		}
		return instancia;
	}
	
	public void inserirCliente(basicas.Cliente cliente) throws ClassNotFoundException, SQLException {
		clienteDao.inserir(cliente);
	}
	
	public basicas.Cliente getCliente(String cpf) throws ClassNotFoundException, SQLException {
		return clienteDao.getCliente(cpf);
	}
	
	public void atualizarCliente(basicas.Cliente clienteAtualizado) throws ClassNotFoundException, SQLException {
		clienteDao.atualizar(clienteAtualizado);
	}
	
	public void inserirCartao(basicas.Cartao cartao) throws ClassNotFoundException, SQLException {
		cartaoDao.inserir(cartao);
	}
	
	public basicas.Cartao getCartao(String numero) throws ClassNotFoundException, SQLException {
		return cartaoDao.getCartao(numero);
	}
	
	public void atualizarCartao(basicas.Cartao cartaoAtualizado) throws ClassNotFoundException, SQLException {
		cartaoDao.atualizar(cartaoAtualizado);
	}
	
	public basicas.Administrador getAdministrador(String cpf) throws ClassNotFoundException, SQLException {
		return administradorDao.getAdministrador(cpf);
	}
	
}
