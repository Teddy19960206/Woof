package com.woof.member.service;

import java.util.List;
import java.util.Map;

import com.woof.member.entity.Member;

public interface MemberService {

	void updateMember(Member member);

	// Delete a member by its number or ID
	void deletePhoto(String memNo);

	// Find a member by its number or ID
	Member findMemberByNo(String memNo);

	// Get all members
	List<Member> getAllMembers();
	
	int getPageTotal();
	
	List<Member> getMembersByCompositeQuery(Map<String, String[]> map);

	void addMember(Member member);

	byte[] getPhotoById(String memNo);
	
    // 新增獲取會員點數的方法定義
    Integer getMemberPoints(String memNo);
	
    void updateMemberPoints(String memNo,Integer momo);
}