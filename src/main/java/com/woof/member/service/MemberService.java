package com.woof.member.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.woof.member.entity.Member;

public interface MemberService {

	void updateMember(Member member);

	// Delete a member by its number or ID
	void deleteMember(String memNo);

	// Find a member by its number or ID
	Member findMemberByNo(String memNo);

	// Get all members
	List<Member> getAllMembers();
	
	int getPageTotal();
	
	List<Member> getMembersByCompositeQuery(Map<String, String[]> map);

	void addMember(Member member);
	
}