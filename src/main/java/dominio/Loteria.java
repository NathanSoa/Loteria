package dominio;

import java.math.BigDecimal;
import java.util.List;

public interface Loteria {

    Loteria addParticipante(Participante participante);
    Loteria addJogo(Participante participante);
    BigDecimal calcularPremio();
    List<Participante> realizar();
}
