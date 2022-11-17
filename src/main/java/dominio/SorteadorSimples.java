package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SorteadorSimples implements Sorteador {

    public SorteadorSimples() {

    }

    @Override
    public List<Integer> sortear(int quantidadeNumeros) {
        List<Integer> numerosSorteados = new ArrayList<>();

        for(int i = 0; i < quantidadeNumeros; i++){
            numerosSorteados.add(i);
        }

        Collections.shuffle(numerosSorteados);

        return numerosSorteados;
    }
}
