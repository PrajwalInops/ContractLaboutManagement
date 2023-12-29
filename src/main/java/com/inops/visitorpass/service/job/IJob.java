package com.inops.visitorpass.service.job;

import java.util.Date;

interface IJob {
	
	public void execute(Date from , Date to);
}
