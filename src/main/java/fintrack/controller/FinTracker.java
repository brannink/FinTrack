package fintrack.controller;

import java.sql.SQLException;
import java.util.List;


import fintrack.dao.TransacaoDao;
import fintrack.model.Transacao;

/**
 * Gerencia a coleção de transações financeiras do usuário,
 * permitindo adicionar, remover, listar e calcular saldo.
 */
public class FinTracker {
    private TransacaoDao dao;

    public FinTracker() throws SQLException {
        this.dao = new TransacaoDao();
    }
    /**
     * Adiciona uma transação já construída (ex: TransacaoMensal) ao tracker.
     * @param t transação a ser adicionada
     * @return true se a transação foi adicionada com sucesso
     * @throws SQLException caso contrário ocorra erro ao salvar 
     */
    public boolean adicionarTransacao(Transacao t) throws SQLException {
            dao.salvar(t);
            return true;
    }
    /**
     * Exibe no console todas as transações cadastradas.
     * Caso não haja nenhuma, informa que a lista está vazia.
     * @return uma lista de objetos do tipo Transacao
     * @throws SQLException caso ocorra algum erro no método
     */
    public List<Transacao> listarTransacao() throws SQLException{
        return dao.buscarTodos();
    }
    /**
     * Remove a transação com o id informado, se existir.
     * @param id id da transação a ser removida
     * @return true se a transação foi encontrada e removida
     * @throws SQLException caso ocorra erro ao remover
     */
    public boolean removerTransacao(int id) throws SQLException{
        dao.remover(id);
        return true;
    }
    /**
     * Calcula o saldo total somando receitas e subtraindo despesas.
     * @return o saldo atual, podendo ser negativo
     * @throws SQLException caso ocorra algum erro
     */
    public double calcularSaldoTotal() throws SQLException{
        return dao.buscarSaldo();
    }
}