package com.raposo.bffagendadortarefas.business;


import com.raposo.bffagendadortarefas.business.DTO.in.LoginDTORequest;
import com.raposo.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.raposo.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {
        String token = login(paraLoginDTOResquest());
        List<TarefasDTOResponse> listaTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(LocalDateTime.now(), LocalDateTime.now().plusHours(1), token);

        listaTarefas.forEach(tarefa -> {

            emailService.enviaEmail(tarefa);
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });
    }


    public String login(LoginDTORequest dto) {
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest paraLoginDTOResquest() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }


}
