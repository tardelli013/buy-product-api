package br.com.tardelli.buyproduct.rest;

import br.com.tardelli.buyproduct.dto.BuyRequestDTO;
import br.com.tardelli.buyproduct.dto.InstallmentDTO;
import br.com.tardelli.buyproduct.service.BuyService;
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
public class BuyServiceRest {

    private BuyService buyService;

    @Autowired
    public BuyServiceRest(BuyService buyService) {
        this.buyService = buyService;
    }

    @PostMapping(path = "/buy/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InstallmentDTO>> buy(@RequestBody BuyRequestDTO buyRequest) {
        List<InstallmentDTO> installments = buyService.buy(buyRequest);

        if (Objects.nonNull(installments)) {
            return new ResponseEntity<>(installments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
