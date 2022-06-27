package com.amcones.nocv.view;

import com.amcones.nocv.entity.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VaccineView extends Vaccine {
    private Integer page;
    private Integer limit;
}
