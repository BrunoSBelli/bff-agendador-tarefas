package com.raposo.bffagendadortarefas.infrastructure.client;


import com.raposo.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.raposo.bffagendadortarefas.business.DTO.in.TarefasDTORequest;
import com.raposo.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto, @RequestHeader("Authorization") String token);


    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                           @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscaListaDeTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alterarStatusTarefa(@RequestParam("id") String id, @RequestParam("status") StatusNotificacaoEnum status, @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse alterarTarefa(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id, @RequestHeader("Authorization") String token);

}
