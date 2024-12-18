package ru.job4j.ood.srp.requirement;

public abstract class AbstractRequirement implements Requirement {
    private final String[] headers;

    public AbstractRequirement(String[] headers) {
        this.headers = headers;
    }

    public String[] getHeaders() {
        return headers;
    }
}
