package com.amcones.nocv.view;

import com.amcones.nocv.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class MenuView extends Menu {
    private Integer page;
    private Integer limit;
}
