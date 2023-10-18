package com.garbage.common;

import com.common.vo.garbage.ElectronicsGarbageVO;
import com.garbage.api.OpenFeignInfoServiceCalls;
import com.garbage.entity.ElectronicsGarbage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@Log4j2
public class CommonMapper {

    @Autowired
    private final OpenFeignInfoServiceCalls openFeignInfoServiceCalls;

    public CommonMapper(OpenFeignInfoServiceCalls openFeignInfoServiceCalls) {
        this.openFeignInfoServiceCalls = openFeignInfoServiceCalls;
    }

    public void mapElectronicsGarbageObj(ElectronicsGarbage newElectronicsGarbage, ElectronicsGarbage existingElectronicsGarbage) {
        existingElectronicsGarbage.setDisposalMethodId(newElectronicsGarbage.getDisposalMethodId());
        existingElectronicsGarbage.setItemNameId(newElectronicsGarbage.getItemNameId());
        existingElectronicsGarbage.setWeight(newElectronicsGarbage.getWeight());
        existingElectronicsGarbage.setCreatedAt(LocalDate.now());
    }

    public ElectronicsGarbageVO mapNames(Long itemId, Long disposalMethodId, ElectronicsGarbageVO electronicsGarbageVO) {
        Optional.ofNullable(openFeignInfoServiceCalls.getItemById(itemId))
                .map(itemVOR -> itemVOR.getBody())
                .ifPresent(itemVO -> electronicsGarbageVO.setItemName(itemVO.getName()));
        Optional.ofNullable(openFeignInfoServiceCalls.getDisposalMethodById(disposalMethodId))
                .map(disposalVOR -> disposalVOR.getBody())
                .ifPresent(disposalVO -> electronicsGarbageVO.setDisposalMethodName(disposalVO.getMethod()));
        return electronicsGarbageVO;
    }
}
