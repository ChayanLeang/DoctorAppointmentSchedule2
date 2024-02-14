package DoctorAppointmentSchedule2;

import java.util.List;

import DoctorAppointmentSchedule2.Models.Appointment;
import DoctorAppointmentSchedule2.Models.AppointmentHistory;

public class AppointmentService {
	
	private List<Appointment> appointments;
	private PatientService patientService;
	private DoctorService doctorService;
	
	public AppointmentService(PatientService patientService,DoctorService doctorService,List<Appointment> appointments) {
		this.doctorService=doctorService;
		this.patientService=patientService;
		this.appointments=appointments;
	}
	
	public List<AppointmentHistory> getDoctorAppointment(int doctorId){
		return appointments.stream().filter(at->at.getDoctorId()==doctorId).map(at->new AppointmentHistory(at.getId(),patientService
						   .getPatientById(at.getPatientId()).getFullName(),patientService.getPatientById(at.getPatientId()).getPhoneNumber()
						   ,"","",at.getDate(),at.getIsCancel())).toList();
	}
	
	public List<AppointmentHistory> getPatientAppointmentHistory(int patientId) {
		return appointments.stream().filter(at->at.getPatientId()==patientId).map(at->new AppointmentHistory(at.getId(),
					patientService.getPatientById(patientId).getFullName(),"",doctorService.getDoctorById(at.getDoctorId()).getFullName(),doctorService
			  	   .getDoctorById(at.getDoctorId()).getPhoneNumber(),at.getDate(),at.getIsCancel())).toList();
	}
	
	public void cancelAppointment(int id) {
		Appointment appointment = appointments.stream().filter(at->at.getId()==id).findFirst().get();
		appointment.setIsCancel(true);
		appointments.set(appointments.indexOf(appointment), appointment);
		System.out.print("\n			Success\n");
	}
}
