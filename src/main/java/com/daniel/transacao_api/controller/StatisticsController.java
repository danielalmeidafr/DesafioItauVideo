package com.daniel.transacao_api.controller;

import com.daniel.transacao_api.business.services.StatisticsService;
import com.daniel.transacao_api.controller.dtos.StatisticsResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    @Operation(description = "Endpoint de busca de transacoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<StatisticsResponseDTO> searchStatistics(
            @RequestParam(value = "optionSeconds", required = false, defaultValue = "60") Integer optionSeconds) {
        return ResponseEntity.ok(statisticsService.calculateStatisticsTransactions(optionSeconds));
    }
}
