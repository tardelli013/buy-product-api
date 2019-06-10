package br.com.tardelli.buyproduct.factory;

import br.com.tardelli.buyproduct.dto.BuyRequestDTO;
import br.com.tardelli.buyproduct.dto.InstallmentDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class InstallmentFactory {

    public static List<InstallmentDTO> build(Double selicRateAccumulated, BuyRequestDTO buyRequest) {

        Double valueForParcel = calcValueForParcel(buyRequest);

        return buildParcels(selicRateAccumulated, buyRequest, valueForParcel);
    }

    private static List<InstallmentDTO> buildParcels(Double selicRateAccumulated, BuyRequestDTO buyRequest, Double valueForParcel) {
        List<InstallmentDTO> installmentDTOS = new ArrayList<>();

        for (int i = 1; i <= buyRequest.getCondicaoPagamento().getQtdeParcelas(); i++) {
            InstallmentDTO dto = new InstallmentDTO();
            dto.setNumeroParcela(i);
            dto.setTaxaJurosAoMes(selicRateAccumulated);

            Double additionValue = calcPercentage(selicRateAccumulated, valueForParcel);
            dto.setValor((new BigDecimal(additionValue + valueForParcel).setScale(2, RoundingMode.HALF_UP).doubleValue()));

            installmentDTOS.add(dto);
        }
        return installmentDTOS;
    }

    public static Double calcValueForParcel(BuyRequestDTO buyRequest) {
        Double productBalanceDue = buyRequest.getProduto().getValor() - buyRequest.getCondicaoPagamento().getValorEntrada();
        return productBalanceDue / buyRequest.getCondicaoPagamento().getQtdeParcelas();
    }

    public static Double calcPercentage(Double n, Double total) {
        double proportion = (n) / (100);
        return proportion * total;
    }
}
