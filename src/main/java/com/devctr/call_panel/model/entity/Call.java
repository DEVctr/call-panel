package com.devctr.call_panel.model.entity;

import java.util.Date;

import com.devctr.call_panel.model.dto.CallDTO;
import com.devctr.call_panel.model.enums.CallStatus;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private String name;

    private Integer code;
    private Integer counter;
    private CallStatus status;
    private Date creationDate;

    public Call(CallDTO callDTO) {
        this.code = callDTO.getCode();
        this.name = callDTO.getName();
        this.counter = callDTO.getCounter();
        this.status = callDTO.getStatus();
        this.creationDate = new Date();
    }
}