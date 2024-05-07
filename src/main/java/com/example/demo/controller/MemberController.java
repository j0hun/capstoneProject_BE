package com.example.demo.controller;

import com.example.demo.dto.MemberRequestDTO;
import com.example.demo.dto.MemberResponseDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getMembers(){
        List<MemberResponseDTO> members = memberService.findMembers();
        return new ResponseEntity<>(members,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> postMember(@RequestBody MemberRequestDTO memberRequestDTO) {
        memberService.addMember(memberRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
