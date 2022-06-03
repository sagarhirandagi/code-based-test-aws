package com.zensar.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zensar.model.patient.DiseasesList;
import com.zensar.model.patient.EmployeesList;
import com.zensar.model.patient.Patient;





@RestController
@RequestMapping("/admissions")
public class AdmissionsResource {
	
	@Autowired
	
	private RestTemplate restTemplate;
	
	
	List<Patient> patients = Arrays.asList(				
		new Patient("P1", "Joseph", "Brazil"),
		new Patient("P2", "William", "American"),
		new Patient("P3", "Sanjith", "Indian")
		);
	
	
	@RequestMapping("/patients")
	public List<Patient> getPatients() {
		return patients;
	}
	
	
	@RequestMapping("/patients/{Id}")
	public Patient getPatientById(@PathVariable("Id") String Id) {
		Patient p = patients.stream()
				.filter(patient -> Id.equals(patient.getId()))
				.findAny()
				.orElse(null);
		return p;
	}
	
	

	@RequestMapping("/physicians")
	public EmployeesList getPhysicians() {
		EmployeesList physicians = 
				
				restTemplate.getForObject("http://hr-service/hr/employees", EmployeesList.class);
		return physicians;
	}
	
	
	@RequestMapping("/diseases")
	public DiseasesList getDiseases() {
		DiseasesList diseases = 
				
				restTemplate.getForObject("http://pathology-service/pathology/diseases", DiseasesList.class);
		
		
				return diseases;
	}	
}
