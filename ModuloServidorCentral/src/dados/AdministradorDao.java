package dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDao implements IAdministradorDao {

	@Override
	public basicas.Administrador getAdministrador(String cpf) throws ClassNotFoundException, SQLException {
		
		basicas.Administrador adm = null;
		
		String sql = "SELECT * FROM administrador WHERE administrador.cpf = ?;";
		
		PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
		stm.setString(1, cpf);
		
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			adm = new basicas.Administrador(rs.getString("cpf"), rs.getString("senha"));
		}
		
		stm.close();
		rs.close();
		
		return adm;
	}

}
