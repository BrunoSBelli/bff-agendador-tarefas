package com.raposo.bffagendadortarefas.business;


import com.raposo.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.raposo.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient client;

    public void enviaEmail(TarefasDTOResponse dto) {

        client.enviarEmail(dto);
    }

}
