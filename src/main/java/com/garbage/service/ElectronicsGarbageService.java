package com.garbage.service;

import com.common.dto.garbage.ElectronicsGarbageDTO;
import com.common.vo.garbage.ElectronicsGarbageVO;

import java.util.List;

public interface ElectronicsGarbageService {
    List<ElectronicsGarbageVO> getAllElectronicsGarbage();
    ElectronicsGarbageVO getElectronicsGarbageById(Long id);
    ElectronicsGarbageVO createElectronicsGarbage(ElectronicsGarbageDTO electronicsGarbageDTO);
    ElectronicsGarbageVO updateElectronicsGarbage(Long id, ElectronicsGarbageDTO electronicsGarbageDTO);
    void deleteElectronicsGarbage(Long id);
}
