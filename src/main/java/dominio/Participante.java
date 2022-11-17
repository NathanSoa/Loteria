package dominio;

import dominio.valueObjects.CPF;
import dominio.valueObjects.Email;

import java.time.LocalDate;

public class Participante {

    private final String nome;
    private final LocalDate dataNascimento;
    private final CPF cpf;
    private final Email email;

    public Participante(String nome, LocalDate dataNascimento, String cpf, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = new CPF(cpf);
        this.email = new Email(email);
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public CPF getCpf() {
        return cpf;
    }

    public Email getEmail() {
        return email;
    }
}
