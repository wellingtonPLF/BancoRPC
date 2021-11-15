package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    void adicionarConta(Conta conta) throws RemoteException;
    void cadastrarNovaConta(double saldo) throws RemoteException;
    void remocaoDeConta(String numero) throws RemoteException;
    Conta pesquisaDeConta(String numero) throws RemoteException;
}
