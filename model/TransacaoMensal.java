package fintrack.model;

import java.time.LocalDate;

/**
 * Representa uma transação financeira que se repete mensalmente
 * (ex: salário, aluguel, assinatura), guardando o dia do mês em
 * que ela ocorre.
 */
public class TransacaoMensal extends Transacao{
    private int dia;
    /**
     * Cria uma nova transação mensal.
     * @param id identificador único da transação
     * @param descricao descrição da transação
     * @param valor valor da transação
     * @param ehReceita true se for receita, false se for despesa
     * @param data data de criação/referência da transação
     * @param dia dia do mês em que a transação se repete
     */
    public TransacaoMensal(int id, String descricao, double valor, boolean ehReceita, LocalDate data, int dia){
        super(id, descricao, valor, ehReceita, data);
        this.dia = dia;
    }
    /**
     * Retorna o dia do mês em que a transação se repete.
     * @return o dia do mês
     */
    public int getDia(){
        return dia;
    }
    /**
     * Define o dia do mês em que a transação se repete.
     * @param dia novo dia do mês
     */
    public void setDia(int dia){
        this.dia = dia;
    }
    /**
     * Retorna a representação textual da transação, incluindo
     * a indicação de que é recorrente e o dia do mês.
     */
    @Override
    public String toString(){
        return super.toString() + String.format("Recorrente, dia %d\n", this.dia);
    }
}