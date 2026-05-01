package com.chirayu.flowgrid.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.usertype.UserType;

/**
 * @author chirayu
 * @created 2026-05-01
 */

@ToString(callSuper = true)
@Setter
@Getter
public class WildPetDTO extends PetDTO {

    private String birthPlace;
}
