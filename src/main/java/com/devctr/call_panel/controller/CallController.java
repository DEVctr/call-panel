package com.devctr.call_panel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.devctr.call_panel.model.dto.CallDTO;
import com.devctr.call_panel.model.entity.Call;
import com.devctr.call_panel.service.CallService;

@RestController
@RequestMapping("/call")
public class CallController {
    private final CallService callService;
    private final SimpMessagingTemplate simpMessageTemplate;

    public CallController(CallService callService, SimpMessagingTemplate simpMessageTemplate) {
        this.callService = callService;
        this.simpMessageTemplate = simpMessageTemplate;
    }

    @PostMapping
    public Call createCall(@RequestBody CallDTO callDTO) {
        Call newCall = callService.save(callDTO);

        simpMessageTemplate.convertAndSend("/topic/calls", newCall);

        return newCall;
    }
}