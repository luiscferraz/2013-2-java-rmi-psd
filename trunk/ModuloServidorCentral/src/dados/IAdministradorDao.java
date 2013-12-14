package dados;

import java.sql.SQLException;

import basicas.Administrador;

public interface IAdministradorDao {
	
	public Administrador getAdministrador(String cpf) throws ClassNotFoundException, SQLException;
	
}
