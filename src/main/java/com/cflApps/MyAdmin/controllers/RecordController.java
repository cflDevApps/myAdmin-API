package com.cflApps.MyAdmin.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cflApps.MyAdmin.dtos.RecordDTO;
import com.cflApps.MyAdmin.dtos.ResponseReportDTO;
import com.cflApps.MyAdmin.services.RecordService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("record")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateRecords(@RequestBody RecordDTO record){
    	recordService.updateRecord(record);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecordDTO>> saveRecords(@RequestBody List<RecordDTO> records){
        return ResponseEntity.ok(recordService.saveRecords(records));
    }
    
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecordDTO>> getAllRecords(){
    	return ResponseEntity.ok(recordService.getAll());
    }
    
    @GetMapping(value = "/{year}/{month}")
    public ResponseEntity<ResponseReportDTO> getRecordsByMonth(@PathVariable("month") Integer monthId,
    		@PathVariable("year") Integer yearId){
    	return ResponseEntity.ok(recordService.getRecordsByMonth(yearId, (monthId+1)));
    }

    @GetMapping(value = "/{year}")
    public ResponseEntity<ResponseReportDTO> getRecordsByYear(@PathVariable("year") Integer yearId){
    	return ResponseEntity.ok(recordService.getRecordsByYear(yearId));
    }

    @GetMapping
    public ResponseEntity<ResponseReportDTO> gerRecords(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dtInit,
    		@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dtEnd){
    	return ResponseEntity.ok(recordService.getRecordsInRange(dtInit, dtEnd));
    }

    @GetMapping(value = "/recurrents")
    public ResponseEntity<List<RecordDTO>> getRecurrentRecords(){
    	return ResponseEntity.ok(recordService.getRecurrentRecords());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable("id") Long recordId){
		recordService.deleteRecord(recordId);
    	return ResponseEntity.noContent().build();
    }

}
