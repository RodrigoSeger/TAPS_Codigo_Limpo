import java.util.Random;
import java.util.Scanner;

public class ContaBancaria {
  private final String nomeCliente;
  private final int numeroConta;
  private int quantidadeSaques;
  private double saldoConta;
  Scanner entradaTeclado = new Scanner(System.in);
  private static Scanner entradaTeclado2;

  // Construtor da Conta Bancária do cliente do banco
  public ContaBancaria(final String nomeCliente, final int numeroConta, final double saldo_inicial) {
    this.nomeCliente = nomeCliente;
    this.numeroConta = numeroConta;
    saldoConta = saldo_inicial;
    quantidadeSaques = 0;
  }

  // Função para imprimir o extrato na tela do cliente
  public void mostrarDadosCliente() {
    System.out.println("\tEXTRATO");
    System.out.println("Nome: " + this.nomeCliente);
    System.out.println("Numero da conta: " + this.numeroConta);
    System.out.printf("Saldo atual: %.2f\n", this.saldoConta);
    System.out.println("Saques realizados hoje: " + this.quantidadeSaques + "\n");
  }

  // Função para realizar a retirada do valor no saldo do Cliente
  public void retirarValor(final double valor) {
    if (saldoConta >= valor) {
      saldoConta -= valor;
      quantidadeSaques++;
      System.out.println("Realizando saque, favor aguardar...");
      System.out.println("Saque realizado com sucesso!");
      System.out.println("Valor sacado: " + valor);
      System.out.println("Novo saldo: " + saldoConta + "\n");
    } else {
      System.out.println("Saldo insuficiente. Por favor, realize um deposito\n");
    }
  }

  // Função para realizar o depósito do valor no saldo do Cliente
  public void depositarValor(final double valor) {
    saldoConta += valor;
    System.out.println("Realizando depósito, favor aguardar...");
    System.out.println("Depósito realizado com sucesso!");
    System.out.println("Valor depositado: " + valor);
    System.out.println("Novo saldo: " + saldoConta + "\n");
  }

  // Função para inciar o sistema bancário após cadastramento do Cliente
  public void iniciarSistema() {
    int opcao;

    do {
      exibeMenu();
      opcao = entradaTeclado.nextInt();
      escolheOpcao(opcao);
    } while (opcao != 4);
  }

  // Função para exibição de menu com opções para o cliente escolher
  public void exibeMenu() {
    System.out.println();
    System.out.println("\tSeja bem-vindo " + this.nomeCliente + " ao banco!");
    System.out.println("\tPor favor, escolha a opcao desejada:");
    System.out.println("1 - Consultar Extrato");
    System.out.println("2 - Sacar");
    System.out.println("3 - Depositar");
    System.out.println("4 - Sair\n");
    System.out.print("Opcao: ");
  }

  // Função para checar qual das opções o cliente escolheu e exibir a ação correta
  public void escolheOpcao(final int opcao) {
    double valor;

    switch (opcao) {
      case 1:
        System.out.println();
        System.out.println("Imprimindo extrato, favor aguardar...");
        System.out.println("Extrato impresso com sucesso!");
        mostrarDadosCliente();
        break;
      case 2:
        System.out.println();
        if (quantidadeSaques < 3) {
          System.out.print("Digite quanto deseja sacar: ");
          valor = entradaTeclado.nextDouble();
          retirarValor(valor);
        } else {
          System.out.println("Limite de saques diarios atingidos! Por favor, tente novamente outro dia.\n");
        }
        break;
      case 3:
        System.out.println();
        System.out.print("Digite quanto deseja depositar: ");
        valor = entradaTeclado.nextDouble();
        depositarValor(valor);
        break;
      case 4:
        System.out.println();
        System.out.println(this.nomeCliente + " obrigado por utilizar o nosso banco! Volte sempre!");
        System.out.println("Sistema encerrando, favor aguardar...");
        System.out.println("Sistema encerrado.");
        break;
      default:
        System.out.println();
        System.out.println("Opcao invalida! Por favor, tentar novamente!");
    }
  }

  // Função Main - para inicialização do sistema na hora de rodar o programa e
  // chamar as outras funções
  public static void main(final String[] args) {
    String nome = "";
    double inicial = 0;
    entradaTeclado2 = new Scanner(System.in);
    final Random numeroAleatorio = new Random();
    final int numeroConta = 1 + numeroAleatorio.nextInt(9999);

    System.out.println("Iniciando sistema bancário, por favor aguarde...");
    System.out.println("Seja bem-vindo ao sistema bancário!");
    System.out.println("Cadastrando novo cliente.");
    System.out.print("Por favor, digite seu nome (apenas letras): ");
    try {
      nome = entradaTeclado2.nextLine();
    } catch (Exception e) {
      System.out.println("Digite apenas letras.");
    }

    System.out.print("Digite o valor inicial depositado na conta (apenas numeros): ");
    try {
      inicial = entradaTeclado2.nextDouble();
    } catch (Exception e) {
      System.out.println("Digite apenas numeros.");
    }

    final ContaBancaria minhaConta = new ContaBancaria(nome, numeroConta, inicial);
    minhaConta.iniciarSistema();
  }
}
