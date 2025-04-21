package com.cflApps.MyAdmin.services;

import com.cflApps.MyAdmin.components.report.ReportProcessor;
import com.cflApps.MyAdmin.dtos.RequestReportDTO;
import com.cflApps.MyAdmin.exceptions.MyAdminServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportService {

    private final Map<String, ReportProcessor> processorsMap;


    @Autowired
    public ReportService(Map<String, ReportProcessor> processorsMap) {
        this.processorsMap = processorsMap;
    }

    /**
     * Method used to generate a report.
     * The reportType is the service's name that implement ReportProcessor interface.
     * @param requestDto
     * @param reportType
     * @return byt[]
     */
    public byte [] generateReport(RequestReportDTO requestDto, String reportType){
        ReportProcessor reportProcessor = processorsMap.get(reportType);
        if(reportProcessor == null){
            throw new MyAdminServiceException(String.format("Inválid Report type: %s", reportType));
        }

        return reportProcessor.generateFile(requestDto);
    }
}
