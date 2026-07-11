package fintrack.app;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import fintrack.controller.FinTracker;
import fintrack.model.*;

public class Main{
    public static void limparTela() {
        try {
            String sistema = System.getProperty("os.name").toLowerCase();
            if (sistema.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static boolean decisao(Scanner option, String pergunta){
        String resposta;
        while(true){
            System.out.println(pergunta + "[s/n]");
            resposta = option.nextLine().trim().toLowerCase();

            if(resposta.equals("s")){
                return true;
            }else if(resposta.equals("n")){
                return false;
            }else{
                System.out.println("Opcao invalida! Digite somente 's' ou 'n'");
            }
        }
    }
    public static int lerInt(Scanner option){
        while (true) {
            try {
                int escolha = option.nextInt();
                option.nextLine();
                return escolha;
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida digite somente numeros!");
                option.nextLine();
            }    
        }
    }
    public static double lerDouble(Scanner option){
        while (true) {
            try {
                double escolha = option.nextDouble();
                option.nextLine();
                return escolha;
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida digite somente numeros!");
                option.nextLine();
            }    
        }   
    }
    public static void main(String[] args){
        limparTela();
        FinTracker tracker = new FinTracker();
        Scanner option = new Scanner(System.in);
        while(true){
            System.out.println("===== FINTRACK - SEU CONTROLE FINANCEIRO =====");
            System.out.println("1. Adicionar nova transação");
            System.out.println("2. Listar transações");
            System.out.println("3. Mostrar saldo atual");
            System.out.println("4. Remover transação");
            System.out.println("5. Sair");
            System.out.println("Escolha uma opção:");
            
            int opc = lerInt(option);
            switch (opc) {
                case 1:
                    limparTela();
                    System.out.println("Descricao:");
                    String descricao = option.nextLine();

                    System.out.println("Valor:");
                    double valor = lerDouble(option);

                    boolean ehReceita = decisao(option, "É receita?");

                    boolean ehMensal = decisao(option, "É uma transacao mensal?");

                    LocalDate data = LocalDate.now();
                    int novoId = tracker.getProximoId();

                    Transacao nova;
                    if(ehMensal){
                        System.out.println("Qual o dia do mes?");
                        int dia = lerInt(option);
                        nova = new TransacaoMensal(novoId, descricao, valor, ehReceita, data, dia);
                    }else{
                        nova = new Transacao(novoId, descricao, valor, ehReceita, data);
                    }
                    tracker.adicionarTransacao(nova);
                    System.out.println("Transacao adicionada!");
                    sleep(2000);
                    limparTela();
                    break;
                case 2:
                    limparTela();
                    tracker.listarTransacao();
                    break;
                case 3:
                    limparTela();
                    double saldo = tracker.calcularSaldoTotal();
                    System.out.println("\nO valor total do seu saldo atual é: R$ " + String.format("%.2f\n", saldo));
                    break;
                case 4:
                    limparTela();
                    tracker.listarTransacao();
                    System.out.println("Qual o id da transacao a ser removida?");
                    int id = lerInt(option);
                    if(tracker.removerTransacao(id)){
                        System.out.println("Transacao removida com sucesso");
                        sleep(2000);
                    }else{
                        System.out.println("Numero id nao consta na lista de transcacoes");
                        sleep(2000);
                    }
                    limparTela();
                    break;
                case 5:
                    limparTela();
                    System.out.println("Obrigado!");
                    option.close();
                    sleep(2000);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao invalida\n");
                    break;
            }
        }
    }
}