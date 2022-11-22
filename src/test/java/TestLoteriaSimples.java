import dominio.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLoteriaSimples {

    @Test
    public void deve_LancarUmaExcecao_quando_CadastrarRepetidasVezesOMesmoParticipante() {
        Sorteador sorteador = new SorteadorSimples();
        Loteria loteria = new LoteriaSimples(sorteador);
        Participante p = new Participante("Nathan", LocalDate.of(2000, 10, 10), "123.456.789-10", "nathan@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            loteria.addParticipante(p)
                .addParticipante(p);
        });
    }

    @Test
    public void deve_LancarUmaExcecao_quando_AdicionarNovoJogoAUmParticipanteQueNaoEstaCadastradoNaLoteria() {
        Sorteador sorteador = new SorteadorSimples();
        Loteria loteria = new LoteriaSimples(sorteador);
        Participante p = new Participante("Nathan", LocalDate.of(2000, 10, 10), "123.456.789-10", "nathan@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            loteria.addJogo(p);
        });
    }

    @Test
    public void deve_LancarUmaExcecao_quando_AdicionarUmParticipanteSemIdadeParaApostar() {
        Sorteador sorteador = new SorteadorSimples();
        Loteria loteria = new LoteriaSimples(sorteador);
        Participante p = new Participante("Nathan", LocalDate.now(Clock.systemDefaultZone()), "123.456.789-10", "nathan@gmail.com");

        assertThrows(RuntimeException.class, () -> {
            loteria.addParticipante(p);
        });
    }

    @Test
    public void deve_CalcularCorretamenteOPremio_quando_InvocadoMetodo() {
        Sorteador sorteador = new SorteadorSimples();
        Loteria loteria = new LoteriaSimples(sorteador);
        Participante p = new Participante("Nathan", LocalDate.of(2000, 10, 10), "123.456.789-10", "nathan@gmail.com");
        Participante p2 = new Participante("Nathan", LocalDate.of(1999, 10, 10), "132.455.789-10", "nathan2@gmail.com");

        loteria.addParticipante(p)
            .addParticipante(p2);

        assertEquals(loteria.calcularPremio(), BigDecimal.valueOf(12.0));
    }
}
