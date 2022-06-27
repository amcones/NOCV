package com.amcones.nocv.view;

import com.amcones.nocv.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class UserView extends User {
    private Integer page;
    private Integer limit;
}
