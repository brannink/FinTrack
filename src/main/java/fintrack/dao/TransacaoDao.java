package fintrack.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;


import fintrack.db.Conexao;
import fintrack.exception.ValorInvalidoException;
import fintrack.model.Transacao;
import fintrack.model.TransacaoMensal;


/**
 * Classe que contém todos os metodos de interface com o banco
 * TransacaoDao
 */
public class TransacaoDao {
    public TransacaoDao() {
        criarTabela();
    }


    private void criarTabela() {
        Connection conexao = null;
        try {
            conexao = Conexao.conectar();
            String sql = """
                CREATE TABLE IF NOT EXISTS transacoes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    descricao VARCHAR(100),
                    valor DECIMAL(10,2),
                    tipo VARCHAR(20),
                    data DATE
                );
                """;
            conexao.createStatement().execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        } finally {
            try {
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Garante que a transação será adicionada ao banco
     * @param t uma transação qualquer
     * @throws SQLException caso ocorra erro ao consultar o banco
     */
    public void salvar(Transacao t) throws SQLException {
        Connection conexao = null;
        try {
            conexao = Conexao.conectar();
            conexao.setAutoCommit(false);

            String sql = "INSERT INTO transacoes (descricao, valor, tipo, data) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, t.getDescricao());
            stmt.setDouble(2, t.getValor());
            stmt.setString(3, t.getTipo());
            stmt.setDate(4, Date.valueOf(t.getData()));

            stmt.executeUpdate();
            conexao.commit();
        } catch(SQLException e) {
            if(conexao != null) {
                conexao.rollback();
                System.err.println("Erro ao salvar" + e.getMessage());
            }
        } finally {
            if(conexao != null) {
                conexao.close();
            }
        }
    }
    /**
     * Método responsável por trazer uma lista contendo
     * todas as transações salvas no banco
     * @return uma lista de transações
     * @throws SQLException caso ocorra erro ao consultar o banco
     */
    public List<Transacao> buscarTodos() throws SQLException {
        Connection conexao = null;
        List<Transacao> lista = new ArrayList<>();
        try {
            conexao = Conexao.conectar();

            String sql = "SELECT * FROM transacoes";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String tipo = rs.getString("tipo");
                if(tipo.equals("Receita mensal") || tipo.equals("Despesa mensal")) {
                    TransacaoMensal tm = new TransacaoMensal(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        tipo,
                        rs.getDate("data").toLocalDate()
                    );
                    lista.add(tm);
                } else {
                    Transacao t = new Transacao(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        tipo,
                        rs.getDate("data").toLocalDate()
                    );
                    lista.add(t);
                }
            }
        } catch (SQLException | ValorInvalidoException e) {
            System.err.println("Erro ao buscar" + e.getMessage());
        } finally {
            if(conexao != null) {
                conexao.close();
            }
        }
        return lista;
    }
    /**
     * Remove a transação a partir do
     * id informado
     * @param id id da transação a ser removida 
     * @throws SQLException caso ocorra erro ao consultar o banco
     */
    public void remover(int id) throws SQLException{
        Connection conexao = null;
        try {
            conexao = Conexao.conectar();
            conexao.setAutoCommit(false);

            String sql = "DELETE FROM transacoes WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            conexao.commit();
        } catch(SQLException e) {
            if(conexao != null) {
                conexao.rollback();
                System.err.println("Erro ao deletar" + e.getMessage());
            }
        } finally {
            if(conexao != null) {
                conexao.close();
            }
        }
    }
    /**
     * Devolve a soma de todas as transações
     * contidas no banco
     * @return saldo total
     * @throws SQLException caso ocorra erro ao consultar o banco
     */
    public double buscarSaldo() throws SQLException{
        Connection conexao = null;
        double saldo = 0.0;
        try {
            conexao = Conexao.conectar();

            String sql = """
                SELECT SUM (
                CASE
                    WHEN tipo IN ('Receita', 'Receita mensal') THEN valor
                    WHEN tipo IN ('Despesa', 'Despesa mensal') THEN -valor
                    ELSE 0
                END
                ) FROM transacoes
                 """;
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                saldo = rs.getDouble(1);
            } 
        } catch(SQLException e) {
            if(conexao != null) {
                System.err.println("Erro ao buscar saldo" + e.getMessage());
            }
        } finally {
            if(conexao != null) {
                conexao.close();
            }
        }
        return saldo;
    }
}
