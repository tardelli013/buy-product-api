package br.com.tardelli.buyproduct.model;

import lombok.Data;

@Data
public class SelicRate {

    private String data;
    private String valor;

    public Double getValor() {
        return new Double(valor);
    }

}
