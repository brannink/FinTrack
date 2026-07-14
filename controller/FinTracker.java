package fintrack.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import fintrack.model.Transacao;

/**
 * Gerencia a coleção de transações financeiras do usuário,
 * permitindo adicionar, remover, listar e calcular saldo.
 */
public class FinTracker {
    ArrayList<Transacao> bucket = new ArrayList<>();
    private int proximoId = 1;
    /**
    * Verifica se não há nenhuma transação cadastrada.
    * @return true se não houver transações, false caso contrário
    */
    public boolean isVazio(){
        return bucket.isEmpty();
    }
    /**
     * Retorna o próximo id disponível para uma nova transação.
     * @return o próximo id a ser utilizado
     */
    public int getProximoId(){
        return this.proximoId;
    }
    /**
     * Cria e adiciona uma nova transação ao tracker, usando o próximo
     * id disponível automaticamente.
     * @param descricao descrição da transação
     * @param valor valor da transação
     * @param ehReceita true se for receita, false se for despesa
     * @param data data em que a transação ocorreu
     * @return true se a transação foi adicionada com sucesso, false caso contrário
     */
    public boolean adicionarTransacao(String descricao, double valor, boolean ehReceita, LocalDate data){
        try {
            bucket.add(new Transacao(proximoId, descricao, valor, ehReceita, data));
            proximoId++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Adiciona uma transação já construída (ex: TransacaoMensal) ao tracker.
     * @param t transação a ser adicionada
     * @return true se a transação foi adicionada com sucesso, false caso contrário
     */
    public boolean adicionarTransacao(Transacao t) {
        try {
            bucket.add(t);
            proximoId++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Exibe no console todas as transações cadastradas.
     * Caso não haja nenhuma, informa que a lista está vazia.
     */
    public void listarTransacao(){
        if(bucket.isEmpty()){
            System.out.println("Nenhuma transação cadastrada.");
        }
        for(Transacao t : bucket){
            System.out.println(t);
        }    
    }
    /**
     * Remove a transação com o id informado, se existir.
     * @param id id da transação a ser removida
     * @return true se a transação foi encontrada e removida, false caso contrário
     */
    public boolean removerTransacao(int id){
        for(Transacao t : bucket){
            if(t.getId() == id){
                bucket.remove(t);
                return true;
            }
        }
        return false;
    }
    /**
     * Calcula o saldo total somando receitas e subtraindo despesas.
     * @return o saldo atual, podendo ser negativo
     */
    public double calcularSaldoTotal(){
        double saldo = 0;
        for(Transacao t : bucket){
            if(t.getEhReceita()){
                saldo += t.getValor();
            }else{
                saldo -= t.getValor();
            }
        }
        return saldo;
    }
}