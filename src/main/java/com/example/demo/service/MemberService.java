package com.example.demo.service;

import com.example.demo.dto.MemberRequestDTO;
import com.example.demo.dto.MemberResponseDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<MemberResponseDTO> findMembers(){
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> MemberResponseDTO.toDTO(member))
                .collect(Collectors.toList());
    }

    public Long addMember(MemberRequestDTO memberRequestDTO) {
        memberRequestDTO.setPassword(passwordEncoder.encode(memberRequestDTO.getPassword()));
        Member member = memberRequestDTO.toEntity();
        memberRepository.save(member);
        return member.getId();
    }

}
