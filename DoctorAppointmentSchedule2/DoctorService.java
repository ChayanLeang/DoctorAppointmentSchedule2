package DoctorAppointmentSchedule2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import DoctorAppointmentSchedule2.Models.Availability;
import DoctorAppointmentSchedule2.Models.Doctor;

public class DoctorService {
	
	private List<Doctor> doctors;
	private List<Availability> availabilities;
	public DoctorService() {
		doctors=new ArrayList<>();
		availabilities=new ArrayList<>();
		doctors.add(new Doctor(1,"Chan Dara","dara123","Dentist","097221223","Mon - Fri, 8:00 AM - 11:00 PM"));
		doctors.add(new Doctor(2,"Sok San","san233","Physical","098323444","Mon - Fri, 7:00 AM - 1:00 PM"));
		availabilities.add(new Availability(1,1,"2024-02-10, 8:30 AM - 9:30 AM"));
		availabilities.add(new Availability(2,1,"2024-02-10, 7:00 PM - 8:00 PM"));
		availabilities.add(new Availability(3,2,"2024-02-10, 10:30 AM - 11:30 AM"));
		availabilities.add(new Availability(4,2,"2024-02-10, 10:00 PM - 11:00 PM"));
	}
	
	public List<Doctor> getDoctors(){
		return doctors;
	}
	
	public Doctor getDoctorById(int id) {
		Optional<Doctor> doctor=doctors.stream().filter(dr->dr.getId()==id).findFirst();
		
		if(!doctor.isPresent()) {
			System.out.print("\n			Doctor Not Found! Please Enter Again\n");
			return null;
		}else {
			return doctor.get();
		}	
	}
	
	public List<Availability> getAvailabilities(int doctorId){
		return availabilities.stream().filter(ay->ay.getDoctorId()==doctorId).toList();
	}
	
	public Availability getAvailabilityById(int doctorId,int apId){
		Optional<Availability> availability=availabilities.stream().filter(ay->ay.getDoctorId()==doctorId && ay.getId()==apId).findFirst();
		
		if(!availability.isPresent()) {
			System.out.print("\n			Appointment Not Found! Please Enter Again\n");
			return null;
		}else {
			return availability.get();
		}
	}
}
