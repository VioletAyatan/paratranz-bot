package org.paratranz.bot.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private Integer id;
    private String username;
    private String avatar;
    private String lastVisit;
}
