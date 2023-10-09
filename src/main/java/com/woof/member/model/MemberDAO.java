package com.woof.member.model;
	import java.util.*;

	public interface MemberDAO {
		public void insert(MemberVO memberVO);

		public void update(MemberVO memberVO);

		public void delete(Integer memberVO);

		public MemberVO findByPrimaryKey(Integer memNo);

		public List<MemberVO> getAll();

	}