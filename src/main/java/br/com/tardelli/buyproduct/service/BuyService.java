package br.com.tardelli.buyproduct.service;

import br.com.tardelli.buyproduct.dto.BuyRequestDTO;
import br.com.tardelli.buyproduct.dto.InstallmentDTO;
import br.com.tardelli.buyproduct.factory.InstallmentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyService {

    private static final Integer SELIC_RATE_ACCUMULATED_DAYS = 30;

    private SelicRateService selicRateService;

    @Autowired
    public BuyService(SelicRateService selicRateService) {
        this.selicRateService = selicRateService;
    }

    public List<InstallmentDTO> buy(BuyRequestDTO buyRequest) {
        Double selicRateAccumulated = getSelicRateAccumulated(buyRequest.getCondicaoPagamento().getQtdeParcelas());

        return InstallmentFactory.build(selicRateAccumulated, buyRequest);
    }

    public Double getSelicRateAccumulated(Integer numberOfParcels) {
        if (numberOfParcels > 6) {
            return selicRateService.getSelicRateAccumulatedByDays(SELIC_RATE_ACCUMULATED_DAYS);
        }
        return 0.0;
    }
}
