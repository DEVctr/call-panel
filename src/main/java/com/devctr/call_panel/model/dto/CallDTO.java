package com.devctr.call_panel.model.dto;

import com.devctr.call_panel.model.enums.CallStatus;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallDTO {
    @Nullable
    private Integer code;
    @Nullable
    private String name;
    
    @Nullable
    private Integer counter;
    private CallStatus status;
}