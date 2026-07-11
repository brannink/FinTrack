package fintrack.model;

import java.time.LocalDate;

public class Transacao {
    private int id;
    private String descricao;
    private double valor;
    private boolean ehReceita;
    private LocalDate data;

    public Transacao(int id, String descricao, double valor, boolean ehReceita, LocalDate data){
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.ehReceita = ehReceita;
        this.data = data;
    }

    public int getId(){
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public double getValor(){
        return this.valor;
    }
    public void setValor(double valor){
        this.valor = valor;
    }

    public boolean getEhReceita(){
        return this.ehReceita;
    }
    public void setEhReceita(boolean ehReceita){
        this.ehReceita = ehReceita;
    }

    public LocalDate getData(){
        return this.data;
    }
    public void setData(LocalDate data){
        this.data = data;
    }
    
    @Override
    public String toString(){
        String tipo = ehReceita ? "Receita" : "Despesa";
        return String.format("[%d]\n | %s -- R$ %.2f |\n %s\n %s\n", id, tipo, valor, data, descricao);
    }
}