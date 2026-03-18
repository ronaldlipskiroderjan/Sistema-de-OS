package com.assistencia.os.api.dto;

import lombok.Data;

@Data
public class FinalizacaoOSRequest {
    private String laudo;
    private String assinaturaBase64;
}
