package br.com.tardelli.buyproduct.dto;

        import lombok.Data;

@Data
public class InstallmentDTO {

    private Integer numeroParcela;
    private Double valor;
    private Double taxaJurosAoMes;

}
