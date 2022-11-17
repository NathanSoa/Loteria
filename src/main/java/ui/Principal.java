package ui;

import dominio.Loteria;
import dominio.Participante;
import dominio.SorteadorSimples;
import dominio.LoteriaSimples;

import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {
        Loteria loteria = new LoteriaSimples(new SorteadorSimples());
        Participante p = new Participante("Nathan", LocalDate.of(2000, 10, 10), "123.456.789-10", "nathan@gmail.com");
        Participante p2 = new Participante("Nathan 2", LocalDate.of(2001, 10, 10), "132476.789-10", "nathan2@gmail.com");
        Participante p3 = new Participante("Nathan 3", LocalDate.of(2002, 10, 10), "123 123 789 10", "nathan34@fatec.com.br");
        Participante p4 = new Participante("Nathan 4", LocalDate.of(1990, 10, 10), "12345948910", "nathan43@gmail.com.br");
        loteria
                .addParticipante(p)
                .addParticipante(p2)
                .addParticipante(p3)
                .addParticipante(p4)
                .addJogo(p)
                .addJogo(p)
                .addJogo(p)
                .addJogo(p2)
                .addJogo(p2)
                .addJogo(p3)
                .addJogo(p4);

        System.out.printf("Tivemos %d vencedores nessa loteria.", loteria.realizar().size());
    }
}
