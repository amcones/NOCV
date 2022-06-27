package com.amcones.nocv.view;

import com.amcones.nocv.entity.BanJi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BanJiView extends BanJi {
    private Integer page;
    private Integer limit;
}
