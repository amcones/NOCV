package com.amcones.nocv.view;

import com.amcones.nocv.entity.HeSuan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HeSuanView extends HeSuan {
    private Integer page;
    private Integer limit;
}
