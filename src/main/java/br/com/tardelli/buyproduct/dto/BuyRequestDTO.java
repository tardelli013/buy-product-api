package br.com.tardelli.buyproduct.dto;

import lombok.Data;

@Data
public class BuyRequestDTO {

    private ProductDTO produto;
    private PaymentConditionDTO condicaoPagamento;
    
}
