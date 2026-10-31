package com.raposo.bffagendadortarefas.infrastructure.client;


import com.raposo.bffagendadortarefas.business.DTO.out.EnderecoDTOResponse;
import com.raposo.bffagendadortarefas.business.DTO.out.TelefoneDTOResponse;
import com.raposo.bffagendadortarefas.business.DTO.out.UsuarioDTOResponse;
import com.raposo.bffagendadortarefas.business.DTO.in.EnderecoDTORequest;
import com.raposo.bffagendadortarefas.business.DTO.in.LoginDTORequest;
import com.raposo.bffagendadortarefas.business.DTO.in.TelefoneDTORequest;
import com.raposo.bffagendadortarefas.business.DTO.in.UsuarioDTORequest;
import com.raposo.bffagendadortarefas.business.DTO.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest usuarioDTO);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,  @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto, @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaDadosEndereco(@RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaDadosTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);


    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto, @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto, @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscaDadosCep(@PathVariable("cep") String cep);


}
