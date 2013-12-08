package dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
	
	private static Connection conexao;
	
	private FabricaConexao() { }
	
	public static Connection obterConexao() throws ClassNotFoundException, SQLException {
		if(conexao == null) {
			conexao = iniciarConexao();
		}
		return conexao;
	}
	
	private static Connection iniciarConexao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/sccdb";
		Connection conexao = DriverManager.getConnection(url, "root", "");
		return conexao;
	}
	
}
