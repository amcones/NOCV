package com.amcones.nocv.view;

import com.amcones.nocv.entity.NocvNews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NocvNewsView extends NocvNews {
    private Integer page;
    private Integer limit;
}
