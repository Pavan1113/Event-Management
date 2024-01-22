package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Dto.Admin;

public class AdminDao 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pavan");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Admin saveAdmin(Admin admin) {
		et.begin();
		em.persist(admin);
		et.commit();
		return admin;
	}
	
	public Admin findAdmin(int adminId) {
		Admin admin = em.find(Admin.class, adminId);
		if(admin!=null) {
			return admin;
		}
		return null;
	}
	
	public Admin updateAdmin(Admin admin, int id) {
		Admin exadmin = em.find(Admin.class, id);
		if(exadmin!=null) {
			admin.setAdminId(id);
			et.begin();
			em.merge(admin);
			et.commit();
			return admin;
		}
		return null;
	}
	
	public Admin deleteAdmin(int id) {
		Admin exadmin = em.find(Admin.class, id);
		if(exadmin!=null) {
			et.begin();
			em.remove(exadmin);
			et.commit();
			return exadmin;
		}
		return null;	
	}
	
}
