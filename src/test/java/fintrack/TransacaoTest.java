package fintrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;


import fintrack.model.Transacao;
import fintrack.exception.ValorInvalidoException;;


/**
 * Classe responsável pelos testes de Transacao.java
 * através do jUnit
 * TransacaoTest
 */
public class TransacaoTest {
    /**
     * Teste responsável por validar
     * a criação de transações
     */
    @Test
    void testCriarTransacao() {
        assertDoesNotThrow(() -> {
            Transacao t = new Transacao(1, "salario", 1000.00, "Receita", LocalDate.now());
            assertEquals("salario", t.getDescricao());
            assertEquals(1000.00, t.getValor());
        });
    }
    /**
     * Teste cuja função é verificar se é 
     * possível ou não criar uma transação de
     * valor negativo
     */
    @Test
    void testValorNegativo() {
        assertThrows(ValorInvalidoException.class, () -> {
            new Transacao(1, "teste", -100.00, "Despesa", LocalDate.now());
        });        
    }
    /**
     * Teste com o objetivo de verificar se
     * é possível criar uma classe com descrição vazia
     */
    @Test
    void testDescricaoNull() {
        assertThrows(ValorInvalidoException.class, () -> {
            new Transacao(1, "", 100.00, "Receita", LocalDate.now());
        });
    }
}
