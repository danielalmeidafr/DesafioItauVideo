package com.daniel.transacao_api.business.services;

import com.daniel.transacao_api.controller.dtos.TransactionRequestDTO;
import com.daniel.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final List<TransactionRequestDTO> transactionList = new ArrayList<>();

    public void addTransaction(TransactionRequestDTO dto){

        log.info("Processamento de gravar transações " + dto);

        if (dto.dateTime().isAfter(OffsetDateTime.now())){
            log.error("Data e hora posterior a atual");
            throw new UnprocessableEntity("Data e hora maiores que atuais.");
        }

        if (dto.value() < 0){
            log.error("Valor menor que 0");
            throw new UnprocessableEntity("Valor nao pode ser menor que 0");
        }


        transactionList.add(dto);
        log.info("Transacoes adicionadas");

    }

    public void deleteTransactions(){

        log.info("Processamento de deletar transações");

        transactionList.clear();

        log.info("Transacoes deletadas");
    }

    public List<TransactionRequestDTO> searchTransactions(Integer optionSeconds){

        log.info("Processamento de buscar transações");

        OffsetDateTime dateTime = OffsetDateTime.now().minusSeconds(optionSeconds);

        log.info("Retorno de transações");
        return transactionList.stream()
                .filter(t -> t.dateTime()
                        .isAfter(dateTime)).toList();
    }
}
