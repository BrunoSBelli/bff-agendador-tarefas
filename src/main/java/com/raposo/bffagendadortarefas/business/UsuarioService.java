package com.raposo.bffagendadortarefas.business;

import com.raposo.bffagendadortarefas.business.DTO.out.EnderecoDTOResponse;
import com.raposo.bffagendadortarefas.business.DTO.out.TelefoneDTOResponse;
import com.raposo.bffagendadortarefas.business.DTO.out.UsuarioDTOResponse;
import com.raposo.bffagendadortarefas.business.DTO.in.EnderecoDTORequest;
import com.raposo.bffagendadortarefas.business.DTO.in.LoginDTORequest;
import com.raposo.bffagendadortarefas.business.DTO.in.TelefoneDTORequest;
import com.raposo.bffagendadortarefas.business.DTO.in.UsuarioDTORequest;
import com.raposo.bffagendadortarefas.infrastructure.client.UsuarioClient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;


    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
       return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }


    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
        return client.atualizaDadosUsuario(dto, token);

    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {

       return client.atualizaDadosEndereco(enderecoDTO,idEndereco,token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {

        return client.atualizaDadosTelefone(telefoneDTO, idTelefone, token);

    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
       return client.cadastraEndereco(dto,token);

    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {
       return client.cadastraTelefone(dto, token);

    }


}
