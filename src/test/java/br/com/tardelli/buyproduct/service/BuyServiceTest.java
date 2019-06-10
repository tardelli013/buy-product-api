package br.com.tardelli.buyproduct.service;

import br.com.tardelli.buyproduct.consumer.CentralBankBrazilClient;
import br.com.tardelli.buyproduct.dto.BuyRequestDTO;
import br.com.tardelli.buyproduct.dto.InstallmentDTO;
import br.com.tardelli.buyproduct.dto.PaymentConditionDTO;
import br.com.tardelli.buyproduct.dto.ProductDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyServiceTest {

    @Autowired
    private BuyService buyService;

    @MockBean
    private CentralBankBrazilClient centralBankBrazilClient;

    @Test
    public void buy7ParcelsTest() {
        Integer parcels7 = 7;
        when(centralBankBrazilClient.findSelicRatesByLastDays(any(),any())).thenReturn(SelicRateServiceTest.getRatesMock(30));

        List<InstallmentDTO> buyIn7Parcels = buyService.buy(getMockBuyRequestDTO(parcels7));
        Assert.assertEquals(buyIn7Parcels.size(), 7);

        buyIn7Parcels.forEach(installmentDTO -> {
            Assert.assertEquals(installmentDTO.getValor(), new Double(106.0));
            Assert.assertEquals(installmentDTO.getTaxaJurosAoMes(), new Double(6.0));
        });
    }

    @Test
    public void buy6ParcelsTest() {
        Integer parcels6 = 6;

        List<InstallmentDTO> buyIn7Parcels = buyService.buy(getMockBuyRequestDTO(parcels6));
        Assert.assertEquals(buyIn7Parcels.size(), 6);

        buyIn7Parcels.forEach(installmentDTO -> {
            Assert.assertEquals(installmentDTO.getValor(), new Double(116.67));
            Assert.assertEquals(installmentDTO.getTaxaJurosAoMes(), new Double(0.0));
        });
    }

    private BuyRequestDTO getMockBuyRequestDTO(Integer numberOfParcels) {
        PaymentConditionDTO conditionDTO = new PaymentConditionDTO();
        conditionDTO.setQtdeParcelas(numberOfParcels);
        conditionDTO.setValorEntrada(100.0);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCodigo(1234);
        productDTO.setNome("Microondas");
        productDTO.setValor(800.0);

        BuyRequestDTO buyRequestDTO = new BuyRequestDTO();
        buyRequestDTO.setCondicaoPagamento(conditionDTO);
        buyRequestDTO.setProduto(productDTO);

        return buyRequestDTO;
    }
}
