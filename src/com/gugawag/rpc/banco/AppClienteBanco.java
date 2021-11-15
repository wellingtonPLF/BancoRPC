package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.saldo(conta));
                }
                case 2: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.quantidadeContas());
                }
                case 3: {
                	System.out.println("Digite o saldo que a conta ter�!");
                	double saldo = Double.parseDouble(entrada.next());
                	banco.cadastrarNovaConta(saldo);
                    System.out.println("Conta cadastrada!");
                }
                case 4: {
                	System.out.println("Digite o numero da conta!");
                	String numero = entrada.next();
                	banco.remocaoDeConta(numero);
                	System.out.println("Conta removida!");
                }
                case 5: {
                    System.out.println("Digite o numero da conta!");
                    String numero = entrada.next();
                    System.out.println(banco.pesquisaDeConta(numero));
                }
                
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
    	System.out.println("\n=== Wellington Pessoa De Lima Filho ===");
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Adicionar conta");
        System.out.println("4 - Remover conta");
        System.out.println("5 - Pesquisar conta");
        System.out.println("9 - Sair");
    }

}
