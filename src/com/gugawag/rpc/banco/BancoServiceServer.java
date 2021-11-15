package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;
    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        contas = new ArrayList<Conta>();
        
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.get(conta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }
    
    @Override
    public String adicionarConta(Conta conta) throws RemoteException{
    	this.contas.add(conta);
    	Conta result = contas.get(this.contas.size() - 1);
    	return "\nNumero: "+ result.getNumero() + "\nSaldo: " + result.getSaldo();
	}
    
    @Override
	public String cadastrarNovaConta(double saldo) throws RemoteException{
    	String numero = Integer.toString(this.contas.size() + 1); 
		Conta conta = new Conta(numero, saldo);
		if (this.pesquisaDeConta(numero) != "Encontrado!") {
			this.remocaoDeConta(numero);
			return this.adicionarConta(conta);
		}
		return this.adicionarConta(conta);
	}
	
    @Override
	public String pesquisaDeConta(String numero) throws RemoteException{
    	if (this.contas.size() != 0) {
    		for (int index = 0; index < this.contas.size(); index ++) {
    			if (this.contas.get(index).getNumero().equals(numero)) {
    				return "Encontrado:\nNumero -> "+this.contas.get(index).getNumero()+""
    						+ "; Saldo -> R$"+ this.contas.get(index).getSaldo();
    			}
    		}
    		return "Essa conta não existe!";
    	}
		return "Não existe nenhuma conta cadastrada!";
	}
	
    @Override
	public String remocaoDeConta(String numero) throws RemoteException{
    	if (this.contas.size() != 0) {
			for (int index = 0; index < this.contas.size(); index ++) {
				if (this.contas.get(index).getNumero().equals(numero)) {
					this.contas.remove(index);
				}
			}
			return "\nConta não encontrada!";
    	}
    	return ". . .";
	}
}
