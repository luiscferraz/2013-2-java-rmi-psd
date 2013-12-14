package dados;

import interfaces.IBancoDeDadosServidorCentral;

import java.sql.SQLException;
import java.util.ArrayList;

import basicas.Cliente;

public class BancoDadosServidorCentral  implements IBancoDeDadosServidorCentral {

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
	
	@Override
	public void inserirCliente(basicas.Cliente cliente) throws ClassNotFoundException, SQLException {
		clienteDao.inserir(cliente);
	}
	
	@Override
	public basicas.Cliente getCliente(String cpf) throws ClassNotFoundException, SQLException {
		return clienteDao.getCliente(cpf);
	}
	
	@Override
	public ArrayList<Cliente> getClientes() throws ClassNotFoundException, SQLException {
		return clienteDao.getClientes();
	}
	
	@Override
	public void atualizarCliente(basicas.Cliente clienteAtualizado) throws ClassNotFoundException, SQLException {
		clienteDao.atualizar(clienteAtualizado);
	}
	
	@Override
	public void inserirCartao(basicas.Cartao cartao) throws ClassNotFoundException, SQLException {
		cartaoDao.inserir(cartao);
	}
	
	@Override
	public basicas.Cartao getCartao(String numero) throws ClassNotFoundException, SQLException {
		return cartaoDao.getCartao(numero);
	}
	
	@Override
	public void atualizarCartao(basicas.Cartao cartaoAtualizado) throws ClassNotFoundException, SQLException {
		cartaoDao.atualizar(cartaoAtualizado);
	}
	
	@Override
	public basicas.Administrador getAdministrador(String cpf) throws ClassNotFoundException, SQLException {
		return administradorDao.getAdministrador(cpf);
	}

}
