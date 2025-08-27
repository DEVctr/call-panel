package com.devctr.call_panel.model.dto;

import com.devctr.call_panel.model.enums.CallStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallDTO {
    private String name;
    private String counter;
    private CallStatus status;
}