package com.example.openaiapi.controller;

import com.example.openaiapi.dto.PricePredictionResponse;
import com.example.openaiapi.service.PricePredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service에서 DTO 리턴받아 그대로 JSON으로 응답
 * */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/predict")
public class PricePredictionController {
    private final PricePredictionService pricePredictionService;

    @GetMapping
    public PricePredictionResponse predict(
            @RequestParam String productName,
            @RequestParam double currentPrice,
            @RequestParam String trendData) throws Exception {

        return pricePredictionService.predictPrice(productName, currentPrice, trendData);
    }
}
