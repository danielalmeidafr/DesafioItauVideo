package com.daniel.transacao_api.controller.dtos;

import java.util.DoubleSummaryStatistics;

public record StatisticsResponseDTO(Long count,
                                    Double sum,
                                    Double avg,
                                    Double min,
                                    Double max) {
}
