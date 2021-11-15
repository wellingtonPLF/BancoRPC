package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    String adicionarConta(Conta conta) throws RemoteException;
    String cadastrarNovaConta(double saldo) throws RemoteException;
    String remocaoDeConta(String numero) throws RemoteException;
    String pesquisaDeConta(String numero) throws RemoteException;
}
