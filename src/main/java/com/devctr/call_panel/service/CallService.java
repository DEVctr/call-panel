package com.devctr.call_panel.service;

import org.springframework.stereotype.Service;

import com.devctr.call_panel.model.dto.CallDTO;
import com.devctr.call_panel.model.entity.Call;
import com.devctr.call_panel.repository.CallRepositoy;

@Service
public class CallService {
    private final CallRepositoy callRepository;
    public int count = 0;

    public CallService(CallRepositoy callRepository) {
        this.callRepository = callRepository;
    }
    
    public Call save(CallDTO CallDTO) {
        return callRepository.save(new Call(CallDTO));
    }
}