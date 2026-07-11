package fintrack.model;

import java.time.LocalDate;

public class TransacaoMensal extends Transacao{
    private int dia;
    
    public TransacaoMensal(int id, String descricao, double valor, boolean ehReceita, LocalDate data, int dia){
        super(id, descricao, valor, ehReceita, data);
        this.dia = dia;
    }

    public int getDia(){
        return dia;
    }
    public void setDia(int dia){
        this.dia = dia;
    }
    
    @Override
    public String toString(){
        return super.toString() + String.format("Recorrente, dia %d\n", this.dia);
    }

}