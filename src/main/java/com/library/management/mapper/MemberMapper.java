package com.library.management.mapper;


import com.library.management.model.dto.MemberDto;
import com.library.management.model.entity.Member;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MemberMapper {

    public static MemberDto toMemberDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        memberDto.setId(member.getId());
        memberDto.setPassword(member.getPassword());
        memberDto.setRole(member.getRole());
        return memberDto;
    }

    public static Member toMemberEntity(MemberDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());
        member.setId(memberDto.getId());
        member.setPassword(memberDto.getPassword());
        member.setRole(memberDto.getRole());
        return member;
    }

    public static List<MemberDto> toMemberDtoList(List<Member> memberList) {
        return memberList.stream()
                .map(MemberMapper::toMemberDto)
                .collect(Collectors.toList());
    }

    public static List<Member> toMemberEntityList(List<MemberDto> memberDtoList) {
        return memberDtoList.stream()
                .map(MemberMapper::toMemberEntity)
                .collect(Collectors.toList());
    }

}
