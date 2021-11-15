package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;
    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
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
    public void adicionarConta(Conta conta) {
    	this.contas.add(conta);
	}
    
    @Override
	public void cadastrarNovaConta(double saldo) {
    	String numero = Integer.toString(this.contas.size() + 1); 
		Conta conta = new Conta(numero, saldo);
		if (this.pesquisaDeConta(numero) != null) {
			this.remocaoDeConta(numero);
			this.adicionarConta(conta);
		}
		else {
			this.adicionarConta(conta);
		}
	}
	
    @Override
	public Conta pesquisaDeConta(String numero) {
		for (int index = 0; index < this.contas.size(); index ++) {
			if (this.contas.get(index).getNumero() == numero) {
				return this.contas.get(index);
			}
		}
		return null;
	}
	
    @Override
	public void remocaoDeConta(String numero) {
		for (int index = 0; index < this.contas.size(); index ++) {
			if (this.contas.get(index).getNumero() == numero) {
				this.contas.remove(index);
			}
		}
	}
}
