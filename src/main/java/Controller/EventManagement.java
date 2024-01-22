package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Dao.AdminDao;
import Dao.ClientDao;
import Dao.ClientEventDao;
import Dao.ClientServiceDao;
import Dao.ServiceDao;
import Dto.Admin;
import Dto.Client;
import Dto.ClientEvent;
import Dto.ClientService;
import Dto.EventType;
import Dto.Service;

public class EventManagement 
{
	AdminDao adao = new AdminDao();
	ServiceDao sdao = new ServiceDao();
	ClientDao cdao = new ClientDao();
	ClientEventDao cedao = new ClientEventDao();
	ClientServiceDao csdao = new ClientServiceDao();
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pavan");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	
	public static void main(String[] args) 
	{
		EventManagement em = new EventManagement();
		System.out.println(em.createClientEvent());
		
	}
	
	public Admin saveAdmin() {
		Admin admin = new Admin();
		Scanner sc = new Scanner(System.in);
		System.out.println("enter admin name :");
		admin.setAdminName(sc.next());
		System.out.println("enter  admin mail :");
		admin.setAdminMail(sc.next());
		System.out.println("enter admin password");
		admin.setAdminPassword(sc.next());
		System.out.println("eneter admin contact");
		admin.setAdminContact(sc.nextLong());
		
		return adao.saveAdmin(admin);
	}
	
	public Admin Adminlogin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter admin mail :");
		String adminMail = sc.next();
		System.out.println("enter admin password :");
		String adminPassword = sc.next();
		
		Query query = em.createQuery("select a from Admin a where a.adminMail=?1"); 
		query.setParameter(1, adminMail);
		Admin exAdmin = (Admin) query.getSingleResult();
		if(exAdmin != null) {
			if(exAdmin.getAdminPassword().equals(adminPassword)) {
				return exAdmin;
			}
			return null;
		}
		return null;	
	}
	
	public Service saveService() {
		System.out.println("enter admin credentials to proceed");
		Admin exAdmin = Adminlogin();
		if(exAdmin!=null) {
			Service service = new Service();
			Scanner sc = new Scanner(System.in);
			System.out.println("enter service name");
			service.setServiceName(sc.next());
			System.out.println("enter service cost per person");
			service.setServiceCostPerPerson(sc.nextDouble());
			System.out.println("enter service cost per day");
			service.setServiceCostPerDay(sc.nextDouble());
			System.out.println("enter service for no. of days");
			service.setServiceNoOfDays(sc.nextInt());
			Service savedservice = sdao.saveService(service);
			exAdmin.getServices().add(savedservice);
			adao.updateAdmin(exAdmin, exAdmin.getAdminId());
			return service;
		}
		return null;
	}
	
	public List<Service> getAllService(){
		System.out.println("enter admin credentials to proceed");
		Admin exAdmin = Adminlogin();
		if(exAdmin!=null) {
			Query query = em.createQuery("select s from Service s");
			List<Service> services = query.getResultList();
			return services;
		}
		return null;
	}
	
//	public Service updateService() {
//		Scanner sc = new Scanner(System.in);
//		List<Service> services = getAllService();
//		for(Service service : services) {
//			System.out.println(service);
//		}
//		System.out.println("Enter Service id : ");
//		int id = sc.nextInt();
//		for(Service service : services)
//		{
//			if(service.getServiceId() == id)
//			{
//				System.out.println("enter Service name :");
//				service.setServiceName(sc.next());
//				System.out.println("enter service cost per person");
//				service.setServiceCostPerPerson(sc.nextDouble());
//				System.out.println("enter service cost per day");
//				service.setServiceCostPerDay(sc.nextDouble());
//				System.out.println("enter service for no. of days");
//				service.setServiceNoOfDays(sc.nextInt());
//				Service s = sdao.upadateService(service, id);
//				return s;
//			}
//		}
//		return null;	
//	}
	
	public String updateService()
	{
		Scanner sc = new Scanner(System.in);
		List<Service> services = getAllService();
		for(Service s : services)
		{
			System.out.println("ServiceId"+"ServiceName"+"Cost Per Person"+"Cost Per Day");
			System.out.println("    "+s.getServiceId()+"     "+s.getServiceName()+"     "+s.getServiceCostPerPerson()+"     "+s.getServiceCostPerDay());
		}
		System.out.println("**** Choose Service From Above  *****");
		int id = sc.nextInt();
		Service  service = sdao.findService(id);
		System.out.println("Enter Updated Cost Per Person");
		double costperperson = sc.nextDouble();
		System.out.println("Enter Update Cost Per Day");
		service.setServiceCostPerDay(id);
		service.setServiceCostPerPerson(costperperson);
		
		Service s = sdao.updateService(service, id);
		
		if(s!=null)
		{
			return "Service Updated Success";
		}
		return "Invalid Service Id";
	}
		
	public Service deleteService()
	{
		Scanner sc = new Scanner(System.in);
		Admin exAdmin = Adminlogin();
		
		if(exAdmin!=null) {
		List<Service> services = exAdmin.getServices();
		System.out.println("**** Choose Service From Above to delete  *****");
		for(Service s : services)
		{
			System.out.println("ServiceId    "+"ServiceName    "+"Cost Per Person     "+"Cost Per Day     ");
			System.out.println("    "+s.getServiceId()+"     "+s.getServiceName()+"     "+s.getServiceCostPerPerson()+"     "+s.getServiceCostPerDay());
		}
		int id = sc.nextInt();
	
		List<Service> newList = new ArrayList<Service>();
		for(Service s : services) 
		{
			if(s.getServiceId()!=id){
				newList.add(s);
			}
		}
		exAdmin.setServices(newList);
		adao.updateAdmin(exAdmin,exAdmin.getAdminId());
		sdao.deleteService(id);
			}
	return null;
	}
	
	public Client saveClient() {
		Client client = new Client();
		Scanner sc = new Scanner(System.in);
		System.out.println("enter Client name :");
		client.setClientName(sc.next());
		System.out.println("enter  Client mail :");
		client.setClientEmail(sc.next());
		System.out.println("enter Client password");
		client.setClientPassword(sc.next());
		System.out.println("enter Client Location");
		client.setClientPlace(sc.next());
		System.out.println("enter Client contact");
		client.setClientContact(sc.nextLong());
		
		return cdao.saveClient(client);
	}
	
	public Client clientLogin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter Client Email :");
		String clientEmail = sc.next();
		System.out.println("enter Client password :");
		String clientPassword = sc.next();
		
		Query query = em.createQuery("select a from Client a where a.clientEmail=?1"); 
		query.setParameter(1, clientEmail);
		Client exClient = (Client) query.getSingleResult();
		if(exClient != null) {
			if(exClient.getClientPassword().equals(clientPassword)) {
				return exClient;
			}
			return null;
		}
		return null;	
	}
	
	public ClientEvent createClientEvent() {
		 ClientEvent ce = new ClientEvent();
		 Scanner sc = new Scanner(System.in);
		 System.out.println("enter No. of peoples");
		 ce.setClientEventNoOfPeople(sc.nextInt());
		 System.out.println("enter No. of Days");
		 ce.setClientEventNoOfDays(sc.nextInt());
		 System.out.println("enter event location");
		 ce.setClientEventLocation(sc.next());
		 ce.setStartDate(LocalDate.now());
		 
		 System.out.println("Select the Event Type");
		 System.out.println("Press 1 for marriage");
		 System.out.println("Press 2 for Engagement");
		 System.out.println("Press 3 for BirthDay");
		 System.out.println("Press 4 for Anniversary");
		 System.out.println("Press 5 for BabyShower");
		 System.out.println("Press 6 for Reunion");
		 System.out.println("Press 7 for NamingCeremony");
		 System.out.println("Press 8 for bachelorParty");
		 
		 int value = sc.nextInt();
		 switch(value) {
			 case 1 : ce.setEventType(EventType.Marriage);
			 		  break;
			 case 2 : ce.setEventType(EventType.Engagement);
			          break;
			 case 3 : ce.setEventType(EventType.BirthDay);
			          break;
			 case 4 : ce.setEventType(EventType.Anniversary);
			          break;
			 case 5 : ce.setEventType(EventType.BabyShower);
			          break;
			 case 6 : ce.setEventType(EventType.Reunion);
			          break;
			 case 7 : ce.setEventType(EventType.NamingCeremony);
			          break;
			 case 8 : ce.setEventType(EventType.bachelorParty);
			          break;        
			 default : System.out.println("Invalid Number");
			 		   break;	          
		 }
		 return cedao.saveClientEvent(createClientEvent());
	}
	
	public ClientEvent AddEventService()
	{
		 Client clientEvent = clientLogin();
		 List<ClientService> clientServices = new ArrayList<ClientService>();
		 
		 
		 
		 
		 		
		 
		 
		 
		return null; 
	}
	
}