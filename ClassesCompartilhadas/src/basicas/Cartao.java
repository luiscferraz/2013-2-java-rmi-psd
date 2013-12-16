/*
 * Classe Cartao
 * 
 * autor: Victor Medeiros
 * 
 * Anderson
 */

package basicas;

//testando

import java.util.ArrayList;

public class Cartao {
	private String cpf;
	private String numero;
	private String validade; 
	private String cvv;
	private String senha;
	private ArrayList<Compra> compras;
	private boolean pagamentoEfetuado;
	
	public Cartao() {
		
	}
	
	public Cartao(String cpf, String numero, String validade, String cvv, String senha) {
		this.cpf = cpf;
		this.numero = numero;
		this.validade = validade;
		this.cvv = cvv;
		this.senha = senha;
		this.compras = new ArrayList<Compra>();
		this.pagamentoEfetuado = true;
	}
	
	public ArrayList<Compra> getCompras() {
		return compras;
	}
	
	public void inserirCompra(String descricao, float valor) {
		this.compras.add(new Compra(descricao, valor));
		this.pagamentoEfetuado = false;
	}

	public boolean isPagamentoEfetuado() {
		return pagamentoEfetuado;
	}
	
	public void efetuarPagamento() {
		this.compras.clear();
		this.pagamentoEfetuado = true;
	}
	
	public float getTotalCompras() {
		float totalCompras = 0.0f;
		for(Compra compra : compras) {
			totalCompras += compra.getValor();
		}
		return totalCompras;
	}
	
	public String getExtratoCompras() {
		String retorno = "DESCRICAO\t\t\tVALOR\n";
		
		for(Compra compra : compras) {
			retorno += compra.getDescricao() + "\t\t\t" + compra.getValor() + "\n";
		}
		retorno = "TOTAL\t\t\t" + this.getTotalCompras() + "\n";
		
		return retorno;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Cartao [cpf=" + cpf + ", numero=" + numero + ", validade="
				+ validade + ", compras=" + compras + ", pagamentoEfetuado="
				+ pagamentoEfetuado + "]";
	}
}
