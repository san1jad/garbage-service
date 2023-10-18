package com.garbage.serviceImpl;

import com.common.dto.garbage.ElectronicsGarbageDTO;
import com.common.exception.HandledInternalServerException;
import com.common.vo.garbage.ElectronicsGarbageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garbage.common.CommonMapper;
import com.garbage.entity.ElectronicsGarbage;
import com.garbage.repo.ElectronicsGarbageRepository;
import com.garbage.service.ElectronicsGarbageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElectronicsGarbageServiceImpl implements ElectronicsGarbageService {

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    ObjectMapper objectMapper;

    private final ElectronicsGarbageRepository electronicsGarbageRepository;

    public ElectronicsGarbageServiceImpl(ElectronicsGarbageRepository electronicsGarbageRepository,
                                         CommonMapper commonMapper,
                                         ObjectMapper objectMapper) {
        this.electronicsGarbageRepository = electronicsGarbageRepository;
        this.commonMapper = commonMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<ElectronicsGarbageVO> getAllElectronicsGarbage() {
        return electronicsGarbageRepository.findAll().stream().map(electronicsGarbage -> {
            ElectronicsGarbageVO electronicsGarbageVO = objectMapper.convertValue(electronicsGarbage, ElectronicsGarbageVO.class);
            electronicsGarbageVO = commonMapper.mapNames(electronicsGarbage.getItemNameId(),electronicsGarbage.getDisposalMethodId(),electronicsGarbageVO);
            return electronicsGarbageVO;
        }).collect(Collectors.toList());
    }

    @Override
    public ElectronicsGarbageVO getElectronicsGarbageById(Long id) {
        Optional<ElectronicsGarbage> byId = electronicsGarbageRepository.findById(id);
        return byId
                .map(electronicsGarbage -> objectMapper.convertValue(electronicsGarbage, ElectronicsGarbageVO.class))
                .map(electronicsGarbageVO -> commonMapper.mapNames(byId.get().getItemNameId(),byId.get().getDisposalMethodId(),electronicsGarbageVO))
                .orElseThrow(() -> new HandledInternalServerException("Garbage details fro the ID not found"));
    }

    @Override
    public ElectronicsGarbageVO createElectronicsGarbage(ElectronicsGarbageDTO electronicsGarbageDTO) {
        ElectronicsGarbage electronicsGarbage = objectMapper.convertValue(electronicsGarbageDTO, ElectronicsGarbage.class);
        return Optional.of(electronicsGarbageRepository.save(electronicsGarbage))
                .map(electronicsGarbageMap -> objectMapper.convertValue(electronicsGarbageMap, ElectronicsGarbageVO.class))
                .map(electronicsGarbageVO -> commonMapper.mapNames(electronicsGarbageDTO.getItemNameId(),electronicsGarbageDTO.getDisposalMethodId(),electronicsGarbageVO))
                .orElseThrow(() -> new HandledInternalServerException("Error while save garbage form data"));

    }

    @Override
    public ElectronicsGarbageVO updateElectronicsGarbage(Long id, ElectronicsGarbageDTO electronicsGarbageDTO) {
        ElectronicsGarbage newElectronicsGarbage = objectMapper.convertValue(electronicsGarbageDTO, ElectronicsGarbage.class);
        return electronicsGarbageRepository.findById(id)
                .map(existingElectronicsGarbage -> {
                    commonMapper.mapElectronicsGarbageObj(newElectronicsGarbage, existingElectronicsGarbage);
                    ElectronicsGarbageVO electronicsGarbageVO = objectMapper.convertValue(electronicsGarbageRepository.save(existingElectronicsGarbage), ElectronicsGarbageVO.class);
                    return commonMapper.mapNames(electronicsGarbageDTO.getItemNameId(),electronicsGarbageDTO.getDisposalMethodId(),electronicsGarbageVO);
                })
                .orElseThrow(() -> new HandledInternalServerException("Error while update garbage form data"));
    }


    @Override
    public void deleteElectronicsGarbage(Long id) {
        electronicsGarbageRepository.deleteById(id);
    }
}
