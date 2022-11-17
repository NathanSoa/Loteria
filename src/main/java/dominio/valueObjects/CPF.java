package dominio.valueObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPF extends AbstractStringValueObject {

    public CPF(String value) {
        super(value);
    }

    @Override
    protected void validar(String value) {
        Pattern pattern = Pattern.compile("([0-9]{3}\\.?\\ ?){3}\\-?\\ ?[0-9]{2}");
        Matcher matcher = pattern.matcher(value);
        if(!matcher.matches())
            throw new IllegalArgumentException("O cpf deve ter um formato válido!");
    }
}
