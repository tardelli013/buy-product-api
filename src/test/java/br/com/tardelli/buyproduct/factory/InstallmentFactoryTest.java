package br.com.tardelli.buyproduct.factory;

        import br.com.tardelli.buyproduct.dto.BuyRequestDTO;
        import br.com.tardelli.buyproduct.dto.InstallmentDTO;
        import br.com.tardelli.buyproduct.dto.PaymentConditionDTO;
        import br.com.tardelli.buyproduct.dto.ProductDTO;
        import org.junit.Assert;
        import org.junit.Test;

        import java.util.List;

public class InstallmentFactoryTest {

    @Test
    public void calcValueForParcelTest() {
        Double percentage = InstallmentFactory.calcPercentage(10.0, 1000.0);

        Assert.assertEquals(new Double(100.0), percentage);
    }

    @Test
    public void calcPercentageTest() {
        Double valueForParcel = InstallmentFactory.calcValueForParcel(mockBuyResquest());

        Assert.assertEquals(new Double(90.0), valueForParcel);
    }

    @Test
    public void buildTest() {
        List<InstallmentDTO> installments = InstallmentFactory.build(6.0, mockBuyResquest());

        Assert.assertEquals(installments.size(), 10);

        installments.forEach(installmentDTO -> {
            Assert.assertEquals(new Double(6.0),installmentDTO.getTaxaJurosAoMes());
            Assert.assertEquals(new Double(95.4),installmentDTO.getValor());
        });
    }

    private BuyRequestDTO mockBuyResquest() {
        BuyRequestDTO buyRequestDTO = new BuyRequestDTO();

        ProductDTO productDTO = new ProductDTO();
        productDTO.setValor(1000.0);
        buyRequestDTO.setProduto(productDTO);

        PaymentConditionDTO conditionDTO = new PaymentConditionDTO();
        conditionDTO.setValorEntrada(100.0);
        conditionDTO.setQtdeParcelas(10);
        buyRequestDTO.setCondicaoPagamento(conditionDTO);
        return buyRequestDTO;
    }
}
