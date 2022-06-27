package com.amcones.nocv.view;

import com.amcones.nocv.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView extends User {
    private Integer page;
    private Integer limit;
}
