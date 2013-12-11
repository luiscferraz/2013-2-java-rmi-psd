package servidor;

import java.rmi.RemoteException;

import interfaces.IServidorConsultaIndices;

/**
 * 
 * @author Erick Haendel <erickhaendel@gmail.com>
 *
 */
public class ServidorConsultaIndices implements IServidorConsultaIndices {
	/**
	 * Converter valores de Dolar para real, tendo como base o valor : 2,30889826;
	 * @param float Valor a ser convetido
	 * @return float Valor apos conversão;
	 */
	public float converterDolarReal(float valor) throws RemoteException{
		
		float valor_dolar = 2.30889826f;
		float valor_fim  = valor_dolar * valor;
		
		return valor_fim;
	};
	/**
	 * Converter valores de Dolar para real, tendo como base o valor : 3.17681312;
	 * @param float Valor a ser convetido
	 * @return float Valor apos conversão;
	 */
	public float converterEuroReal(float valor) throws RemoteException{
		float valor_dolar = 3.17681312f;
		float valor_fim  = valor_dolar * valor;
		
		return valor_fim;
	};
	public float consultarIOF() throws RemoteException{
		float valor = 0.38f;
		return valor;
	};
}
