package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.MemberRequestDTO;
import com.example.demo.dto.MemberResponseDTO;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO,  HttpSession session) {
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        // 인증 성공 후 처리
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public ResponseEntity<?> getLogin() {
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return null;
    }

}
