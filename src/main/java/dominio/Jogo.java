package dominio;

import java.util.Arrays;
import java.util.List;

public class Jogo {

    private final Boolean[] volante;

    public Jogo(int quantidadeNumerosPossiveis) {
        this.volante = new Boolean[quantidadeNumerosPossiveis];
        Arrays.fill(volante, false);
    }

    public boolean numeroFoiMarcado(int posicao){
        return volante[posicao];
    }

    public void marcarNumero(List<Integer> numeros){
        numeros
            .forEach(cadaNumero -> {
                volante[cadaNumero] = true;
            });
    }
}
