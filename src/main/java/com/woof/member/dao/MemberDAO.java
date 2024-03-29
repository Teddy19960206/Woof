package com.woof.member.dao;

import java.util.*;

import com.woof.member.entity.Member;

public interface MemberDAO {
	int insert(Member member);

	int update(Member member);

	int delete(String memNo);

	Member findByMemberNo(String memNo);
	
	Member findByMemberEmail(String memEmail);
	
	List<Member> getAll();
	
	List<Member> getByCompositeQuery(Map<String, String> map);
	
	List<Member> getAll(int currentPage);
	
	long getTotal();

	void updateTotalClass(String memNo , Integer clazz);

	Member findMemberByName(String name);

}