package com.raposo.bffagendadortarefas.infrastructure.client.ClientConfig;

import com.raposo.bffagendadortarefas.infrastructure.exceptions.BusinesseException;
import com.raposo.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.raposo.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.raposo.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 409:
                return new ConflictException("Erro atributo já existente");

            case 403:
                return new ResourceNotFoundException("Erro atributo não encontrado");

            case 401:
                return new UnauthorizedException("Usuário não autorizado");

            default:
                return new BusinesseException("Erro de servidor");
        }
    }
}
