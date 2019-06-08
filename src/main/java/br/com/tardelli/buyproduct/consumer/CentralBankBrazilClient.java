package br.com.tardelli.buyproduct.consumer;

import br.com.tardelli.buyproduct.model.SelicRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${consumers.centralBankBrazil.name}", url = "${consumers.centralBankBrazil.url}")
public interface CentralBankBrazilClient {

    @RequestMapping(method = RequestMethod.GET, value = "${consumers.centralBankBrazil.selicRatesByLastDays}")
    List<SelicRate> findSelicRatesByLastDays(@PathVariable("lastDays") Integer lastDays, @RequestParam(name = "formato") String format);

}
