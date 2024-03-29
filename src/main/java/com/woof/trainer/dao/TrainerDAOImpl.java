package com.woof.trainer.dao;


import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.List;
import java.util.Set;

import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.skill.entity.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.trainer.entity.Trainer;
import org.hibernate.query.Query;

public class TrainerDAOImpl implements TrainerDAO {
	
	private SessionFactory factory;

	public TrainerDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Trainer trainer) {
		return (Integer) getSession().save(trainer);
	}

	@Override
	public int update(Trainer trainer) {
		try {
			getSession().update(trainer);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer trainerNo) {
		Trainer trainer =getSession().get(Trainer.class, trainerNo);
		if(trainer !=null) {
			getSession().delete(trainer);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public Set<Skill> getSkillsList(Integer trainerNo) {

		Trainer trainer = getSession().get(Trainer.class, trainerNo);
		return trainer.getSkills();
	}

	@Override
	public Trainer findBytrainerNo(Integer trainerNo) {
		return getSession().get(Trainer.class, trainerNo);
	}

	@Override
	public Set<GroupScheduleDetail> groupScheduleDetails(Integer trainerNo) {
		return getSession().get(Trainer.class , trainerNo).getGroupScheduleDetailSet();
	}

	@Override
	public List<Trainer> getAll() {
		return getSession().createQuery("FROM Trainer", Trainer.class).list();
	}

	public Trainer getByAdmin(String adminNo){

		String hql  = "FROM Trainer trainer WHERE trainer.administrator.adminNo = :adminNo";
		Query query = getSession().createQuery(hql, Trainer.class);
		query.setParameter("adminNo" , adminNo);
		return (Trainer) query.getSingleResult();
	}

	@Override
	public List<Trainer> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM Trainer", Trainer.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Trainer", Long.class)
				.uniqueResult();
	}
	
}


//	 private static final String INSERT_STMT = "INSERT INTO trainer ( admin_no ,experience) VALUES (? , ? )";
//
//	    private static final String UPDATE_STMT = "UPDATE trainer SET admin_no=?,experience=? WHERE trainer_no=?";
//
//	    private static final String DELETE_STMT = "DELETE FROM trainer WHERE trainer_no= ?";
//
//	    private static final String FIND_BY_ADMINNO= "SELECT * FROM trainer WHERE trainer_no = ?";
//
//	    private static final String GET_ALL = "SELECT * FROM trainer";
//
//	    @Override
//	    public void insert(Trainer trainerVO ) {
//
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        int count = 0;
//	        byte[] a = null;
//	        
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(INSERT_STMT);
//	            ps.setInt(1, trainerVO.getAdminNo());
//	            ps.setString(2, trainerVO.getExperience());
//	           
//	            
//
//	            count = ps.executeUpdate();
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con, ps, null);
//	        }
//
//
//	        if (count == 1) {
//	            System.out.println("新增成功");
//	        } else {
//	            System.out.println("新增失敗");
//	        }
//
//	    }
//
//	    @Override
//	    public void update(Trainer trainerVO) {
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        int count = 0;
//
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(UPDATE_STMT);
//	            ps.setInt(1, trainerVO.getAdminNo());
//	            ps.setString(2, trainerVO.getExperience());
//	            ps.setInt(3, trainerVO.getTrainerNo());
//	       
//
//	            count = ps.executeUpdate();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con , ps , null);
//	        }
//
//	        if (count == 1) {
//	            System.out.println("修改成功");
//	        } else {
//	            System.out.println("修改失敗");
//	        }
//
//	    }
//
//	    public void delete(Trainer trainerVO) {
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        int count  = 0;
//
//	        try {
//	            con = Util.getConnection();
//	            ps =  con.prepareStatement(DELETE_STMT);
//	            ps.setInt(1 , trainerVO.getTrainerNo());
//	            count = ps.executeUpdate();
//
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con  , ps ,null);
//	        }
//
//	        if( count == 1){
//	            System.out.println("刪除成功");
//	        }else{
//	            System.out.println("刪除失敗");
//	        }
//	    }
//
//	    
//	    @Override
//	    public Trainer  findByTrainerNo(Integer trainerNo){
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//
//	        Trainer trainerVO = null;
//
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(FIND_BY_ADMINNO);
//	            ps.setInt(1,trainerNo);
//	            rs = ps.executeQuery();
//
//	            if (rs.next()){
//	            	trainerVO = new Trainer();
//	            	trainerVO.setTrainerNo(trainerNo);
//	            	trainerVO.setAdminNo(rs.getInt("admin_no"));
//	            	trainerVO.setExperience(rs.getString("experience"));
//	          
//	            	
//	            	
//	            	
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con , ps , rs);
//	        }
//
//	        return trainerVO;
//	    }
//
//	    @Override
//	    public List<Trainer> getAll() {
//
//
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//
//	        List<Trainer> trainerVOList = new ArrayList<>();
//
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(GET_ALL);
//	            rs = ps.executeQuery();
//
//	            while (rs.next()){
//	            	Trainer trainerVO = new Trainer();
//	            	trainerVO.setTrainerNo(rs.getInt("trainer_No"));
//	            	trainerVO.setAdminNo(rs.getInt("admin_no"));
//	            	trainerVO.setExperience(rs.getString("experience"));
//	    
//
//	            	trainerVOList.add(trainerVO);
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con , ps , rs);
//	        }
//
//	        return trainerVOList;
//	    }
//
//
//	    public static void main(String[] args) throws IOException {
//	    	TrainerDAO trainerDAO = new TrainerDAOImpl();
//
//	    	Trainer trainerVO = new Trainer();
//	        
//	    	trainerVO.setTrainerNo(11);
//	    	trainerVO.setAdminNo(1);
//	    	trainerVO.setExperience("寵物大師");
//  
//     	
//
//
//
//	        trainerDAO.delete(trainerVO);
//
//	        System.out.println(trainerDAO.findByTrainerNo(1));
//	
//	        List<Trainer> trainerVOList = trainerDAO.getAll();
//	
//	        for (Trainer trainer : trainerVOList){
//	            System.out.println(trainer);
//	
//	        }
//
//	    }
//
//	    public static byte[] getPictureBtyeArray(String path) throws IOException {
//
//	        FileInputStream fis = new FileInputStream(path);
//	        byte[] buffer = new byte[fis.available()];
//	        fis.read(buffer);
//	        fis.close();
//	        return buffer;
//	    }
	
	