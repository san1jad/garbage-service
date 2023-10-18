package com.garbage.repo;

import com.garbage.entity.ElectronicsGarbage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectronicsGarbageRepository extends JpaRepository<ElectronicsGarbage, Long> {
    // You can add custom query methods here if needed
}
