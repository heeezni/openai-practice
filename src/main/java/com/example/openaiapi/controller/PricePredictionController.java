package com.example.openaiapi.controller;

import com.example.openaiapi.dto.PricePredictionResponse;
import com.example.openaiapi.service.PricePredictionService;
import io.swagger.v3.oas.annotations.Parameter;
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
            @Parameter(description = "상품명", example = "와인")
            @RequestParam String productName,

            @Parameter(description = "현재 가격", example = "35000")
            @RequestParam double currentPrice,

            @Parameter(description = "최근 가격 추이 (쉼표 구분)", example = "34000,34500,35000")
            @RequestParam String trendData) throws Exception {

        return pricePredictionService.predictPrice(productName, currentPrice, trendData);
    }
}
