package com.inops.visitorpass.service.job;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.service.ICompute;

import lombok.RequiredArgsConstructor;

@Service("jobService")
@RequiredArgsConstructor
public class JobServiceImpl implements IJobService {

	private final ICompute computeService;
	private final IJob leaveComputation;

	@Override
	public IJob getJob(String serviceName) {
		IJob jobService = null;
		switch (serviceName) {
		case "Muster":
			jobService = (fromDate , toDate)->{
				computeService.createMusterForAll(fromDate);
			};
			break;
			
		case "Compute":
			jobService = (fromDate , toDate)->{
				computeService.computeAll(fromDate);
			};
			break;
		case "Leave":
			return leaveComputation;
			//break;

		default:
			break;
		}

		return jobService;
	}

}
