package com.cflApps.MyAdmin.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.cflApps.MyAdmin.dtos.RecordDTO;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Record implements Serializable{
	
	private static final long serialVersionUID = 5301098313869652719L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    private String description;

    @Nonnull
    private Float amount;

    @Nonnull
    private LocalDate date;

    //TODO: Remover relacionamento e usar apenas o ID como relacionamento;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Nonnull
    private Integer recordType; // 0-INCOME // 1-ONE_TIME // 2-RECURRING

    private Integer recurringType; //0-N/A //1-Fixed // 2-Installment

    private Integer recurringCount;
    
    private Integer status; // 0 - INACTIVE // 1- ACTIVE

    private LocalDate created;


    public Record(RecordDTO dto, Department department){
        this.id = dto.getId();
        this.description = dto.getDescription();
        this.amount = dto.getAmount();
        this.date = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.department = department;
        this.recordType = dto.getRecordType();
        this.recurringType = dto.getRecurringType();
        this.recurringCount = dto.getRecurringCount();
        this.status = dto.getStatus();
        this.created = LocalDate.parse(dto.getCreated(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


	public void updateValues(RecordDTO dto, Department department) {
        this.description = dto.getDescription();
        this.amount = dto.getAmount();
        this.date = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.department = department;
        this.recordType = dto.getRecordType();
        this.recurringType = dto.getRecurringType();
        this.recurringCount = dto.getRecurringCount();
        this.status = dto.getStatus();
        this.created = LocalDate.parse(dto.getCreated(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
