/*
 * teste Rafael
 * 
 * */
package basicas;

public class Cliente {
	public String nome;
	public String sobrenome;
	public String cpf;
	public String senha;
	public String numeroDoCartao;
	
	public Cliente(){
		
	}
	
	public Cliente(String nome, String sobrenome, String cpf, String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.senha = senha;
		this.numeroDoCartao = "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	public String getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public void setNumeroDoCartao(String numeroDoCartao) {
		this.numeroDoCartao = numeroDoCartao;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", sobrenome=" + sobrenome + ", cpf="
				+ cpf + ", numeroDoCartao="
				+ numeroDoCartao + "]";
	}
}