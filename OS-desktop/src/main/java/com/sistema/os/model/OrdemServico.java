package com.sistema.os.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdemServico {

    private String id;
    private String descricao;
    private String status;
    private Double valorTotal;
    private String nomeCliente;

    @JsonProperty("cliente")
    private void unpackNomeCliente(Map<String, Object> clienteJson){
        if (clienteJson != null && clienteJson.containsKey("nome")){
            this.nomeCliente = (String) clienteJson.get("nome");
        }
    }
}
