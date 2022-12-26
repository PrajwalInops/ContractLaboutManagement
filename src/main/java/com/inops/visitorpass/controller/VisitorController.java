package com.inops.visitorpass.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.primefaces.event.CaptureEvent;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.inops.visitorpass.entity.Company;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.ICompany;
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

	private final IVisitorService visitorService;
	private final IEmployee employeeService;
	private final ReportGenerationService reportGenerationService;
	private final ICompany company;

	@Autowired
	ApplicationContext ctx;

	public VisitorController(IVisitorService visitorService, IEmployee employeeService,
			ReportGenerationService reportGenerationService, ICompany company) {
		super();
		this.visitorService = visitorService;
		this.employeeService = employeeService;
		this.reportGenerationService = reportGenerationService;
		this.company = company;
	}

	private List<Visitor> visitors;
	private List<Visitor> preApprovedVisitors;
	private List<Visitor> droppedVisitors;
	private Visitor selectedVisitor;
	private List<Visitor> selectedVisitors;
	private List<Employee> employees;


	private String photoPath;
	private StreamedContent visitorPhoto;
	private String filename;
	private String mobileNo;
	private Date date;
	private String visitorId;
	private String badgeNo;
	private String visitorName;
	private String visitorCompany;
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
	ZoneId defaultZoneId = ZoneId.systemDefault();

	@PostConstruct
	public void init() throws UnsupportedEncodingException {
		getAllPreApprovedVisitors();
		droppedVisitors = new ArrayList<>();
		

		getVisitorIdByDate();
		employees = ((Optional<List<Employee>>) ctx.getBean("getEmployees")).get();
		visitors = ((Optional<List<Visitor>>) ctx.getBean("getVisitors")).get();
		setDate(new Date());
		fileDownload(null, "VisitorPass");
		
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
	public List<Visitor> completeVisitors(String mobileNo) {
		return visitors.stream().filter(visitor -> visitor.getMobileNo().contains(mobileNo))
				.collect(Collectors.toList());
	}

	public void getVisitor() {

		Optional<Visitor> visitor = Optional.ofNullable(
				visitors.stream().filter(visitors -> visitors.getMobileNo().equals(mobileNo)).findAny().orElse(null));
		if (visitor.isPresent()) {
			setMobileNo(visitor.get().getMobileNo());
			// setDate(visitor.get().getDate());
			// setVisitorId(visitor.get().getVisitorId());
			setBadgeNo(visitor.get().getBadgeNo());
			setVisitorName(visitor.get().getVisitorName());
			setVisitorCompany(visitor.get().getCompany());
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
		try {
			Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, visitorCompany, address,
					noOfPersons, nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems,
					visitingDepartment, visitingEmployee, remarks, filename, false);
			if (filename == null) {
				addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
						"Please capture the photo for visitor: " + visitorName);
			} else {
				visitorService.save(visitor);
				visitors.add(visitor);
				byte[] pass = reportGenerationService.generateReport(visitor, filename);
				fileDownload(pass, mobileNo);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
						"Visitorpass generated successfully for: " + visitorName);

				cleanUp();
			}
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
					"Visitor details already exist, update the details : " + visitorName);
		}

	}

	public void savePreApproval() {
		try {
			Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, visitorCompany, address,
					noOfPersons, nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems,
					visitingDepartment, visitingEmployee, remarks, filename, true);

			visitorService.save(visitor);
			visitors.add(visitor);

			addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
					"Visitor pre-approval pass generated successfully for: " + visitorName);
			cleanUp();
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
					"Visitor details already exist, update the details : " + visitorName);
		}
	}

	public void updatePreApproval() {
		Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, visitorCompany, address,
				noOfPersons, nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems,
				visitingDepartment, visitingEmployee, remarks, filename, false);
		visitorService.update(visitor);
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
				"Visitor pre-approval pass updated successfully for: " + visitorName);
		cleanUp();
	}

	public void update() {
		Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, visitorCompany, address,
				noOfPersons, nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems,
				visitingDepartment, visitingEmployee, remarks, filename, false);
		byte[] pass = reportGenerationService.generateReport(visitor, filename);
		fileDownload(pass, mobileNo);
		visitorService.update(visitor);
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Visitorpass updated successfully for: " + visitorName);
		cleanUp();
	}

	public void deletePreApproval() {
		visitorService.delete(mobileNo);
		getAllVisitors();
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
				"Visitor pre-approval pass deleted successfully for: " + visitorName);
		cleanUp();
	}

	public void delete() {
		visitorService.delete(mobileNo);
		getAllVisitors();
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Visitorpass deleted successfully for: " + visitorName);
		cleanUp();
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	private void getAllVisitors() {
		Optional<List<Visitor>> visitors = visitorService.findAll();
		setVisitors(visitors.get());
	}

	private void getAllPreApprovedVisitors() {
		Optional<List<Visitor>> visitors = visitorService.findAllByIsApproved();
		setPreApprovedVisitors(visitors.get());
	}

	public void getDepartment() {
		Employee employee = employees.stream().filter(employees -> employees.getEmployeeId().equals(visitingEmployee))
				.findAny().orElse(null);
		setVisitingDepartment(employee.getDepartment().getDepartmentName());
	}

	public void fileDownload(byte[] buffer, String mobileno) {

		file = DefaultStreamedContent.builder().name(mobileno + ".pdf").contentType("application/pdf")
				.stream(() -> new ByteArrayInputStream(buffer))
				// .stream(() ->
				// FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:\\Users\\User\\Prajwal\\source
				// code\\Visitorpass\\src\\main\\webapp\\resources\\demo\\media\\2515655.pdf"))
				.build();
	}

	public void cleanUp() {
		setMobileNo(null);
		getVisitorIdByDate();
		setBadgeNo(null);
		setVisitorName(null);
		setVisitorCompany(null);
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

	private void getVisitorIdByDate() {

		Date getdateCount = Date.from(LocalDate.now().minusDays(1).atStartOfDay(defaultZoneId).toInstant());
		new java.sql.Date(getdateCount.getTime());
		visitorId = String.valueOf(visitorService.countByDate(new java.sql.Date(getdateCount.getTime())) + 1);

	}

}
