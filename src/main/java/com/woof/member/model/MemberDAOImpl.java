package com.woof.member.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woof.util.Util;

public class MemberDAOImpl implements MemberDAO {
	private static final String INSERT_STMT = "INSERT INTO member (mem_no, mem_name, mem_gender, mem_photo, mem_email, mem_password, mem_tel, mem_address, mem_bd, momo_point, total_class, mem_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_STMT = "UPDATE member set mem_name = ?, mem_gender = ?, mem_photo = ?, mem_email = ?, mem_password = ?, mem_tel = ?, mem_address = ?, mem_bd = ?, momo_point = ?, total_class = ?, mem_status = ? WHERE mem_no = ?";

	private static final String DELETE_STMT = "DELETE FROM member WHERE mem_no = ?";

	private static final String FIND_BY_MEMNO = "SELECT * FROM member WHERE mem_no = ?";

	private static final String GET_ALL = "SELECT * FROM member";

	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = Util.getConnection();
			ps = con.prepareStatement(INSERT_STMT);

			ps.setInt(1, memberVO.getMemNo());
			ps.setString(2, memberVO.getMemName());
			ps.setString(3, memberVO.getMemGender());
			ps.setBytes(4, memberVO.getMemPhoto());
			ps.setString(5, memberVO.getMemEmail());
			ps.setString(6, memberVO.getMemPassword());
			ps.setString(7, memberVO.getMemTel());
			ps.setString(8, memberVO.getMemAdress());
			ps.setDate(9, memberVO.getMemBd());
			ps.setInt(10, memberVO.getMomoPoint());
			ps.setInt(11, memberVO.getTotalClass());
			ps.setInt(12, memberVO.getMemStatus());

			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, null);
		}

		if (count == 1) {
			System.out.println("新增成功");
		} else {
			System.out.println("新增失敗");
		}
	}

	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = Util.getConnection();
			ps = con.prepareStatement(UPDATE_STMT);

			ps.setString(1, memberVO.getMemName());
			ps.setString(2, memberVO.getMemGender());
			ps.setBytes(3, memberVO.getMemPhoto());
			ps.setString(4, memberVO.getMemEmail());
			ps.setString(5, memberVO.getMemPassword());
			ps.setString(6, memberVO.getMemTel());
			ps.setString(7, memberVO.getMemAdress());
			ps.setDate(8, memberVO.getMemBd());
			ps.setInt(9, memberVO.getMomoPoint());
			ps.setInt(10, memberVO.getTotalClass());
			ps.setInt(11, memberVO.getMemStatus());
			ps.setInt(12, memberVO.getMemNo());

			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, null);
		}

		if (count == 1) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失敗");
		}
	}

	@Override
	public void delete(Integer memNo) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = Util.getConnection();
			ps = con.prepareStatement(DELETE_STMT);
			ps.setInt(1, memNo);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, null);
		}

		if (count == 1) {
			System.out.println("刪除成功");
		} else {
			System.out.println("刪除失敗");
		}
	}

	@Override
	public MemberVO findByPrimaryKey(Integer memNo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO memberVO = null;

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(FIND_BY_MEMNO);
			ps.setInt(1, memNo);
			rs = ps.executeQuery();
			if (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemNo(rs.getInt("mem_no"));
				memberVO.setMemName(rs.getString("mem_name"));
				memberVO.setMemGender(rs.getString("mem_gender"));
				memberVO.setMemPhoto(rs.getBytes("mem_photo"));
				memberVO.setMemEmail(rs.getString("mem_email"));
				memberVO.setMemPassword(rs.getString("mem_password"));
				memberVO.setMemTel(rs.getString("mem_tel"));
				memberVO.setMemAdress(rs.getString("mem_address"));
				memberVO.setMemBd(rs.getDate("mem_bd"));
				memberVO.setMomoPoint(rs.getInt("momo_point"));
				memberVO.setTotalClass(rs.getInt("total_class"));
				memberVO.setMemStatus(rs.getInt("mem_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, rs);
		}

		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MemberVO> memberList = new ArrayList<>();

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setMemNo(rs.getInt("mem_no"));
				memberVO.setMemName(rs.getString("mem_name"));
				memberVO.setMemGender(rs.getString("mem_gender"));
				memberVO.setMemPhoto(rs.getBytes("mem_photo"));
				memberVO.setMemEmail(rs.getString("mem_email"));
				memberVO.setMemPassword(rs.getString("mem_password"));
				memberVO.setMemTel(rs.getString("mem_tel"));
				memberVO.setMemAdress(rs.getString("mem_address"));
				memberVO.setMemBd(rs.getDate("mem_bd"));
				memberVO.setMomoPoint(rs.getInt("momo_point"));
				memberVO.setTotalClass(rs.getInt("total_class"));
				memberVO.setMemStatus(rs.getInt("mem_status"));

				memberList.add(memberVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, rs);
		}

		return memberList;
	}
	public static void main(String[] args) {
		MemberDAO memberDAO = new MemberDAOImpl();
		MemberVO memberVO = new MemberVO();

//		// 新增
//		memberVO.setMemNo(15); // 假設的數值，您可能需要相應的設定值
//		memberVO.setMemName("jack");
//		memberVO.setMemGender("M"); // 假設的數值
//		memberVO.setMemPhoto(null); // 假設為null
//		memberVO.setMemEmail("example@example.com");
//		memberVO.setMemPassword("password123");
//		memberVO.setMemTel("123456789");
//		memberVO.setMemAdress("Example Address");
//		memberVO.setMemBd(Date.valueOf("1990-01-01"));
//		memberVO.setMomoPoint(100); // 假設的數值
//		memberVO.setTotalClass(5); // 假設的數值
//		memberVO.setMemStatus(1); // 假設的數值
//		memberDAO.insert(memberVO);

//		// 修改
//		memberVO.setMemNo(5); // 假設的數值
//		memberVO.setMemName("Updated Name");
//		memberVO.setMemGender("F"); // 假設的數值
//		memberVO.setMemEmail("updated@example.com");
//		memberDAO.update(memberVO);
//
//		// 刪除
//		memberDAO.delete(17); // 假設的數值
//
//		// 查詢
//		memberVO = memberDAO.findByPrimaryKey(13); // 假設的數值
//		System.out.println(memberVO);
//
//		// 查詢全部
//		List<MemberVO> memberVOList = memberDAO.getAll();
//		for (MemberVO memberVOItem : memberVOList) {
//			System.out.println(memberVOItem.getMemNo());
//			System.out.println(memberVOItem.getMemName());
//			System.out.println(memberVOItem.getMemGender());
//			System.out.println(memberVOItem.getMemPhoto());
//			System.out.println(memberVOItem.getMemEmail());
//			System.out.println(memberVOItem.getMemPassword());
//			System.out.println(memberVOItem.getMemTel());
//			System.out.println(memberVOItem.getMemAdress());
//			System.out.println(memberVOItem.getMemBd());
//			System.out.println(memberVOItem.getMomoPoint());
//			System.out.println(memberVOItem.getTotalClass());
//			System.out.println(memberVOItem.getMemStatus());
//		}
	}
}
