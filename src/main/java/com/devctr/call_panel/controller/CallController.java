package com.devctr.call_panel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devctr.call_panel.model.dto.CallDTO;
import com.devctr.call_panel.model.entity.Call;
import com.devctr.call_panel.service.CallService;

@RestController
@RequestMapping("/call")
public class CallController {
    private final CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @PostMapping
    public Call createCall(@RequestBody CallDTO callDTO) {
        return callService.save(callDTO);
    }
}