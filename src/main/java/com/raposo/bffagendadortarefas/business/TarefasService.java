package com.raposo.bffagendadortarefas.business;


import com.raposo.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.raposo.bffagendadortarefas.business.DTO.in.TarefasDTORequest;
import com.raposo.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.raposo.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient client;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {

        return client.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {

        return client.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorEmail(String token) {
        return client.buscaListaDeTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        client.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return client.alterarStatusTarefa(status, id, token);
    }

    public TarefasDTOResponse alteraTarefa(String id, TarefasDTORequest dto, String token) {
        return client.alterarTarefa(dto, id, token);
    }

}
