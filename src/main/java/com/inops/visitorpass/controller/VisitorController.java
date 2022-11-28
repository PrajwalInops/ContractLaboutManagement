package com.inops.visitorpass.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;

import org.primefaces.event.CaptureEvent;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.IEmployee;
import com.inops.visitorpass.service.IVisitorService;
import com.inops.visitorpass.service.impl.ReportGenerationService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component("visitorController")
@Scope("session")
public class VisitorController implements Serializable {

	private IVisitorService visitorService;
	private IEmployee employeeService;
	private ReportGenerationService reportGenerationService;

	public VisitorController(IVisitorService visitorService, IEmployee employeeService,
			ReportGenerationService reportGenerationService) {
		super();
		this.visitorService = visitorService;
		this.employeeService = employeeService;
		this.reportGenerationService = reportGenerationService;
	}

	private List<Visitor> visitors;
	private List<Visitor> preApprovedVisitors;
	private List<Visitor> droppedVisitors;
	private Visitor selectedVisitor;
	private List<Employee> employees;

	private String filename;
	private String mobileNo;
	private Date date;
	private String visitorId;
	private String badgeNo;
	private String visitorName;
	private String company;
	private String address;
	private String noOfPersons;
	private String nationality;
	private String purpose;
	private String idProof;
	private String idProofNo;
	private String laptopToBePermitted;
	private String otherMediaItems;
	private String visitingDepartment;
	private String visitingEmployee;
	private String remarks;
	private StreamedContent file;

	@PostConstruct
	public void init() {		
		getAllVisitors();
		getAllPreApprovedVisitors();
		droppedVisitors = new ArrayList<>();
		Optional<List<Employee>> employees = employeeService.findAll();
		setEmployees(employees.get());
	}

	private String getRandomImageName() {
		int i = (int) (Math.random() * 10000000);

		return String.valueOf(i);
	}

	public void oncapture(CaptureEvent captureEvent) {
		filename = getRandomImageName();
		byte[] data = captureEvent.getData();

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
				+ File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".jpeg";

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(newFileName));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}
	}

	public void getVisitor() {
		Optional<Visitor> visitor = Optional.ofNullable(
				visitors.stream().filter(visitors -> visitors.getMobileNo().equals(mobileNo)).findAny().orElse(null));
		if (visitor.isPresent()) {
			setMobileNo(visitor.get().getMobileNo());
			setDate(visitor.get().getDate());
			setVisitorId(visitor.get().getVisitorId());
			setBadgeNo(visitor.get().getBadgeNo());
			setVisitorName(visitor.get().getVisitorName());
			setCompany(visitor.get().getCompany());
			setAddress(visitor.get().getAddress());
			setNoOfPersons(visitor.get().getNoOfPersons());
			setNationality(visitor.get().getNationality());
			setPurpose(visitor.get().getPurpose());
			setIdProof(visitor.get().getIdProof());
			setIdProofNo(visitor.get().getIdProofNo());
			setLaptopToBePermitted(visitor.get().getLaptopToBePermitted());
			setOtherMediaItems(visitor.get().getOtherMediaItems());
			setVisitingDepartment(visitor.get().getVisitingDepartment());
			setVisitingEmployee(visitor.get().getVisitingEmployee());
			setRemarks(visitor.get().getRemarks());
			setFilename(visitor.get().getVisitorPhoto());
		}
	}

	public void save() {
		Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, company, address, noOfPersons,
				nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems, visitingDepartment,
				visitingEmployee, remarks, filename, false);
		visitorService.save(visitor);
		visitors.add(visitor);
		byte[] pass = reportGenerationService.generateReport(visitor, filename);
		fileDownload(pass);
		cleanUp();
		addMessage("Visitor saved successfully!");
	}

	public void update() {
		Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, company, address, noOfPersons,
				nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems, visitingDepartment,
				visitingEmployee, remarks, filename, false);
		byte[] pass = reportGenerationService.generateReport(visitor, filename);
		fileDownload(pass);
		visitorService.update(visitor);
		cleanUp();
		addMessage("Visitor updated successfully!");
	}

	public void delete() {
		visitorService.delete(mobileNo);
		getAllVisitors();
		cleanUp();
		addMessage("Visitor deleted successfully!");
	}

	private void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private void getAllVisitors() {
		Optional<List<Visitor>> visitors = visitorService.findAll();
		setVisitors(visitors.get());
	}
	
	private void getAllPreApprovedVisitors()
	{
		Optional<List<Visitor>> visitors = visitorService.findAllByIsApproved();
		setPreApprovedVisitors(visitors.get());
	}

	public void getDepartment() {
		Employee employee = employees.stream().filter(employees -> employees.getEmployeeId().equals(visitingEmployee))
				.findAny().orElse(null);
		setVisitingDepartment(employee.getDepartment().getDepartmentName());
	}

	public void fileDownload(byte[] buffer) {
		file = DefaultStreamedContent.builder().name("visitorpass.pdf").contentType("application/pdf")
				.stream(() -> new ByteArrayInputStream(buffer))
				// .stream(() ->
				// FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:\\Users\\User\\Prajwal\\source
				// code\\Visitorpass\\src\\main\\webapp\\resources\\demo\\media\\2515655.pdf"))
				.build();
	}

	public void cleanUp() {
		setMobileNo(null);
		setDate(null);
		setVisitorId(null);
		setBadgeNo(null);
		setVisitorName(null);
		setCompany(null);
		setAddress(null);
		setNoOfPersons(null);
		setNationality(null);
		setPurpose(null);
		setIdProof(null);
		setIdProofNo(null);
		setLaptopToBePermitted(null);
		setOtherMediaItems(null);
		setVisitingDepartment(null);
		setVisitingEmployee(null);
		setRemarks(null);
		setFilename(null);

	}
	
	 public void onProductDrop(DragDropEvent<Visitor> vEvent) {
	        Visitor visitor = vEvent.getData();

	        droppedVisitors.add(visitor);
	        preApprovedVisitors.remove(visitor);
	    }

}
