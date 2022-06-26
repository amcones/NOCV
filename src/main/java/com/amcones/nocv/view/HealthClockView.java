package com.amcones.nocv.view;

import com.amcones.nocv.entity.HealthClock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HealthClockView extends HealthClock {
    private Integer page;
    private Integer limit;
}
