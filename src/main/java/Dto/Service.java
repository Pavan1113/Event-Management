package Dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceId;
	private String serviceName;
	private double ServiceCostPerDay;
	private int serviceNoOfDays;
	private double serviceCostPerPerson;
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public double getServiceCostPerDay() {
		return ServiceCostPerDay;
	}
	public void setServiceCostPerDay(double serviceCostPerDay) {
		ServiceCostPerDay = serviceCostPerDay;
	}
	public int getServiceNoOfDays() {
		return serviceNoOfDays;
	}
	public void setServiceNoOfDays(int serviceNoOfDays) {
		this.serviceNoOfDays = serviceNoOfDays;
	}
	public double getServiceCostPerPerson() {
		return serviceCostPerPerson;
	}
	public void setServiceCostPerPerson(double serviceCostPerPerson) {
		this.serviceCostPerPerson = serviceCostPerPerson;
	}
	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceName=" + serviceName + ", ServiceCostPerDay="
				+ ServiceCostPerDay + ", serviceNoOfDays=" + serviceNoOfDays + ", serviceCostPerPerson="
				+ serviceCostPerPerson + "]";
	}
	
	
	
	
}
