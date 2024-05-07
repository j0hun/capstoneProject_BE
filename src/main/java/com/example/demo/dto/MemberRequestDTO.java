package com.example.demo.dto;

import com.example.demo.constant.Role;
import com.example.demo.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDTO {

    private String email;
    private String name;
    private String password;

    public Member toEntity(){
        return new Member(email,name,password, Role.USER);
    }
}
