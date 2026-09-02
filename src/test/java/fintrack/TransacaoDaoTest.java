package fintrack;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


import fintrack.dao.TransacaoDao;
import fintrack.db.Conexao;
import fintrack.exception.ValorInvalidoException;
import fintrack.model.Transacao;


/**
 * Classe responsável por executar os testes
 * dos métodos de TransacaoDao
 * TransacaoDaoTest
 */
public class TransacaoDaoTest {
    private TransacaoDao dao = new TransacaoDao();
    /**
     * Garante que depois de cada teste inserido no banco
     * eles sejam devidamente removidos
     * @throws SQLException caso ocorra erro ao consultar o banco
     */
    @AfterEach
    void rollback() throws SQLException {
        Connection conexao = Conexao.conectar();
        conexao.createStatement().execute("DELETE FROM transacoes WHERE descricao = 'teste'");
        conexao.close();
    }
    /**
     * Responsável por verificar se está sendo possível
     * guardar transações no banco
     * @throws SQLException caso ocorra erro ao consultar o banco
     * @throws ValorInvalidoException caso haja inserção de valor inválido
     */
    @Test
    void testSalvar() throws SQLException, ValorInvalidoException {
        Transacao t = new Transacao(0, "teste", 100, "Receita", LocalDate.now());
        dao.salvar(t);
        List<Transacao> lista = dao.buscarTodos();
        assertTrue(lista.stream().anyMatch(tr -> tr.getDescricao().equals("teste")));
    }
    /**
     * Tem como objetivo verificar se as transações
     * estão sendo devidamente removidas
     * @throws SQLException caso ocorra erro ao consultar o banco
     * @throws ValorInvalidoException caso haja inserção de valor inválido
     */
    @Test
    void testRemover() throws SQLException, ValorInvalidoException {
        Transacao t = new Transacao(0, "teste", 100, "Receita", LocalDate.now());
        dao.salvar(t);
        List<Transacao> lista = dao.buscarTodos();
        int id = lista.stream().filter(tr -> tr.getDescricao().equals("teste")).findFirst().get().getId();
        dao.remover(id);
        List<Transacao> listaNova = dao.buscarTodos();
        assertFalse(listaNova.stream().anyMatch(tr -> tr.getDescricao().equals("teste")));
    }
    /**
     * Assegura que o retorno do saldo do usuário
     * está com o devido retorno
     * @throws SQLException caso ocorra erro ao consultar o banco
     * @throws ValorInvalidoException caso haja inserção de valor inválido
     */
    @Test
    void buscarSaldo() throws SQLException, ValorInvalidoException {
        Transacao t = new Transacao(0, "teste", 100, "Receita", LocalDate.now());
        dao.salvar(t);
        double saldo = dao.buscarSaldo();
        assertTrue(saldo >= 100.00);
    }
}
