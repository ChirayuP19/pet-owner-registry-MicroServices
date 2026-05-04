package com.chirayu.flowgrid.service.imp;

import com.chirayu.flowgrid.dto.PetCategoryStatisticsDTO;
import com.chirayu.flowgrid.dto.PetGenderStatisticDTO;
import com.chirayu.flowgrid.dto.PetStatisticDTO;
import com.chirayu.flowgrid.enums.Gender;
import com.chirayu.flowgrid.enums.PetType;
import com.chirayu.flowgrid.repository.PetRepository;
import com.chirayu.flowgrid.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chirayu
 * @created 2026-05-03
 */

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public PetStatisticDTO getStatistics() {
        List<Object[]> rows = petRepository.getStatistics();
        PetStatisticDTO petStatisticDTO = new PetStatisticDTO();
        for (Object[] row : rows) {
            String category = (String) row[0];
            Gender gender = (Gender) row[1];
            PetType type = (PetType) row[2];
            Long count = (Long) row[3];
            petStatisticDTO.incrementTotal(count);
            PetCategoryStatisticsDTO petCategoryStatisticDTO = petStatisticDTO.getOrCreateCategory(category);
            petCategoryStatisticDTO.incrementTotal(count);
            PetGenderStatisticDTO petGenderStatistics = petCategoryStatisticDTO.getOrCreateGender(gender);
            petGenderStatistics.incrementTotal(count);
            petGenderStatistics.mergeOrCreateType(type, count);
        }
        return petStatisticDTO;
    }
}
