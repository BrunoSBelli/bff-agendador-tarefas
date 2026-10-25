package com.raposo.bffagendadortarefas.infrastructure.client.ClientConfig;

import com.raposo.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import com.raposo.bffagendadortarefas.infrastructure.exceptions.BusinesseException;
import com.raposo.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.raposo.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.raposo.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        String messageError = mensagemErro(response);

        switch (response.status()){
            case 409:
                return new ConflictException("Erro: "+ messageError);

            case 403:
                return new ResourceNotFoundException("Erro: "+ messageError);

            case 401:
                return new UnauthorizedException("Erro: "+ messageError);
            case 400:
                return new IllegalArgumentException("Erro: "+ messageError);
            default:
                return new BusinesseException("Erro: "+ messageError);
        }
    }

    private String mensagemErro(Response response){
        try {
            if (Objects.isNull(response)){
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
