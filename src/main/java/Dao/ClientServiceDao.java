package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Dto.ClientService;

public class ClientServiceDao 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pavan");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public ClientService saveClientService(ClientService clientService) {
		et.begin();
		em.persist(clientService);
		et.commit();
		return clientService;
	}
	
	public ClientService findClientService(int clientServiceId) {
		ClientService clientService = em.find(ClientService.class, clientServiceId);
		if(clientService!=null) {
			return clientService;
		}
		return null;
	}
	
	public ClientService upadateClientService(ClientService clientService, int id) {
		ClientService exclientService = em.find(ClientService.class, id);
		if(exclientService!=null) {
		clientService.setClientServiceId(id); 
			et.begin();
			em.merge(exclientService);
			et.commit();
			return exclientService;
		}
		return null;
	}
	
	public ClientService deleteClientService(int id) {
		ClientService exclientService = em.find(ClientService.class, id);
		if(exclientService!=null) {
			et.begin();
			em.remove(exclientService);
			et.commit();
			return exclientService;
		}
		return null;
	
	}
}
	
