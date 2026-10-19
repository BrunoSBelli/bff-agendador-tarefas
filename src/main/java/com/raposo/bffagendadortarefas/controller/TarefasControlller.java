package com.raposo.bffagendadortarefas.controller;



import com.raposo.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.raposo.bffagendadortarefas.business.DTO.in.TarefasDTORequest;
import com.raposo.bffagendadortarefas.business.TarefasService;
import com.raposo.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.raposo.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro e alteração de tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasControlller {


    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")

    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }


    @GetMapping("/eventos")
    @PostMapping
    @Operation(summary = "Buscar lista de tarefas por Período", description = "Busca lista de tarefas cadastradas por um período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                                                  @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal,token));
    }

    @GetMapping
    @Operation(summary = "Buscar lista de tarefas por email de Usuário", description = "Busca lista de tarefas cadastradas por email de Usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deletar tarefa por ID", description = "Deleta tarefas por email de ID")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token) {

        tarefasService.deletaTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Alterar status de tarefa por ID", description = "Altera status das tarefas por email de ID")
    @ApiResponse(responseCode = "200", description = "Status de tarefa alterada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alterarStatusTarefa(@RequestParam("id")String id, @RequestParam("status") StatusNotificacaoEnum status, @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));

    }

    @PutMapping
    @Operation(summary = "Alterar tarefa por ID", description = "Altera dados das tarefas por email de ID")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alterarTarefa(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alteraTarefa(id, dto, token));
    }



}
