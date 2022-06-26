package com.amcones.nocv.view;

import com.amcones.nocv.entity.NocvData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NocvDataView extends NocvData {
    private Integer page;
    private Integer limit;
}
