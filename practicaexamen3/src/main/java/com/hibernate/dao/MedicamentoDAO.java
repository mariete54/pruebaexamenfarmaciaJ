package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Medicamento;
import com.hibernate.util.HibernateUtil;

public class MedicamentoDAO {
	public void insertMedicamento(Medicamento m) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.persist(m);
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		
	}
	
	public void updateMedicamento(Medicamento m) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.merge(m);
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		
	}
	
	
	public void deleteMedicamento(int id) {
		Transaction transaction = null;
		Medicamento m = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			m = session.get(Medicamento.class, id);
			session.remove(m);
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		
	}
	
	public Medicamento selectMedicamento(int id) {
		Transaction transaction = null;
		Medicamento m = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			m = session.get(Medicamento.class, id);
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		return m;
	}
	
	public List<Medicamento> selectAllMedicamentos() {
		Transaction transaction = null;
		List<Medicamento> medicamentos = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			medicamentos = session.createQuery("from Medicamento", Medicamento.class).getResultList();
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		return medicamentos;
	}
	
	public List<Medicamento> selectAllSinStock() {
		Transaction transaction = null;
		List<Medicamento> medicamentos = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			medicamentos = session.createQuery("from Medicamento WHERE stock = false", Medicamento.class).getResultList();
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		return medicamentos;
	}




}
