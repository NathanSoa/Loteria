package dominio.valueObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email extends AbstractStringValueObject {

    public Email(String value) {
        super(value);
    }

    @Override
    protected void validar(String value) {
        Pattern pattern = Pattern.compile("^[a-zA-Z](\\w|.)+@[a-zA-Z]+\\.com(\\.\\w+)?");
        Matcher matcher = pattern.matcher(value);
        if(!matcher.matches())
            throw new IllegalArgumentException("O email deve ter um formato válido!");
    }
}
