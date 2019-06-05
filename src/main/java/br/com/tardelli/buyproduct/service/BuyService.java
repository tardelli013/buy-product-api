package br.com.tardelli.buyproduct.service;

import br.com.tardelli.buyproduct.dto.BuyRequestDTO;
import br.com.tardelli.buyproduct.dto.InstallmentDTO;
import br.com.tardelli.buyproduct.factory.InstallmentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyService {

    @Autowired
    private InstallmentFactory factory;

    public List<InstallmentDTO> buy(BuyRequestDTO buyRequest) {

        factory.build();
        //TODO fake object temp

        List<InstallmentDTO> list = new ArrayList<>();
        InstallmentDTO dto = new InstallmentDTO();
        dto.setNumeroParcela(1);
        dto.setTaxaJurosAoMes(2.23);
        dto.setValor(123.33);
        list.add(dto);

        return list;
    }
}
