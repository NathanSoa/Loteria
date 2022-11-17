package dominio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LoteriaSimples implements Loteria {

    private static final double VALOR_POR_JOGO = 6;

    private final Set<Participante> participantes;
    private final Map<Participante, List<Jogo>> jogosPorParticipante;
    private final Sorteador sorteador;

    public LoteriaSimples(Sorteador sorteador) {
        this.participantes = new HashSet<>();
        this.jogosPorParticipante = new HashMap<>();
        this.sorteador = sorteador;
    }

    @Override
    public Loteria addParticipante(Participante participante) {
        if(EUmParticipanteNovo(participante)) {
            adicionaParticipanteNoMapaComNovoJogo(participante);
        }

        participantes.add(participante);
        return this;
    }

    private boolean EUmParticipanteNovo(Participante participante) {
        return !participantes.contains(participante);
    }

    private void adicionaParticipanteNoMapaComNovoJogo(Participante participante) {
        if(participanteNaoEMaiorDeIdade(participante)){
            throw new RuntimeException("Participante deve ser maior de idade para apostar!");
        }
        List<Jogo> jogos = new ArrayList<>();
        adicionaNovoJogoAoParticipante(participante, jogos);
        jogosPorParticipante.put(participante, jogos);
    }

    private boolean participanteNaoEMaiorDeIdade(Participante participante){
        long idadeParticipante = ChronoUnit.YEARS.between(participante.getDataNascimento(), LocalDate.now());
        return !(idadeParticipante > 18);
    }

    @Override
    public Loteria addJogo(Participante participante) {
        if(!participantes.contains(participante)) {
            throw new IllegalArgumentException("Participante não está apostando nessa loteria!");
        }

        List<Jogo> jogos = jogosPorParticipante.get(participante);
        adicionaNovoJogoAoParticipante(participante, jogos);
        return this;
    }

    private void adicionaNovoJogoAoParticipante(Participante participante, List<Jogo> jogos){
        Jogo jogo = new Jogo(60);
        List<Integer> numerosMarcados = sorteador.sortear(60);
        jogo.marcarNumero(numerosMarcados.subList(0, 5));
        jogos.add(jogo);
    }

    @Override
    public BigDecimal calcularPremio() {
        double quantidadeDeJogos = 0;
        for(Participante participante : participantes) {
            for(Jogo jogo : jogosPorParticipante.get(participante)) {
                quantidadeDeJogos++;
            }
        }
        return BigDecimal.valueOf(VALOR_POR_JOGO * quantidadeDeJogos);
    }

    @Override
    public List<Participante> realizar() {
        List<Participante> vencedores = new ArrayList<>();
        List<Integer> jogoVencedor = sorteador.sortear(60);

        for(Participante participante : participantes){
            for(Jogo jogo : jogosPorParticipante.get(participante)) {
                int acertos = 0;

                for(int i = 0; i < 6; i++)
                    if(jogo.numeroFoiMarcado(jogoVencedor.get(i)))
                        acertos++;

                if(acertos == 6){
                    vencedores.add(participante);
                    break;
                }
            }
        }
        return vencedores;
    }
}
