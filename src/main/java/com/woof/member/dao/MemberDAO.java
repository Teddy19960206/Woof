package com.woof.member.dao;

import java.util.*;

import com.woof.member.entity.Member;

public interface MemberDAO {
	int insert(Member member);

	int update(Member member);

	int delete(String memNo);

	Member findByMemberNo(String memNo);
	
	List<Member> getAll();
	
	List<Member> getByCompositeQuery(Map<String, String> map);

	long getTotal();

}

//package com.woof.member.dao;
//	import java.util.*;
//
//import com.woof.member.entity.MemberVO;
//
//	public interface MemberDAO {
//		public void insert(MemberVO memberVO);
//
//		public void update(MemberVO memberVO);
//
//		public void delete(Integer memberVO);
//
//		public MemberVO findByPrimaryKey(String memNo);
//
//		public List<MemberVO> getAll();
//
//	}
//