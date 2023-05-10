package com.inops.visitorpass.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.inops.visitorpass.constant.InopsConstant;
import com.inops.visitorpass.domain.AttendanceRegister;
import com.inops.visitorpass.domain.Balance;
import com.inops.visitorpass.domain.Consolidated;
import com.inops.visitorpass.domain.ContinousAbsenteesim;
import com.inops.visitorpass.domain.LWPDetails;
import com.inops.visitorpass.domain.LWPSummary;
import com.inops.visitorpass.domain.LeaveTransactionReport;
import com.inops.visitorpass.domain.LogDeteails;
import com.inops.visitorpass.domain.PhysicalDays;
import com.inops.visitorpass.domain.Punch;
import com.inops.visitorpass.domain.ThreeYears;
import com.inops.visitorpass.entity.Company;
import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.ICompany;
import com.inops.visitorpass.service.IReport;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Log4j2
@Service("reportGenerationService")
@RequiredArgsConstructor
public class ReportGenerationService {

	private final DataExtractionService registery;
	private final DataExtractionService continousAbsenteesim;
	private final DataExtractionService allPunch;
	private final DataExtractionService dailySummary;
	private final DataExtractionService dailyVisitors;
	private final DataExtractionService physicalDays;
	private final DataExtractionService leaveTransactionReportService;
	private final DataExtractionService leaveBalanceReportService;
	private final DataExtractionService consolidatedService;
	private final DataExtractionService logRegisterService, lwpDetailsService, lwpSummaryDetailsService,
			threeYearsAttendanceReport;
	private final ICompany company;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	public byte[] generateVisitorReport(Visitor visitor, String fileName, String divisionName) {
		try {

			List<Visitor> visitors = new ArrayList<Visitor>();
			visitors.add(visitor);
			// empLst.add(emp2);

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator
					+ "demo" + File.separator + "images" + File.separator + "photocam" + File.separator + fileName
					+ ".jpeg";

			Company companyDetails = company.findAll().get().get(0);
			// String newFileName = companyDetails.getVisitorsPhotoPath() + File.separator +
			// fileName + ".jpeg";

			// dynamic parameters required for report
			Map<String, Object> empParams = new HashMap<String, Object>();
			empParams.put("CompanyName", companyDetails.getCompanyName());
			empParams.put("VisitDate", new Date());
			empParams.put("VisitorImage", newFileName);
			empParams.put("VisitorId", visitor.getVisitorId());
			empParams.put("BadgeNo", visitor.getBadgeNo());
			empParams.put("location", divisionName);
			empParams.put("nationality", visitor.getNationality());
			empParams.put("company", visitor.getCompany());
			empParams.put("address", visitor.getAddress());
			empParams.put("VisitorName", visitor.getVisitorName());
			empParams.put("mobileNo", visitor.getMobileNo());
			empParams.put("visitingEmployee", visitor.getVisitingEmployee());
			empParams.put("visitingDepartment", visitor.getVisitingDepartment());
			empParams.put("noOfPersons", visitor.getNoOfPersons());
			empParams.put("purpose", visitor.getPurpose());
			empParams.put("visitorData", new JRBeanCollectionDataSource(visitors));
			empParams.put("otherDetails", new JRBeanCollectionDataSource(visitors));

			JasperPrint visitorReport = JasperFillManager.fillReport(JasperCompileManager
					// .compileReport(ResourceUtils.getFile("classpath:VisitorPass2.jrxml").getAbsolutePath())
					// // path of
					.compileReport(ResourceUtils
							.getFile(companyDetails.getReportsJRXMLFilePath() + File.separator + "VisitorPass4.jrxml")
							.getAbsolutePath()) // path of
			// the
			// jasper
			// report
					, empParams // dynamic parameters
					, new JREmptyDataSource());

			// the report in PDF format
			// JasperExportManager.exportReportToPdfFile(visitorReport, newPdfFileName);

			return JasperExportManager.exportReportToPdf(visitorReport);
			// return new ResponseEntity<byte[]>
			// (JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

		} catch (Exception e) {
			// return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
			log.error("GenerateReport for visitor data exception {}", e);
		}
		return null;

	}

	public byte[] generateReport(Visitor visitor, String fileName, String divisionName) {
		try {

			List<Visitor> visitors = new ArrayList<Visitor>();
			visitors.add(visitor);
			// empLst.add(emp2);

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator
					+ "demo" + File.separator + "images" + File.separator + "photocam" + File.separator + fileName
					+ ".jpeg";

			Company companyDetails = company.findAll().get().get(0);
			// String newFileName = companyDetails.getVisitorsPhotoPath() + File.separator +
			// fileName + ".jpeg";

			// dynamic parameters required for report
			Map<String, Object> empParams = new HashMap<String, Object>();
			empParams.put("CompanyName", companyDetails.getCompanyName());
			empParams.put("VisitDate", new Date());
			empParams.put("VisitorImage", newFileName);
			empParams.put("VisitorId", visitor.getVisitorId());
			empParams.put("BadgeNo", visitor.getBadgeNo());
			empParams.put("location", visitor.getDivision());
			empParams.put("visitorData", new JRBeanCollectionDataSource(visitors));
			empParams.put("otherDetails", new JRBeanCollectionDataSource(visitors));

			JasperPrint visitorReport = JasperFillManager.fillReport(JasperCompileManager
					// .compileReport(ResourceUtils.getFile("classpath:VisitorPass2.jrxml").getAbsolutePath())
					// // path of
					.compileReport(ResourceUtils
							.getFile(companyDetails.getReportsJRXMLFilePath() + File.separator + "VisitorPass2.jrxml")
							.getAbsolutePath()) // path of
			// the
			// jasper
			// report
					, empParams // dynamic parameters
					, new JREmptyDataSource());

			// the report in PDF format
			// JasperExportManager.exportReportToPdfFile(visitorReport, newPdfFileName);

			return JasperExportManager.exportReportToPdf(visitorReport);
			// return new ResponseEntity<byte[]>
			// (JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

		} catch (Exception e) {
			// return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
			log.error("GenerateReport for visitor data exception {}", e);
		}
		return null;

	}

	public StreamedContent downloadFile(File file) {

		if (!file.exists()) {
			// logger.info("File {} does not exist", file.getPath());
			// BeanUtil.addErrorMessage("File does not exist", "");
			return null;
		}

		try {

			InputStream stream = new BufferedInputStream(new FileInputStream(file));

			return DefaultStreamedContent.builder().contentType("application/pdf").name("TEST.pdf").stream(() -> stream)
					.build();

		} catch (IOException e) {
			// BeanUtil.addErrorMessage("Download failed", e.getMessage());
		}

		return null;
	}

	public IReport getRegistery() {
		return (from, to, id, type) -> {
			try {
				String jrxmlFile = null;
				String reportParameter = null;
				switch (type) {
				case InopsConstant.ATTENDANCE_REGISTER:
					jrxmlFile = "AttendanceRegister.jrxml";
					reportParameter = "attendanceRegister";
					break;
				case InopsConstant.LATEIN_REGISTER:
					jrxmlFile = "LateInRegister.jrxml";
					reportParameter = "lateInRegister";
					break;
				case InopsConstant.EARLYOUT_REGISTER:
					jrxmlFile = "EarlyOutRegister.jrxml";
					reportParameter = "earlyOutRegister";
					break;
				case InopsConstant.EXTRAHOURS_REGISTER:
					jrxmlFile = "ExtraHoutsRegister.jrxml";
					reportParameter = "extrahoursRegister";
					break;
				case InopsConstant.ABSENTEESM_REGISTER:
					jrxmlFile = "AbsenteesmRegister.jrxml";
					reportParameter = "absenteesmRegister";
					break;
				case InopsConstant.OVERTIME_REGISTRY:
					jrxmlFile = "OverTime.jrxml";
					reportParameter = "overTimeRegister";
					break;
				case InopsConstant.LEAVE_REGISTER:
					jrxmlFile = "LeaveRegister.jrxml";
					reportParameter = "leaveRegister";
					break;

				default:
					break;
				}
				List<AttendanceRegister> attRegister = (List<AttendanceRegister>) registery.dataExtraction(from, to, id,
						type);

				return generateFinalReport(from, to, attRegister, jrxmlFile, reportParameter);

			} catch (Exception e) {
				log.error("GetRegistery for {} data exception {}", type, e);
			}
			return null;
		};

	}

	public IReport getContinousAbsenteesim() {
		return (from, to, id, type) -> {
			try {

				List<ContinousAbsenteesim> continuoursAbsentees = (List<ContinousAbsenteesim>) continousAbsenteesim
						.dataExtraction(from, to, id, type);

				return generateFinalReport(from, to, continuoursAbsentees, "ContinousAbsenteesim.jrxml",
						"continousAbsenteesim");

			} catch (Exception e) {
				log.error("GetContinousAbsenteesim for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getAllPunches() {
		return (from, to, id, type) -> {
			try {

				List<Punch> allPunches = (List<Punch>) allPunch.dataExtraction(from, to, id, type);

				return generateFinalReport(from, to, allPunches, "Allpunches.jrxml", "allPunches");

			} catch (Exception e) {
				log.error("GetAllPunches for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getDailySummary() {
		return (from, to, id, type) -> {
			try {

				List<Punch> allPunches = (List<Punch>) dailySummary.dataExtraction(from, to, id, type);

				return generateFinalReport(from, to, allPunches, "DailySummary.jrxml", "dailySummary");

			} catch (Exception e) {
				log.error("GetDailySummary for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getDailyVisitors() {
		return (from, to, id, type) -> {
			try {

				List<Visitor> visitorsData = (List<Visitor>) dailyVisitors.dataExtraction(from, to, id, type);

				return generateFinalReport(from, to, visitorsData, "DailyVisitors.jrxml", "dailyVisitor");

			} catch (Exception e) {
				log.error("GetDailyVisitors for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getPhysicalDays() {
		return (from, to, id, type) -> {
			try {

				List<PhysicalDays> visitorsData = (List<PhysicalDays>) physicalDays.dataExtraction(from, to, id, type);

				return generateFinalReport(from, to, visitorsData, "PhysicalDays.jrxml", "physicalDays");

			} catch (Exception e) {
				log.error("getPhysicalDays for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getLeaveTransactions() {
		return (from, to, id, type) -> {
			try {

				List<LeaveTransactionReport> visitorsData = (List<LeaveTransactionReport>) leaveTransactionReportService
						.dataExtraction(from, to, id, type);

				return generateFinalReport(from, to, visitorsData, "LeaveTransaction.jrxml", "leaveTransaction");

			} catch (Exception e) {
				log.error("getLeaveTransactions for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getLeaveBalance() {
		return (from, to, id, type) -> {
			try {

				List<Balance> visitorsData = (List<Balance>) leaveBalanceReportService.dataExtraction(from, to, id,
						type);

				return generateFinalReport(from, to, visitorsData, "LeaveBalance.jrxml", "leaveBalance");

			} catch (Exception e) {
				log.error("getLeaveBalance for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getConsolidated() {
		return (from, to, id, type) -> {
			try {

				List<Consolidated> consolidatedData = (List<Consolidated>) consolidatedService.dataExtraction(from, to,
						id, type);

				return generateFinalReport(from, to, consolidatedData, "ConsolidatedReport.jrxml", "consolidated");

			} catch (Exception e) {
				log.error("getConsolidated for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getLogRegister() {
		return (from, to, id, type) -> {
			try {

				List<LogDeteails> consolidatedData = (List<LogDeteails>) logRegisterService.dataExtraction(from, to, id,
						type);

				return generateFinalReport(from, to, consolidatedData, "LogRegister.jrxml", "logRegister");

			} catch (Exception e) {
				log.error("getLogRegister for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getLWPDetails() {
		return (from, to, id, type) -> {
			try {

				List<LWPDetails> LWPDetails = (List<LWPDetails>) lwpDetailsService.dataExtraction(from, to, id, type);

				return generateFinalReport(from, to, LWPDetails, "LwpDetails.jrxml", "lwpDetails");

			} catch (Exception e) {
				log.error("getLWPDetails for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getLWPSummaryDetails() {
		return (from, to, id, type) -> {
			try {

				List<LWPSummary> LWPDetails = (List<LWPSummary>) lwpSummaryDetailsService.dataExtraction(from, to, id,
						type);

				return generateFinalReport(from, to, LWPDetails, "LwpSummary.jrxml", "lwpSummary");

			} catch (Exception e) {
				log.error("getLWPSummaryDetails for {} data exception {}", type, e);
			}
			return null;
		};
	}

	public IReport getThreeYearsAttendance() {
		return (from, to, id, type) -> {
			try {

				List<ThreeYears> threeYearsAttendance = (List<ThreeYears>) threeYearsAttendanceReport
						.dataExtraction(from, to, id, type);

				Map<String, Object> empParams = new HashMap<String, Object>();

				empParams.put("y1", from.getYear());
				empParams.put("y2", from.getYear() + 1);
				empParams.put("y3", from.getYear() + 2);

				return generateFinalReport(from, to, threeYearsAttendance, "ThreeYearsAttendance.jrxml",
						"threeYearsAttendance", empParams);

			} catch (Exception e) {
				log.error("getThreeYearsAttendance for {} data exception {}", type, e);
			}
			return null;
		};
	}

	private byte[] generateFinalReport(LocalDate from, LocalDate to, Collection<?> beanCollection, String jrxmlFileName,
			String type) throws JRException, FileNotFoundException {
		// dynamic parameters required for report
		Company companyDetails = company.findAll().get().get(0);
		Map<String, Object> empParams = new HashMap<String, Object>();
		empParams.put("CompanyName", companyDetails.getCompanyName());
		empParams.put("fromDate", Date.from(from.atStartOfDay(defaultZoneId).toInstant()));
		empParams.put("toDate", Date.from(to.atStartOfDay(defaultZoneId).toInstant()));

		empParams.put(type, new JRBeanCollectionDataSource(beanCollection));

		JasperPrint extractReport = JasperFillManager.fillReport(JasperCompileManager
				// .compileReport(ResourceUtils.getFile("classpath:" +
				// jrxmlFileName).getAbsolutePath()) // path
				.compileReport(
						ResourceUtils.getFile(companyDetails.getReportsJRXMLFilePath() + File.separator + jrxmlFileName)
								.getAbsolutePath()) // path
		// of
		// the
		// jasper
		// report
				, empParams // dynamic parameters
				, new JREmptyDataSource());

		// the report in PDF format
		// JasperExportManager.exportReportToPdfFile(visitorReport, newPdfFileName);

		return JasperExportManager.exportReportToPdf(extractReport);
	}

	private byte[] generateFinalReport(LocalDate from, LocalDate to, Collection<?> beanCollection, String jrxmlFileName,
			String type, Map<String, Object> empParams) throws JRException, FileNotFoundException {
		// dynamic parameters required for report
		Company companyDetails = company.findAll().get().get(0);
		// Map<String, Object> empParams = new HashMap<String, Object>();
		empParams.put("CompanyName", companyDetails.getCompanyName());
		empParams.put("fromDate", Date.from(from.atStartOfDay(defaultZoneId).toInstant()));
		empParams.put("toDate", Date.from(to.atStartOfDay(defaultZoneId).toInstant()));

		empParams.put(type, new JRBeanCollectionDataSource(beanCollection));

		JasperPrint extractReport = JasperFillManager.fillReport(JasperCompileManager
				// .compileReport(ResourceUtils.getFile("classpath:" +
				// jrxmlFileName).getAbsolutePath()) // path
				.compileReport(
						ResourceUtils.getFile(companyDetails.getReportsJRXMLFilePath() + File.separator + jrxmlFileName)
								.getAbsolutePath()) // path
		// of
		// the
		// jasper
		// report
				, empParams // dynamic parameters
				, new JREmptyDataSource());

		// the report in PDF format
		// JasperExportManager.exportReportToPdfFile(visitorReport, newPdfFileName);

		return JasperExportManager.exportReportToPdf(extractReport);
	}

}
