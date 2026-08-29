package fintrack.model;

import java.time.LocalDate;


import fintrack.exception.ValorInvalidoException;


/**
 * Representa uma transação financeira que se repete mensalmente
 * (ex: salário, aluguel, assinatura), guardando o dia do mês em
 * que ela ocorre.
 */
public class TransacaoMensal extends Transacao{
    /**
     * Cria uma nova transação mensal.
     * @param id identificador único da transação
     * @param descricao descrição da transação
     * @param valor valor da transação
     * @param tipo classifica o tipo de transação
     * @param data data de criação/referência da transação
     */
    public TransacaoMensal(int id, String descricao, double valor, String tipo, LocalDate data) throws ValorInvalidoException {
        super(id, descricao, valor, tipo, data);
    }
    /**
     * Responsavel por consultar a data da transação
     * @return o dia do mês da transação mensal
     */
    public int getDia(){
        return getData().getDayOfMonth();
    }
}