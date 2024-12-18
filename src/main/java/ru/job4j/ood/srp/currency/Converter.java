package ru.job4j.ood.srp.currency;

public class Converter {
    private final Currency source;
    private final Currency target;

    public Converter(Currency source, Currency target) {
        this.source = source;
        this.target = target;
    }

    public Currency getSource() {
        return source;
    }

    public Currency getTarget() {
        return target;
    }
}
