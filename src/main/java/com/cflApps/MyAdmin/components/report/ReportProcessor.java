package com.cflApps.MyAdmin.components.report;

import com.cflApps.MyAdmin.dtos.RequestReportDTO;

public interface ReportProcessor {
	
	public byte[] generateFile(RequestReportDTO requestDto);

}
