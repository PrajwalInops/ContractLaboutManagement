package com.inops.visitorpass.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.inops.visitorpass.entity.Visitor;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service("reportGenerationService")
public class ReportGenerationService {

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

}
