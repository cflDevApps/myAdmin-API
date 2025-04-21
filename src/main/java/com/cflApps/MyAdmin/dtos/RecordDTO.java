package com.cflApps.MyAdmin.dtos;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.cflApps.MyAdmin.entities.Record;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecordDTO implements Serializable {

	private static final long serialVersionUID = 8410532094005381157L;

	private Long id;

    private String description;

    private Float amount;

    private String date;

    private Long departmentId;

    private int recordType; // 0-INCOME // 1-ONE_TIME // 2-RECURRING

    private int recurringType; //0-"N/A" //1-FIXED // 2-INSTALLMENT

    private int recurringCount;
    
    private int status; // 0 - INACTIVE // 1- ACTIVE

    private String created;


    public RecordDTO(Record entity){
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.amount = entity.getAmount();
        this.date =entity.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.departmentId = entity.getDepartment().getId();
        this.recordType = entity.getRecordType();
        this.recurringType = entity.getRecurringType();
        this.recurringCount = entity.getRecurringCount();
        this.status = entity.getStatus();
        this.created = entity.getCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RecordDTO recordDTO = (RecordDTO) o;
        return recordType == recordDTO.recordType && recurringType == recordDTO.recurringType && recurringCount == recordDTO.recurringCount && status == recordDTO.status && Objects.equals(id, recordDTO.id) && Objects.equals(description, recordDTO.description) && Objects.equals(amount, recordDTO.amount) && Objects.equals(date, recordDTO.date) && Objects.equals(departmentId, recordDTO.departmentId) && Objects.equals(created, recordDTO.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, amount, date, departmentId, recordType, recurringType, recurringCount, status, created);
    }
}
