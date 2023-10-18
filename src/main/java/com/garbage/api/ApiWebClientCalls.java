package com.garbage.api;

import com.common.vo.info.DisposalMethodVO;
import com.common.vo.info.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public class ApiWebClientCalls {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${info.service-name}")
    private String infoServiceName;

    @Value("${info.items.request-path}")
    private String itemRequestPath;

    @Value("${info.disposal-methods.request-path}")
    private String disposalMethodsRequestPath;


    public Optional<ItemVO> getItemById(Long id){
        return Optional.ofNullable(webClientBuilder.build().get()
                .uri(infoServiceName + itemRequestPath+"/"+id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(ItemVO.class)
                .block());
    }

    public Optional<DisposalMethodVO> getDisposalMethodById(Long id){
        return Optional.ofNullable(webClientBuilder.build().get()
                .uri(infoServiceName + disposalMethodsRequestPath+"/"+id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(DisposalMethodVO.class)
                .block());
    }

}
