package dominio.valueObjects;

import java.util.Objects;

public abstract class AbstractStringValueObject {

    private final String value;

    protected abstract void validar(String value);

    public AbstractStringValueObject(String value) {
        Objects.requireNonNull(value);
        validar(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
