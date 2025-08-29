package com.devctr.call_panel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import com.devctr.call_panel.model.dto.CallDTO;
import com.devctr.call_panel.model.entity.Attendant;
import com.devctr.call_panel.model.entity.Call;
import com.devctr.call_panel.model.enums.CallStatus;
import com.devctr.call_panel.service.AttendantService;
import com.devctr.call_panel.service.CallService;

@RestController
@RequestMapping("/call")
public class CallController {
    private final CallService callService;
    private final AttendantService attendantService;
    private final SimpMessagingTemplate simpMessageTemplate;

    public CallController(CallService callService,
                          AttendantService attendantService,
                          SimpMessagingTemplate simpMessageTemplate) {
        this.callService = callService;
        this.attendantService = attendantService;
        this.simpMessageTemplate = simpMessageTemplate;
    }

    @PostMapping("/next")
    public Call createCall(@AuthenticationPrincipal UserDetails userDetails,
                           @RequestBody(required = false) CallDTO callDTO) {
        
        Optional<Attendant> attendant = attendantService.getUserByEmail(userDetails.getUsername());

        
        if (callDTO.getCode() == null) {
            int nextCode = callService.count++;
            callDTO.setCode(nextCode);
        }

        if (callDTO.getStatus() != CallStatus.PREFERENCIAL) {
            callDTO.setStatus(CallStatus.NORMAL);
        }

        callDTO.setCounter(attendant.isPresent()? attendant.get().getCounter(): null);

        Call savedCall = callService.save(callDTO);

        simpMessageTemplate.convertAndSend("/topic/calls", savedCall);

        return savedCall;
    }
}