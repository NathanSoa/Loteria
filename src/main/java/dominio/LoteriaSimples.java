package dominio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class LoteriaSimples implements Loteria {

    private static final double VALOR_POR_JOGO = 6;

    private final Map<Participante, List<Jogo>> participantes;
    private final Sorteador sorteador;

    public LoteriaSimples(Sorteador sorteador) {
        this.participantes = new HashMap<>();
        this.sorteador = sorteador;
    }

    @Override
    public Loteria addParticipante(Participante participante) {
        if(NaoEUmParticipanteNovo(participante)) {
            throw new RuntimeException("Participante já está cadastrado nesta aposta!");
        }

        adicionaParticipanteNoMapaComNovoJogo(participante);
        return this;
    }

    private boolean NaoEUmParticipanteNovo(Participante participante) {
        return participantes.get(participante) != null;
    }

    private void adicionaParticipanteNoMapaComNovoJogo(Participante participante) {
        if(participanteNaoEMaiorDeIdade(participante)){
            throw new RuntimeException("Participante deve ser maior de idade para apostar!");
        }
        List<Jogo> jogos = new ArrayList<>();
        adicionaNovoJogoAoParticipante(jogos);
        participantes.put(participante, jogos);
    }

    private boolean participanteNaoEMaiorDeIdade(Participante participante){
        long idadeParticipante = ChronoUnit.YEARS.between(participante.getDataNascimento(), LocalDate.now());
        return !(idadeParticipante > 18);
    }

    private void adicionaNovoJogoAoParticipante(List<Jogo> jogos){
        Jogo jogo = new Jogo(60);
        List<Integer> numerosMarcados = sorteador.sortear(60);
        jogo.marcarNumero(numerosMarcados.subList(0, 5));
        jogos.add(jogo);
    }

    @Override
    public Loteria addJogo(Participante participante) {
        if(!participantes.containsKey(participante)) {
            throw new IllegalArgumentException("Participante não está apostando nessa loteria!");
        }

        List<Jogo> jogos = participantes.get(participante);
        adicionaNovoJogoAoParticipante(jogos);
        return this;
    }

    @Override
    public BigDecimal calcularPremio() {
        int quantidadeDeJogos = 0;
        for(Participante participante : participantes.keySet()) {
            for(Jogo jogo : participantes.get(participante)) {
                quantidadeDeJogos++;
            }
        }
        return BigDecimal.valueOf(VALOR_POR_JOGO * quantidadeDeJogos);
    }

    @Override
    public List<Participante> realizar() {
        List<Participante> vencedores = new ArrayList<>();
        List<Integer> jogoVencedor = sorteador.sortear(60);

        for(Participante participante : participantes.keySet()){
            for(Jogo jogo : participantes.get(participante)) {
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
