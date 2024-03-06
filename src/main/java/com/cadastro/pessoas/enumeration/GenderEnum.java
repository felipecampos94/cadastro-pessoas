package com.cadastro.pessoas.enumeration;

/**
 *
 * @author Felipe
 */
public enum GenderEnum {
    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private final String value;

    private GenderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
