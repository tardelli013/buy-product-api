package br.com.tardelli.buyproduct.rest;

import br.com.tardelli.buyproduct.consumer.CentralBankBrazilClient;
import br.com.tardelli.buyproduct.model.SelicRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestServiceRest {

    @Autowired
    private CentralBankBrazilClient centralBankBrazilClient;

    @GetMapping("/people")
    public List<SelicRate> listPeople() {

        List<SelicRate> selicRates = centralBankBrazilClient.findSelicRates("json", "01/01/2010", "30/01/2010");
        List<SelicRate> selicRatesByLastDays = centralBankBrazilClient.findSelicRatesByLastDays(3, "json");

        return selicRatesByLastDays;
    }

}
