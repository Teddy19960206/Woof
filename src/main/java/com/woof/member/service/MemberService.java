package com.woof.member.service;

import java.util.List;

import com.woof.member.entity.Member;

public interface MemberService {
	Member addMember(Member member);

	Member updateMember(Member member);

	// Delete a member by its number or ID
	void deleteMember(Integer memberNo);

	// Find a member by its number or ID
	Member findMemberByNo(Integer memberNo);

	// Get all members
	List<Member> getAllMembers();
}
