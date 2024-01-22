package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Dto.Client;
import Dto.ClientEvent;

public class ClientDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pavan");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Client saveClient(Client client) {
		et.begin();
		em.persist(client);
		et.commit();
		return client;
	}
	
	public Client findClient(int clientId) {
		Client client = em.find(Client.class, clientId);
		if(client!=null) {
			return client;
		}
		return null;
	}
	
	public Client upadateClient(Client client, int id) {
		Client exclient = em.find(Client.class, id);
		if(exclient!=null) {
			client.setClientId(id);
			et.begin();
			em.merge(client);
			et.commit();
			return client;
		}
		return null;
	}
	
	public Client deleteClient(int id) {
		Client exclient = em.find(Client.class, id);
		if(exclient!=null) {
			et.begin();
			em.remove(exclient);
			et.commit();
			return exclient;
		}
		return null;	
	}

}
