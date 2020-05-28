package exercicios01;

import java.util.Random;
import java.util.Scanner;

public class ContaBancaria {
	
	private String nome;
    private int conta, saques;
    private double saldo;
    Scanner entrada = new Scanner(System.in);
	private static Scanner entrada2;

    public ContaBancaria(String nome, int conta, double saldo_inicial){
        this.nome=nome;
        this.conta=conta;
        saldo=saldo_inicial;
        saques=0;
    }

    public void mostraDados(){
        System.out.println("\tEXTRATO");
        System.out.println("Nome: " + this.nome);
        System.out.println("Número da conta: " + this.conta);
        System.out.printf("Saldo atual: %.2f\n",this.saldo);
        System.out.println("Saques realizados hoje: " + this.saques + "\n");

    }

    public void retira(double valor){
        if(saldo >= valor){
            saldo -= valor;
            saques++;
            System.out.println("Sacado: " + valor);
            System.out.println("Novo saldo: " + saldo + "\n");
        } else {
            System.out.println("Saldo insuficiente. Faça um depósito\n");
        }
    }

    public void deposita(double valor)
    {
        saldo += valor;
        System.out.println("Depositado: " + valor);
        System.out.println("Novo saldo: " + saldo + "\n");
    }

    public void iniciar(){
        int opcao;

        do{
            exibeMenu();
            opcao = entrada.nextInt();
            escolheOpcao(opcao);
        }while(opcao!=4);
    }

    public void exibeMenu(){

        System.out.println("\t Escolha a opção desejada");
        System.out.println("1 - Consultar Extrato");
        System.out.println("2 - Sacar");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sair\n");
        System.out.print("Opção: ");

    }

    public void escolheOpcao(int opcao){
        double valor;

        switch( opcao ){
            case 1:    
                    mostraDados();
                    break;
            case 2: 
                    if(saques<3){
                        System.out.print("Quanto deseja sacar: ");
                        valor = entrada.nextDouble();
                        retira(valor);
                    } else{
                        System.out.println("Limite de saques diários atingidos.\n");
                    }
                    break;

            case 3:
                    System.out.print("Quanto deseja depositar: ");
                    valor = entrada.nextDouble();
                    deposita(valor);
                    break;

            case 4: 
                    System.out.println("Sistema encerrado.");
                    break;

            default:
                    System.out.println("Opção inválida");
        }
    }
	
	 public static void main(String[] args){
	        String nome;
	        double inicial;
	        entrada2 = new Scanner(System.in);
	        Random numero = new Random();
	        int conta = 1 + numero.nextInt(9999);

	        System.out.println("Cadastrando novo cliente.");
	        System.out.print("Ente com seu nome: ");
	        nome = entrada2.nextLine();

	        System.out.print("Entre com o valor inicial depositado na conta: ");
	        inicial = entrada2.nextDouble();

	        ContaBancaria minhaConta = new ContaBancaria(nome, conta, inicial);
	        minhaConta.iniciar();
	    }

}
