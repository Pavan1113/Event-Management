package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Dto.Admin;
import Dto.ClientEvent;

public class ClientEventDao 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pavan");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public ClientEvent saveClientEvent(ClientEvent clientEvent) {
		et.begin();
		em.persist(clientEvent);
		et.commit();
		return clientEvent;
	}
	
	public ClientEvent findClientEvent(int clientEventId) {
		ClientEvent clientEvent = em.find(ClientEvent.class, clientEventId);
		if(clientEvent!=null) {
			return clientEvent;
		}
		return null;
	}
	
	public ClientEvent upadateClientEvent(ClientEvent clientEvent, int id) {
		ClientEvent exclientEvent = em.find(ClientEvent.class, id);
		if(exclientEvent!=null) {
		clientEvent.setClientEventId(id);
			et.begin();
			em.merge(exclientEvent);
			et.commit();
			return exclientEvent;
		}
		return null;
	}
	
	public ClientEvent deleteClientEvent(int id) {
		ClientEvent exclientEvent = em.find(ClientEvent.class, id);
		if(exclientEvent!=null) {
			et.begin();
			em.remove(exclientEvent);
			et.commit();
			return exclientEvent;
		}
		return null;	
	}
}
