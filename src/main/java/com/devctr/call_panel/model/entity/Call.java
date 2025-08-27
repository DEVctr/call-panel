package com.devctr.call_panel.model.entity;

import java.util.Date;

import com.devctr.call_panel.model.dto.CallDTO;
import com.devctr.call_panel.model.enums.CallStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String counter;
    private CallStatus status;
    private Date creationDate;

    public Call(CallDTO callDTO) {
        this.name = callDTO.getName();
        this.counter = callDTO.getCounter();
        this.status = callDTO.getStatus();
        this.creationDate = new Date();
    }
}