package com.library.management.controller;

import com.library.management.model.dto.MemberDto;
import com.library.management.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/save")
    public ResponseEntity<MemberDto> save(@RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.save(memberDto));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MemberDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getById(id));
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<MemberDto> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getByEmail(email));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MemberDto>> getAll() {
        return ResponseEntity.ok(memberService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<MemberDto> update(@RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.update(memberDto));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return ResponseEntity.ok("Member deleted succesfully with given id: " + id);
    }
}
