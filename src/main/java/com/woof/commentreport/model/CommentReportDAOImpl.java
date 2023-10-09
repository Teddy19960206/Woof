package com.woof.commentreport.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.woof.util.Util;

public class CommentReportDAOImpl implements CommentReportDAO{

	public static final String INSERT_STMT = "INSERT INTO comment_report VALUES (? , ? , ? , ? , ? , ?)";
	public static final String UPDATE_STMT = "UPDATE comment_report SET mem_no = ?, trainer_no = ?, pta_no = ?, cp_context = ?, cp_status= ?, cp_date = ? WHERE cr_no = ?";
	private static final String DELETE_STMT = "DELETE FROM comment_report WHERE cr_no = ?";
	private static final String FIND_BY_CRNO = "SELECT * FROM comment_report WHERE cr_no = ?";
	private static final String GET_ALL = "SELECT * FROM comment_report";
	
	@Override
	public void insert(CommentReportVO commentReportVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        byte[] a = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1, commentReportVO.getMemNo());
            ps.setInt(2, commentReportVO.getTrainerNo());
            ps.setInt(3, commentReportVO.getPtaNo());
            ps.setString(4, commentReportVO.getCpContext());
            ps.setInt(5, commentReportVO.getCpStatus());
            ps.setTimestamp(6, commentReportVO.getCpDate());

            

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
	public void update(CommentReportVO commentReportVO) {
		
	}
	@Override
	public void delete(Integer crNo) {
		
	}
	@Override
	public CommentReportVO findByCrNo(Integer crNo) {
		return null;
	}
	@Override
	public List<CommentReportVO> getAll() {
		return null;
	}
	
//	public static void main(String[] args) {
//		CommentReportDAO commentReport = new CommentReportDAOImpl();
//		List<CommentReportVO> commentReportVOList = commentReport.getAll();
//		for(CommentReportVO commentReportVO: commentReportVOList) {
//			System.out.println(commentReportVO.getCrNo());
//			System.out.println(commentReportVO.getMemNo());
//			System.out.println(commentReportVO.getTrainerNo());
//			System.out.println(commentReportVO.getPtaNo());
//			System.out.println(commentReportVO.getCpContext());
//			System.out.println(commentReportVO.getCpStatus());
//			System.out.println(commentReportVO.getCpDate());
//		}
//	}
}
