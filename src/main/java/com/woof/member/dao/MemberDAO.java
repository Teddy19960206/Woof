package com.woof.member.dao;

import java.util.*;

import com.woof.member.entity.Member;

public interface MemberDAO {
	int insert(Member member);

	int update(Member member);

	int deletePhoto(String memNo);

	Member findByMemberNo(String memNo);
	
	List<Member> getAll();
	
	List<Member> getByCompositeQuery(Map<String, String> map);
	
	List<Member> getAll(int currentPage);
	
	long getTotal();

}