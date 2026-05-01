package com.chirayu.flowgrid.util;

import com.chirayu.flowgrid.dto.DomesticPetDTO;
import com.chirayu.flowgrid.dto.OwnerDTO;
import com.chirayu.flowgrid.dto.PetDTO;
import com.chirayu.flowgrid.dto.WildPetDTO;
import com.chirayu.flowgrid.entity.DomesticPet;
import com.chirayu.flowgrid.entity.Owner;
import com.chirayu.flowgrid.entity.Pet;
import com.chirayu.flowgrid.entity.WildPet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {

    @Mapping(source = "pet", target = "petDTO")
    OwnerDTO ownerToOwnerDTO(Owner owner);

    default PetDTO petToPetDTO(Pet pet) {
        return switch (pet) {
            case DomesticPet domesticPet -> domesticPetToDomesticPetDTO(domesticPet);
            case WildPet wildPet -> wildPetToWildPetDTO(wildPet);
            default -> throw new IllegalArgumentException("Invalid pet type");
        };
    }

    DomesticPetDTO domesticPetToDomesticPetDTO(DomesticPet domesticPet);

    WildPetDTO wildPetToWildPetDTO(WildPet wildPet);
}
