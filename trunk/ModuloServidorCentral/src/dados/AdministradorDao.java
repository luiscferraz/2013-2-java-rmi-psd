package dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import basicas.Administrador;

public class AdministradorDao implements IAdministradorDao {

	@Override
	public Administrador getAdministrador(String cpf) throws ClassNotFoundException, SQLException {
		
		Administrador adm = null;
		
		String sql = "SELECT * FROM administrador WHERE administrador.cpf = ?;";
		
		PreparedStatement stm = FabricaConexao.obterConexao().prepareStatement(sql);
		stm.setString(1, cpf);
		
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			adm = new Administrador(rs.getString("cpf"), rs.getString("senha"));
		}
		
		stm.close();
		rs.close();
		
		return adm;
	}

}
