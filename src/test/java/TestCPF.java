
import dominio.valueObjects.CPF;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestCPF {

    @Test
    public void deve_LancarUmaExcecao_quando_CriarUmCPFComFormatoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            CPF cpf = new CPF("234142142141");
        });
    }

    @Test
    public void deve_LancarUmaExcecao_quando_CriarUmCPFComValorNulo() {
        assertThrows(NullPointerException.class, () -> {
            CPF cpf = new CPF(null);
        });
    }

    @Test
    public void deve_CriarUmNovoObjeto_quando_CriarUmCPFComFormatoBasico(){
        String valorBase = "123.456.789-10";

        CPF cpf = new CPF(valorBase);

        assertEquals(cpf.getValue(), valorBase);
    }

    @Test
    public void deve_CriarUmNovoObjeto_quando_CriarUmCPFComFormatoValidoSemPontuacao(){
        String valorBase = "12345678910";

        CPF cpf = new CPF(valorBase);

        assertEquals(cpf.getValue(), valorBase);
    }

    @Test
    public void deve_CriarUmNovoObjeto_quando_CriarUmCPFComFormatoValidoComEspacos(){
        String valorBase = "123 456 789 10";

        CPF cpf = new CPF(valorBase);

        assertEquals(cpf.getValue(), valorBase);
    }
}
