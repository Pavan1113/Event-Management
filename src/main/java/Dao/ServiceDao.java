package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Dto.Service;

public class ServiceDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pavan");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Service saveService(Service service) {
		et.begin();
		em.persist(service);
		et.commit();
		return service;
	}
	
	public Service findService(int serviceId) {
		Service service = em.find(Service.class, serviceId);
		if(service!=null) {
			return service;
		}
		return null;
	}
	
	public Service updateService(Service Service, int id) {
		Service exservice = em.find(Service.class, id);
		if(exservice!=null) {
			Service.setServiceId(id);
			et.begin();
			em.merge(Service);
			et.commit();
			return Service;
		}
		return null;
	}
	
	public Service deleteService(int id) {
		Service exservice = em.find(Service.class, id);
		if(exservice!=null) {
			et.begin();
			em.remove(exservice);
			et.commit();
			return exservice;
		}
		return null;	
	}

}
