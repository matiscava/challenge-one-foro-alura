package com.alura.foro.infra.validar.statusTopico;

import com.alura.foro.errores.ValidacionRechazada;
import com.alura.foro.modelo.StatusTopico;
import org.springframework.stereotype.Component;

@Component
public class StatusNoExiste{
    public void validar( String statusNuevo){
        boolean statusExists = false;
        for (
        StatusTopico status
                    : StatusTopico.values()){
            if(status.name().equals(statusNuevo)){
                statusExists = true;
                return;
            }
        }
        if (!statusExists){
            throw new ValidacionRechazada("No existe el Estatus de Tópico Seleccionado");
        }
    }
}
