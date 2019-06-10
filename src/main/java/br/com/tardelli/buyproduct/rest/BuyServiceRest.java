package br.com.tardelli.buyproduct.rest;

import br.com.tardelli.buyproduct.dto.BuyRequestDTO;
import br.com.tardelli.buyproduct.dto.InstallmentDTO;
import br.com.tardelli.buyproduct.service.BuyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@Api(value = "BuyProduct")
public class BuyServiceRest {

    private BuyService buyService;

    @Autowired
    public BuyServiceRest(BuyService buyService) {
        this.buyService = buyService;
    }

    @ApiOperation(
            value = "Buy Product",
            response = InstallmentDTO.class,
            notes = "Return List Installments by Buy Request \n\n\n " +
                    "<b> Required Parameters </b> \n\n " +
                    "qtdeParcelas \n\n " +
                    "valorEntrada \n\n " +
                    "codigo \n\n" +
                    "nome \n\n " +
                    "valor \n\n ")
    @PostMapping(path = "/buy/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buy(@RequestBody BuyRequestDTO buyRequest) {
        List<InstallmentDTO> installments = buyService.buy(buyRequest);

        if (Objects.nonNull(installments)) {
            return new ResponseEntity<>(installments, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
