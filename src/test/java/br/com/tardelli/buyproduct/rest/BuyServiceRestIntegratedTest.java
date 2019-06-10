package br.com.tardelli.buyproduct.rest;

import br.com.tardelli.buyproduct.dto.BuyRequestDTO;
import br.com.tardelli.buyproduct.dto.PaymentConditionDTO;
import br.com.tardelli.buyproduct.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BuyServiceRestIntegratedTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void buyIntegratedTest() throws Exception {

        PaymentConditionDTO conditionDTO = new PaymentConditionDTO();
        conditionDTO.setQtdeParcelas(7);
        conditionDTO.setValorEntrada(100.0);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setValor(800.0);
        productDTO.setNome("Geladeira");
        productDTO.setCodigo(12345);

        BuyRequestDTO buyRequestDTO = new BuyRequestDTO();
        buyRequestDTO.setCondicaoPagamento(conditionDTO);
        buyRequestDTO.setProduto(productDTO);

        mockMvc
                .perform(
                        post("/buy/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(buyRequestDTO))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("\"numeroParcela\":1")))
                .andExpect(content().string(containsString("\"numeroParcela\":2")))
                .andExpect(content().string(containsString("\"numeroParcela\":3")))
                .andExpect(content().string(containsString("\"numeroParcela\":4")))
                .andExpect(content().string(containsString("\"numeroParcela\":5")))
                .andExpect(content().string(containsString("\"numeroParcela\":6")))
                .andExpect(content().string(containsString("\"numeroParcela\":7")))
                .andExpect(content().string(containsString("valor")))
                .andExpect(content().string(containsString("taxaJurosAoMes")));
    }
}
