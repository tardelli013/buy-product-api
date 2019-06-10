package br.com.tardelli.buyproduct.service;

import br.com.tardelli.buyproduct.consumer.CentralBankBrazilClient;
import br.com.tardelli.buyproduct.model.SelicRate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SelicRateServiceTest {

    @Autowired
    private SelicRateService selicRateService;

    @MockBean
    private CentralBankBrazilClient centralBankBrazilClient;

    @Test
    public void getSelicRateAccumulatedByDays() {
        when(centralBankBrazilClient.findSelicRatesByLastDays(any(), anyString())).thenReturn(getRatesMock(30));
        Double accumulated = selicRateService.getSelicRateAccumulatedByDays(30);
        Assert.assertEquals(accumulated, new Double("6.0"));
    }

    public static List<SelicRate> getRatesMock(int days) {
        List<SelicRate> rates = new ArrayList<>();

        for (int i = 0; i < days; i++) {
            SelicRate selicRate = new SelicRate();
            selicRate.setData(i + "/01/2019");
            selicRate.setValor("0.20");

            rates.add(selicRate);
        }

        return rates;
    }
}
