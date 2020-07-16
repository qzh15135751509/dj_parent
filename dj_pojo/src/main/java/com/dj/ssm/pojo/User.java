package com.dj.ssm.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    private Integer id;

    private String userName;

    private String userPwd;

    private Integer isDel;
}
