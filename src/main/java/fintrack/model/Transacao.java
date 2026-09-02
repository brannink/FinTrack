package fintrack.model;

import java.time.LocalDate;
import java.util.Locale;
import java.text.NumberFormat;


import fintrack.exception.ValorInvalidoException;


/**
 * Representa uma transação financeira simples (receita ou despesa),
 * contendo id, descrição, valor, tipo e data.
 */
public class Transacao {
    private int id;
    private String descricao;
    private double valor;
    private String tipo;
    private LocalDate data;

    /**
     * Cria uma nova transação.
     * @param id identificador único da transação
     * @param descricao descrição da transação
     * @param valor valor da transação
     * @param tipo descreve qual tipo a transação se classifica
     * @param data data em que a transação ocorreu
     */
    public Transacao(int id, String descricao, double valor, String tipo, LocalDate data) throws ValorInvalidoException {
        if (valor < 0) {
            throw new ValorInvalidoException("O valor da transação não pode ser negativo.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new ValorInvalidoException("A descrição não pode estar vazia.");
        } 
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
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
     * Retorna o valor da transação.
     * @return o valor da transação
     */
    public double getValor(){
        return this.valor;
    }
    /**
     * Retorna o valor da transação.
     * @return o valor da transação
     */
    public String getValorFormatado(){
        NumberFormat nf = NumberFormat.getInstance(Locale.of("pt", "BR"));
        return "R$ " + nf.format(this.valor);
    }
    /**
     * Indica se a transação é uma receita ou despesa.
     * @return o tipo de transação
     */
    public String getTipo(){
        return this.tipo;
    }
    /**
     * Retorna a data em que a transação ocorreu.
     * @return a data da transação
     */
    public LocalDate getData(){
        return this.data;
    }
}