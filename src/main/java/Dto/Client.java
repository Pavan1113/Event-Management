package Dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	private String clientName;
	private long clientContact;
	private String clientPlace;
	private String clientEmail;
	private String clientPassword;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientEvent> events;
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public long getClientContact() {
		return clientContact;
	}
	public void setClientContact(long clientContact) {
		this.clientContact = clientContact;
	}
	public String getClientPlace() {
		return clientPlace;
	}
	public void setClientPlace(String clientPlace) {
		this.clientPlace = clientPlace;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	public List<ClientEvent> getEvents() {
		return events;
	}
	public void setEvents(List<ClientEvent> events) {
		this.events = events;
	}
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", clientContact=" + clientContact
				+ ", clientPlace=" + clientPlace + ", clientEmail=" + clientEmail + ", clientPassword=" + clientPassword
				+ ", events=" + events + "]";
	}
	
	
	
}