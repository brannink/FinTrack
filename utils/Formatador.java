package fintrack.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe utilitária com métodos auxiliares para entrada de dados,
 * formatação de tela e controle de tempo de execução.
 */
public class Formatador {
    /**
     * Limpa a tela do console, detectando o sistema operacional
     * (Windows ou Unix/Linux) e executando o comando apropriado.
     */
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
    /**
     * Pausa a execução da thread atual pelo tempo especificado.
     * @param time tempo de pausa em milissegundos
     */
    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Faz uma pergunta de sim/não ao usuário, repetindo até
     * receber uma resposta válida ('s' ou 'n').
     * @param option Scanner usado para ler a entrada do usuário
     * @param pergunta texto da pergunta a ser exibida
     * @return true se a resposta for 's', false se for 'n'
     */
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
    /**
     * Lê um número inteiro do usuário, repetindo a solicitação
     * caso a entrada seja inválida.
     * @param option Scanner usado para ler a entrada do usuário
     * @return o número inteiro digitado pelo usuário
     */
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
    /**
     * Lê um número decimal do usuário, repetindo a solicitação
     * caso a entrada seja inválida.
     * @param option Scanner usado para ler a entrada do usuário
     * @return o número decimal digitado pelo usuário
     */
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
}
