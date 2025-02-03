package com.daniel.transacao_api.controller;

import com.daniel.transacao_api.business.services.TransactionService;
import com.daniel.transacao_api.controller.dtos.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Operation(description = "Endpoint para adicionar transacoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transacao adicionada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos nao atendem requisitos"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> addTransactions(@RequestBody TransactionRequestDTO dto){

        transactionService.addTransaction(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint para deletar transacoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacao deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteTransactions(){

        transactionService.deleteTransactions();

        return ResponseEntity.ok().build();
    }
}
