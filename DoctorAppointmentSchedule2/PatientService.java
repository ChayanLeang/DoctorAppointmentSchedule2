package DoctorAppointmentSchedule2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import DoctorAppointmentSchedule2.Models.Patient;

public class PatientService {
	
	private List<Patient> patients;
	public PatientService() {
		patients=new ArrayList<>();
		patients.add(new Patient(1,"Lim Visal","visal233","09872211"));
	}
	
	public List<Patient> getPatients() {
		return patients;
	}
	
	public Patient getPatientById(int id) {
		Optional<Patient> patient=patients.stream().filter(pt->pt.getId()==id).findFirst();
		
		if(!patient.isPresent()) {
			System.out.print("\n			Patient Not Found! Please Enter Again\n");
			return null;
		}else {
			return patient.get();
		}
	}
}
