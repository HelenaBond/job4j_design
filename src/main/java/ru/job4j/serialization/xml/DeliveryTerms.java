package ru.job4j.serialization.xml;

public class DeliveryTerms {
    private boolean isFreezing;
    private boolean dryEnvironment;

    public DeliveryTerms(boolean isFreezing, boolean dryEnvironment) {
        this.isFreezing = isFreezing;
        this.dryEnvironment = dryEnvironment;
    }

    @Override
    public String toString() {
        return "DeliveryTerms{"
                + "isFreezing=" + isFreezing
                + ", dryEnvironment=" + dryEnvironment + '}';
    }
}
