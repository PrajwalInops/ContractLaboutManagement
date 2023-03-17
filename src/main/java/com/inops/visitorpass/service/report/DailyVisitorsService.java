package com.inops.visitorpass.service.report;

import java.io.File;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.Employee;
import com.inops.visitorpass.entity.Visitor;
import com.inops.visitorpass.service.DataExtractionService;
import com.inops.visitorpass.service.IVisitorService;

@Service("dailyVisitors")
public class DailyVisitorsService implements DataExtractionService {

	private IVisitorService visitorService;

	public DailyVisitorsService(IVisitorService visitorService) {
		super();
		this.visitorService = visitorService;
	}

	@Override
	public Collection<Visitor> dataExtraction(LocalDate from, LocalDate to, List<Employee> employeeIds, String type) {

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String newFileName = externalContext.getRealPath("") + "resources" + File.separator + "demo"
				+ File.separator + "images" + File.separator + "photocam" + File.separator + "%s.jpeg";

		Optional<List<Visitor>> visitors = visitorService.findAllByDateBetween(from, to);
		if (visitors.isPresent()) {
			return visitors.get().stream().map(visitor -> {
				String fileId = visitor.getVisitorPhoto();
				visitor.setVisitorPhoto(String.format(newFileName, fileId));
				return visitor;
			}).collect(Collectors.toList());
		}
		return null;
	}

}
