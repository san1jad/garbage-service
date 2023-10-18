package com.garbage.api;

import com.common.exception.HandledInternalServerException;
import com.common.vo.info.DisposalMethodVO;
import com.common.vo.info.ItemVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "INFO-SERVICE")
public interface OpenFeignInfoServiceCalls {

    @GetMapping("/disposal-methods/{id}")
    public ResponseEntity<DisposalMethodVO> getDisposalMethodById(@PathVariable("id") Long id);

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemVO> getItemById(@PathVariable Long id);

    default void fallback(Exception e){
        throw new HandledInternalServerException("Info service is not available right now. Please try again later !!");
    }
}
