package com.amcones.nocv.view;

import com.amcones.nocv.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class RoleView extends Role {
    private Integer page;
    private Integer limit;
}
