package fintrack.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import fintrack.model.Transacao;

public class FinTracker {
    ArrayList<Transacao> bucket = new ArrayList<>();
    private int proximoId = 1;

    public int getProximoId(){
        return this.proximoId;
    }

    public boolean adicionarTransacao(String descricao, double valor, boolean ehReceita, LocalDate data){
        try {
            bucket.add(new Transacao(proximoId, descricao, valor, ehReceita, data));
            proximoId++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean adicionarTransacao(Transacao t) {
    try {
        bucket.add(t);
        proximoId++;
        return true;
    } catch (Exception e) {
        return false;
    }
}

    public void listarTransacao(){
        if(bucket.isEmpty()){
            System.out.println("Nenhuma transação cadastrada.");
        }
        for(Transacao t : bucket){
            System.out.println(t);
        }    
    }

    public boolean removerTransacao(int id){
        for(Transacao t : bucket){
            if(t.getId() == id){
                bucket.remove(t);
                return true;
            }
        }
        return false;
    }

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