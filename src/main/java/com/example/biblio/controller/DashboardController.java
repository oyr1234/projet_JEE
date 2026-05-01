package com.example.biblio.controller;

import com.example.biblio.dto.DashboardResponse;
import com.example.biblio.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public DashboardResponse getStats() {
        return service.getDashboardStats();
    }
}