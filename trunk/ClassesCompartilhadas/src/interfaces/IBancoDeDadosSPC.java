/*
 * Interface para classe do banco de dados
 */
/**
 * 
 * @author Daniel
 *
 */
package interfaces;

/**
 * 
 * @author Daniel
 *
 */
public interface IBancoDeDadosSPC {
	// metodos para cadastro de devedores
	public boolean inserirDevedor(String cpf, float valor);
	public boolean removerDevedor(String cpf);
	public boolean eDevedor(String cpf);
	public String getDevedores();
}
