package com.woof.member.dao;

import java.util.*;

import com.woof.member.entity.Member;

public interface MemberDAO {
	int insert(Member memberVO);

	int update(Member memberVO);

	int delete(Integer memNo);

	List<Member> getAll();

	Member findByMemNo(Integer memNo);

	List<Member> getAll(int currentPage);

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
//		public MemberVO findByPrimaryKey(Integer memNo);
//
//		public List<MemberVO> getAll();
//
//	}