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

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.inops.visitorpass.domain.AttendanceRegister;
import com.inops.visitorpass.domain.ContinousAbsenteesim;
import com.inops.visitorpass.domain.Punch;
import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IReport;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service("reportGenerationService")
public class ReportGenerationService {

	private final DataExtractionService attendanceRegister;
	private final DataExtractionService continousAbsenteesim;
	private final DataExtractionService allPunch;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	public ReportGenerationService(DataExtractionService attendanceRegister, DataExtractionService continousAbsenteesim,
			DataExtractionService allPunch) {
		super();
		this.attendanceRegister = attendanceRegister;
		this.continousAbsenteesim = continousAbsenteesim;
		this.allPunch = allPunch;
	}

	public byte[] generateReport(Visitor visitor, String fileName) {
		try {

			List<Visitor> visitors = new ArrayList<Visitor>();
			visitors.add(visitor);
			// empLst.add(emp2);

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator
					+ "demo" + File.separator + "images" + File.separator + "photocam" + File.separator + fileName
					+ ".jpeg";

			// dynamic parameters required for report
			Map<String, Object> empParams = new HashMap<String, Object>();
			empParams.put("CompanyName", "NATIONAL AEROSPACE LABORATORIES");
			empParams.put("VisitDate", new Date());
			empParams.put("VisitorImage", newFileName);
			empParams.put("VisitorId", visitor.getVisitorId());
			empParams.put("BadgeNo", visitor.getBadgeNo());
			empParams.put("visitorData", new JRBeanCollectionDataSource(visitors));

			JasperPrint visitorReport = JasperFillManager.fillReport(JasperCompileManager
					.compileReport(ResourceUtils.getFile("classpath:VisitorPass2.jrxml").getAbsolutePath()) // path of
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
			System.out.println(e);
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

			// final InputStream [b]is[/b] = event.getFile().getInputStream(); // event from
			// the fileuploader-component

			return DefaultStreamedContent.builder().contentType("application/pdf").name("TEST.pdf").stream(() -> stream)
					.build();

		} catch (IOException e) {
			// logger.error(e.getMessage(), e);
			// BeanUtil.addErrorMessage("Download failed", e.getMessage());
		}

		return null;
	}

	public IReport getAttendanceRegister() {
		return (from, to, id) -> {
			try {

				List<AttendanceRegister> attRegister = (List<AttendanceRegister>) attendanceRegister
						.dataExtraction(from, to, id);

				return generateFinalReport(from, to, attRegister, "AttendanceRegister.jrxml", "attendanceRegister");
				// return new ResponseEntity<byte[]>
				// (JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

			} catch (Exception e) {
				// return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
				System.out.println(e);
			}
			return null;
		};

	}

	public IReport getContinousAbsenteesim() {
		return (from, to, id) -> {
			try {

				List<ContinousAbsenteesim> continuoursAbsentees = (List<ContinousAbsenteesim>) continousAbsenteesim
						.dataExtraction(from, to, id);

				return generateFinalReport(from, to, continuoursAbsentees, "ContinousAbsenteesim.jrxml",
						"continousAbsenteesim");
				// return new ResponseEntity<byte[]>
				// (JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

			} catch (Exception e) {
				// return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
				System.out.println(e);
			}
			return null;
		};
	}

	public IReport getAllPunches() {
		return (from, to, id) -> {
			try {

				List<Punch> allPunches = (List<Punch>) allPunch.dataExtraction(from, to, id);

				return generateFinalReport(from, to, allPunches, "Allpunches.jrxml", "Allpunches");
				// return new ResponseEntity<byte[]>
				// (JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

			} catch (Exception e) {
				// return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
				System.out.println(e);
			}
			return null;
		};
	}

	private byte[] generateFinalReport(LocalDate from, LocalDate to, Collection<?> beanCollection, String jrxmlFileName,
			String type) throws JRException, FileNotFoundException {
		// dynamic parameters required for report
		Map<String, Object> empParams = new HashMap<String, Object>();
		empParams.put("CompanyName", "NATIONAL AEROSPACE LABORATORIES");
		empParams.put("fromDate", Date.from(from.atStartOfDay(defaultZoneId).toInstant()));
		empParams.put("toDate", Date.from(to.atStartOfDay(defaultZoneId).toInstant()));

		empParams.put(type, new JRBeanCollectionDataSource(beanCollection));

		JasperPrint extractReport = JasperFillManager.fillReport(JasperCompileManager
				.compileReport(ResourceUtils.getFile("classpath:" + jrxmlFileName).getAbsolutePath()) // path
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