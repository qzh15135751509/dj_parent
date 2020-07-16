package com.dj.ssm.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Token {
    private Integer id;

    private String token;

    private Integer userId;

    private Date validateTime;
}
