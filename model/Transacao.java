package fintrack.model;

import java.time.LocalDate;

/**
 * Representa uma transação financeira simples (receita ou despesa),
 * contendo id, descrição, valor, tipo e data.
 */
public class Transacao {
    private int id;
    private String descricao;
    private double valor;
    private boolean ehReceita;
    private LocalDate data;

    /**
     * Cria uma nova transação.
     * @param id identificador único da transação
     * @param descricao descrição da transação
     * @param valor valor da transação
     * @param ehReceita true se for receita, false se for despesa
     * @param data data em que a transação ocorreu
     */
    public Transacao(int id, String descricao, double valor, boolean ehReceita, LocalDate data){
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.ehReceita = ehReceita;
        this.data = data;
    }
    /**
     * Retorna o identificador único da transação.
     * @return o id da transação
     */
    public int getId(){
        return this.id;
    }
    /**
     * Retorna a descrição da transação.
     * @return a descrição da transação
     */
    public String getDescricao(){
        return this.descricao;
    }
    /**
     * Define a descrição da transação.
     * @param descricao nova descrição da transação
     */
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    /**
     * Retorna o valor da transação.
     * @return o valor da transação
     */
    public double getValor(){
        return this.valor;
    }
    /**
     * Define o valor da transação.
     * @param valor novo valor da transação
     */
    public void setValor(double valor){
        this.valor = valor;
    }
    /**
     * Indica se a transação é uma receita ou despesa.
     * @return true se for receita, false se for despesa
     */
    public boolean getEhReceita(){
        return this.ehReceita;
    }
    /**
     * Define se a transação é receita ou despesa.
     * @param ehReceita true para receita, false para despesa
     */
    public void setEhReceita(boolean ehReceita){
        this.ehReceita = ehReceita;
    }
    /**
     * Retorna a data em que a transação ocorreu.
     * @return a data da transação
     */
    public LocalDate getData(){
        return this.data;
    }
    /**
     * Define a data da transação.
     * @param data nova data da transação
     */
    public void setData(LocalDate data){
        this.data = data;
    }
    /**
     * Retorna a representação textual da transação, incluindo
     * id, tipo (receita/despesa), valor, data e descrição.
     */
    @Override
    public String toString(){
        String tipo = ehReceita ? "Receita" : "Despesa";
        return String.format("[%d]\n | %s -- R$ %.2f |\n %s\n %s\n", id, tipo, valor, data, descricao);
    }
}