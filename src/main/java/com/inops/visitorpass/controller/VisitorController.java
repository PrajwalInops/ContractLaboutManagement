
package com.inops.visitorpass.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.inops.visitorpass.constant.InopsConstant;
import com.inops.visitorpass.domain.CardDetails;
import com.inops.visitorpass.entity.Cards;
import com.inops.visitorpass.entity.Division;
import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.ReaderIpAddress;
import com.inops.visitorpass.entity.User;
import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.ICard;
import com.inops.visitorpass.service.ICompany;
import com.inops.visitorpass.service.IDivision;
import com.inops.visitorpass.service.IEmployee;
import com.inops.visitorpass.service.IReaderIpAddress;
import com.inops.visitorpass.service.IVisitorService;
import com.inops.visitorpass.service.impl.ReportGenerationService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
@Component("visitorController")
@Scope("session")
public class VisitorController implements Serializable {

	private final IVisitorService visitorService;
	private final IEmployee employeeService;
	private final ReportGenerationService reportGenerationService;
	private final ICompany company;
	private final IReaderIpAddress readerIpAddress;
	private final ICard card;
	private final IDivision division;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ApplicationContext ctx;

	public VisitorController(IVisitorService visitorService, IEmployee employeeService,
			ReportGenerationService reportGenerationService, ICompany company, IReaderIpAddress readerIpAddress,
			ICard card, IDivision division) {
		super();
		this.visitorService = visitorService;
		this.employeeService = employeeService;
		this.reportGenerationService = reportGenerationService;
		this.company = company;
		this.readerIpAddress = readerIpAddress;
		this.card = card;
		this.division = division;
	}

	private List<Visitor> visitors;
	private List<Visitor> preApprovedVisitors;
	private List<Visitor> droppedVisitors;
	private Visitor selectedVisitor;
	private List<Visitor> selectedVisitors;
	private List<Employee> employees;
	private List<ReaderIpAddress> readerIpAddresses;
	private List<Cards> badgeNumbers;
	private List<Division> divisions;

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
	private long divisionId;
	private StreamedContent file;
	ZoneId defaultZoneId = ZoneId.systemDefault();
	private User user;

	DateFormat fromDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	DateFormat toDateFormat = new SimpleDateFormat("dd/MM/yyyy 18:00");

	@PostConstruct
	public void init() throws UnsupportedEncodingException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (User) auth.getPrincipal();
		getAllPreApprovedVisitors();
		droppedVisitors = new ArrayList<>();

		getVisitorIdByDate();
		employees = ((Optional<List<Employee>>) ctx.getBean("getEmployees")).get();
		visitors = ((Optional<List<Visitor>>) ctx.getBean("getVisitors")).get();
		divisions = division.findAll().get();
		readerIpAddresses = readerIpAddress.findAll().get();
		setDate(new Date());
		fileDownload(null, "VisitorPass");

		if (user.getRole().getValue().equals("User")) {
			employees = employees.stream().filter(emp -> emp.getEmployeeId().equals(user.getEmployee().getEmployeeId()))
					.collect(Collectors.toList());
		}
		getCards();

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
		if (selectedVisitor != null) {
			selectedVisitor.setVisitorPhoto(filename);
		}
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
			log.info("Fetching the visitor data {}", visitor);
		}
	}

	public void save() {
		try {
			Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, visitorCompany, address,
					noOfPersons, nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems,
					visitingDepartment, visitingEmployee, remarks, filename, false, InopsConstant.IN_PASS,
					user.getEmployee().getDivision().getDivisionId());
			if (filename == null) {
				addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
						"Please capture the photo for visitor: " + visitorName);
			} else {
				visitorService.save(visitor);
				visitors.add(visitor);

				byte[] pass = reportGenerationService.generateVisitorReport(visitor, filename,
						setEmployeeAndDivision(visitor));
				fileDownload(pass, mobileNo);
				addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
						"Visitorpass generated successfully for: " + visitorName);
				getAllPreApprovedVisitors();
				writeCardToDevise();
				getCards();
				cleanUp();
				log.info("saving the visitor data {}", visitor);
			}
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
					"Visitor details already exist, update the details : " + visitorName);
			log.error("exception at the time of saving visitor data {}", e);
		}

	}

	public void savePreApproval() {
		try {
			Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, visitorCompany, address,
					noOfPersons, nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems,
					visitingDepartment, visitingEmployee, remarks, filename, true, InopsConstant.IN_PASS, divisionId);

			visitorService.save(visitor);
			visitors.add(visitor);

			addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
					"Visitor pre-approval pass generated successfully for: " + visitorName);
			cleanUp();
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
					"Visitor details already exist, update the details : " + visitorName);
			log.error("exception at the time of saving PreApproval visitor data {}", e);
		}
	}

	public void updatePreApproval() {
		try {
			Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, visitorCompany, address,
					noOfPersons, nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems,
					visitingDepartment, visitingEmployee, remarks, filename, true, InopsConstant.IN_PASS, divisionId);
			visitorService.update(visitor);
			addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
					"Visitor pre-approval pass updated successfully for: " + visitorName);
			getAllPreApprovedVisitors();
			cleanUp();
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
					"error while updating pre-approval , update the details : " + visitorName);
			log.error("exception at the time of update PreApproval visitor data {}", e);
		}
	}

	public void updateApproval() {

		try {
			Visitor visitor = new Visitor(0, selectedVisitor.getMobileNo(), selectedVisitor.getDate(),
					selectedVisitor.getVisitorId(), selectedVisitor.getBadgeNo(), selectedVisitor.getVisitorName(),
					selectedVisitor.getCompany(), selectedVisitor.getAddress(), selectedVisitor.getNoOfPersons(),
					selectedVisitor.getNationality(), selectedVisitor.getPurpose(), selectedVisitor.getIdProof(),
					selectedVisitor.getIdProofNo(), selectedVisitor.getLaptopToBePermitted(),
					selectedVisitor.getOtherMediaItems(), selectedVisitor.getVisitingDepartment(),
					selectedVisitor.getVisitingEmployee(), selectedVisitor.getRemarks(),
					selectedVisitor.getVisitorPhoto(), false, InopsConstant.IN_PASS, selectedVisitor.getDivision());
			byte[] pass = reportGenerationService.generateVisitorReport(visitor, selectedVisitor.getVisitorPhoto(),
					setEmployeeAndDivision(visitor));
			fileDownload(pass, selectedVisitor.getMobileNo());
			visitorService.update(visitor);
			getAllPreApprovedVisitors();
			addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
					"Visitor pre-approval pass updated successfully for: " + selectedVisitor.getVisitorName());
			writeCardToDevise();
			getCards();
			cleanUp();
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
					"exception at the time of update Approval visitor data, update the details : " + visitorName);
			log.error("exception at the time of update Approval visitor data {}", e);
		}
	}

	public void update() {
		try {
			Visitor visitor = new Visitor(0, mobileNo, date, visitorId, badgeNo, visitorName, visitorCompany, address,
					noOfPersons, nationality, purpose, idProof, idProofNo, laptopToBePermitted, otherMediaItems,
					visitingDepartment, visitingEmployee, remarks, filename, false, InopsConstant.IN_PASS,
					user.getEmployee().getDivision().getDivisionId());
			byte[] pass = reportGenerationService.generateVisitorReport(visitor, filename, setEmployeeAndDivision(visitor));
			fileDownload(pass, mobileNo);
			visitorService.update(visitor);
			addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
					"Visitorpass updated successfully for: " + visitorName);
			//writeCardToDevise();
			getCards();
			cleanUp();
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error Message",
					"exception at the time of update  visitor data, update the details : " + visitorName);
			log.error("exception at the time of update  visitor data {}", e);
		}
	}

	public void deletePreApproval() {
		visitorService.delete(selectedVisitor.getMobileNo());
		getAllVisitors();
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
				"Visitor pre-approval pass deleted successfully for: " + visitorName);
		cleanUp();
	}

	public void deleteApproval() {
		visitorService.delete(selectedVisitor.getMobileNo());
		getPreApprovedVisitors().remove(selectedVisitor);
		getAllPreApprovedVisitors();
		// selectedVisitors.remove(selectedVisitor);
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
				"Visitor pre-approval pass deleted successfully for: " + selectedVisitor.getVisitorName());
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
		Optional<List<Visitor>> visitors = visitorService.findAllByIsApprovedAndDivision(true,
				user.getEmployee().getDivision().getDivisionId());
		setPreApprovedVisitors(visitors.get());
	}

	public void getDepartment() {
		Employee employee = employees.stream().filter(employees -> employees.getEmployeeId().equals(visitingEmployee))
				.findAny().orElse(null);
		setVisitingEmployee(employee.getEmployeeName());
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

	public void updateOutPass(long id) {
		Visitor visitor = visitors.stream().filter(visit -> visit.getId() == id).findAny().orElse(null);
		visitor.setOutOrInPass(InopsConstant.OUT_PASS);
		visitorService.update(visitor);
		addMessage(FacesMessage.SEVERITY_INFO, "Info Message",
				"Visitorpass updated successfully for: " + visitor.getVisitorName());

	}

	private void getCards() {
		List<Cards> cardsList = card.findAllByDivisionId(user.getEmployee().getDivision()).get();
		Date getdateCount = Date.from(LocalDate.now().minusDays(1).atStartOfDay(defaultZoneId).toInstant());
		List<String> badgeNo = visitors.stream().filter(vis -> vis.getDate().after(getdateCount))
				.map(vis -> vis.getBadgeNo()).collect(Collectors.toList());

		badgeNumbers = cardsList.stream().filter(card -> !badgeNo.contains(card.getCardNo()))
				.collect(Collectors.toList());

	}

	private String writeCardToDevise() {
		try {
			CardDetails cardDetails = new CardDetails(badgeNo, visitorName, fromDateFormat.format(new Date()),
					toDateFormat.format(new Date()));
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CardDetails> entity = new HttpEntity<CardDetails>(cardDetails, headers);

			return restTemplate.exchange("http://" + user.getSystemIpAddress() + ":8080/writecarddata", HttpMethod.POST,
					entity, String.class).getBody();

		} catch (Exception e) {
			throw e;
		}
	}

	private String setEmployeeAndDivision(Visitor visitor) {
		Employee employee = employees.stream().filter(emp -> emp.getEmployeeId().equals(visitor.getVisitingEmployee()))
				.findAny().orElse(null);
		visitor.setVisitingEmployee(employee.getEmployeeName());
		visitor.setVisitingDepartment(employee.getDepartment().getDepartmentName());
		return user.getEmployee().getDivision().getDivisionName();
	}

}
