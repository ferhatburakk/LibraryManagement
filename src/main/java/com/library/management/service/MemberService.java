package com.library.management.service;

import com.library.management.mapper.MemberMapper;
import com.library.management.model.dto.MemberDto;
import com.library.management.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto save(MemberDto memberDto) {
        return MemberMapper.toMemberDto(memberRepository.save(MemberMapper.toMemberEntity(memberDto)));
    }

    public MemberDto getById(Long id) {
        return MemberMapper.toMemberDto(memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found with given Id.")));
    }

    public MemberDto getByEmail(String email) {
        return MemberMapper.toMemberDto(memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Member not found with given Email.")));
    }

    public List<MemberDto> getAll(){
        return MemberMapper.toMemberDtoList(memberRepository.findAll());
    }

    public MemberDto update(MemberDto memberDto) {
        MemberDto result = getByEmail(memberDto.getEmail());
        result.setName(memberDto.getName());
        result.setEmail(memberDto.getEmail());
        result = save(result);

        return result;
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

}
