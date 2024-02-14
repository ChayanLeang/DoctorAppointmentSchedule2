package DoctorAppointmentSchedule2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import DoctorAppointmentSchedule2.Models.Appointment;

public class Main {
	
	private static Scanner input=new Scanner(System.in);
	private static OutputOptionAndSelected outputOptionAndSelected=new OutputOptionAndSelected();
	private static DoctorService doctorService=new DoctorService();
	private static PatientService patientService=new PatientService();
	private static List<Appointment> appointments=new ArrayList<>();
	private static AppointmentService appointmentService=new AppointmentService(patientService,doctorService,appointments);
	public static void main(String[] args) {
		String exit="N";int patientId=0;int doctorId=0;int apId=0;
		do {
			System.out.println("\n			Welcome To Doctor Appointment's Schedule Application\n");
			outputOptionAndSelected.outputOption(List.of("\n			1.Appointment Booking\n","			2.Appointment History\n","			3.Exit\n"));
			switch(outputOptionAndSelected.selectChoice()) {
				case "1":{
					
					System.out.println("\n                        ID                   Name                   PhoneNumber");
					for(var pt : patientService.getPatients()) {
						System.out.printf("                        %-20d %-22s %-26s\n",pt.getId(),pt.getFullName(),pt.getPhoneNumber());
					}
					
					do {
						System.out.print("\n			Enter PatientId : ");
						patientId=input.nextInt();
					}while(patientService.getPatientById(patientId)==null);
					
					System.out.println("\n                        ID                   Name                   Position                   PhoneNumber                   WorkTIme");
					for(var dr : doctorService.getDoctors()) {
						System.out.printf("                        %-20d %-22s %-26s %-29s %s\n",dr.getId(),dr.getFullName(),dr.getPosition(),dr.getPhoneNumber(),dr.getWorkTime());
					}
					
					do {
						System.out.print("\n			Enter The DoctorId which that you want to make an appointment with : ");
						doctorId=input.nextInt();
					}while(doctorService.getDoctorById(doctorId)==null);
					
					System.out.println("\n                        ID               Availability Appointment's Date");
					for(var de : doctorService.getAvailabilities(doctorId)) {
						System.out.printf("                        %-17d%s\n",de.getId(),de.getDate());
					}
					
					do {
						System.out.print("\n			Enter Appointment Date Id : ");
						apId=input.nextInt();
					}while(doctorService.getAvailabilityById(doctorId, apId)==null);
					
					appointments.add(new Appointment(appointments.size()+1,doctorId,patientId,doctorService.getAvailabilityById(doctorId, apId).getDate(),false));

					break;
				}
				
				case "2":{
					accessAppointmentHistory();
					break;
				}
				
				case "3":{
					exit="Y";
					System.out.print("\n			Exist Program!\n");
					break;
				}
				
				default:System.out.print("\n			Our Option has only 1-3! Please Choose Again\n");
			}
		}while(exit=="N");
	}
	
	private static void accessAppointmentHistory() {
		String exit="N";int patientId=0;int doctorId=0;
		do {
			outputOptionAndSelected.outputOption(List.of("\n			1.Patient Appointment History\n","			2.Doctor Appointment History\n","			3.Exit\n"));
			switch(outputOptionAndSelected.selectChoice()) {
			case "1":{
				
				do {
					System.out.print("\n			Enter PatientId That You Want To See Their Appointment History : ");
					patientId=input.nextInt();
				}while(patientService.getPatientById(patientId)==null);
				
				System.out.println("\n                        ID               DoctorName               PhoneNumber               Appointment_Date               IsCancel");
				for(var at : appointmentService.getPatientAppointmentHistory(patientId)) {
					System.out.printf("                        %-16d %-24s %-25s %-31s %s\n",at.getId(),at.getDoctorName(),at.getDoctorPhoneNumber(),at.getDate(),at.getIsCancel());
				}
				
				System.out.print("\n			Do you want to cancel appointment ? (Y/N) : ");
				String res=input.next();
				if(res.equals("Y") || res.equals("y")) {
					System.out.print("\n			Enter AppointmentId that you want ot cancel : ");
					int apId=input.nextInt();
					appointmentService.cancelAppointment(apId);
				}
				break;
			}
			case "2":{
				
				do {
					System.out.print("\n			Enter DoctorId That You Want To See Their Appointment History : ");
					doctorId=input.nextInt();
				}while(doctorService.getDoctorById(doctorId)==null);
				
				System.out.println("\n                        ID               PatientName               PhoneNumber               Appointment_Date               IsCancel");
				for(var at : appointmentService.getDoctorAppointment(doctorId)) {
					System.out.printf("                        %-16d %-25s %-26s %-31s %s\n",at.getId(),at.getPatientName(),at.getPatientPhoneNumber(),at.getDate(),at.getIsCancel());
				}
				break;
			}
			case "3":{
				exit="Y";
				break;
			}
		}
		}while(exit=="N");
	}
}
