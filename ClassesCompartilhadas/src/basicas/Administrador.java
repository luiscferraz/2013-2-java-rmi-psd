package basicas;

public class Administrador {
	String cpf;
	String senha;
	
	public Administrador(String cpf, String senha) {
		this.cpf = cpf;
		this.senha = senha;
	}
	
	public Administrador() {
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
