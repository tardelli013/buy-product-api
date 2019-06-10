package br.com.tardelli.buyproduct.service;

import br.com.tardelli.buyproduct.consumer.CentralBankBrazilClient;
import br.com.tardelli.buyproduct.model.SelicRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelicRateService {

    private static final String DEFAULT_FORMAT = "json";

    private CentralBankBrazilClient centralBankBrazilClient;

    @Autowired
    public SelicRateService(CentralBankBrazilClient centralBankBrazilClient) {
        this.centralBankBrazilClient = centralBankBrazilClient;
    }

    Double getSelicRateAccumulatedByDays(Integer days) {
        List<SelicRate> selicRatesByLastDays = centralBankBrazilClient.findSelicRatesByLastDays(days, DEFAULT_FORMAT);
        DoubleSummaryStatistics summaryStatistics = selicRatesByLastDays.stream().collect(Collectors.summarizingDouble(SelicRate::getValor));

        return summaryStatistics.getSum();
    }
}
