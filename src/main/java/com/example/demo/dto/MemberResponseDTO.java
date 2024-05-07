package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDTO {

    private Long id;
    private String email;
    private String name;
    private String password;

    public static MemberResponseDTO toDTO(Member member){
        return new MemberResponseDTO(member.getId(),member.getEmail(), member.getName(),member.getPassword());
    }

}
