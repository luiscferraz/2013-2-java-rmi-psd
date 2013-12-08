package dados;

import java.sql.SQLException;

public interface IAdministradorDao {
	public basicas.Administrador getAdministrador(String cpf) throws ClassNotFoundException, SQLException;
}
