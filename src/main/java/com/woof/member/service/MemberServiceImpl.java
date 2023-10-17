package com.woof.member.service;

import java.util.List;
import org.hibernate.Session;

import com.woof.member.dao.MemberDAO;
import com.woof.member.dao.MemberDAOImpl;
import com.woof.member.entity.Member;
import com.woof.util.HibernateUtil;

public class MemberServiceImpl implements MemberService {

    private MemberDAO dao;

    public MemberServiceImpl() {
        dao = new MemberDAOImpl(HibernateUtil.getSessionFactory());
    }

    @Override
    public Member addMember(Member member) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (dao.insert(member) == 1) {
                session.getTransaction().commit();
                return member;
            } else {
                session.getTransaction().rollback();
                return null;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e; // or handle the exception appropriately
        }
    }

    @Override
    public Member updateMember(Member member) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (dao.update(member) == 1) {
                session.getTransaction().commit();
                return member;
            } else {
                session.getTransaction().rollback();
                return null;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e; 
        }
    }

    @Override
    public void deleteMember(Integer memNo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (dao.delete(memNo) == 1) {
                session.getTransaction().commit();
            } else {
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e; 
        }
    }

    @Override
    public Member findMemberByNo(Integer memNo) {
        return dao.findByMemberNo(memNo);
    }

    @Override
    public List<Member> getAllMembers() {
    	List<Member>memberList=dao.getAll();
        return memberList;
    }
}
//
