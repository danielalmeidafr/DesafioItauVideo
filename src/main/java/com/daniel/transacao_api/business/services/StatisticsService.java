package com.daniel.transacao_api.business.services;

import com.daniel.transacao_api.controller.dtos.StatisticsResponseDTO;
import com.daniel.transacao_api.controller.dtos.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    public final TransactionService transactionService;

    public StatisticsResponseDTO calculateStatisticsTransactions(Integer optionSeconds) {

        log.info("Calculando estatisticas com tempo de {}", optionSeconds);
        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(optionSeconds);

        if (transactions.isEmpty()) {
            return new StatisticsResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics transactionsStatistics = transactions.stream()
                .mapToDouble(TransactionRequestDTO::value).summaryStatistics();

        log.info("Estatisticas retornadas.");

        return new StatisticsResponseDTO(transactionsStatistics.getCount(),
                transactionsStatistics.getSum(),
                transactionsStatistics.getAverage(),
                transactionsStatistics.getMin(),
                transactionsStatistics.getMax());

    }

}
