package com.assistencia.os.api.controller;

import com.assistencia.os.domain.model.OrdemServico;
import com.assistencia.os.domain.model.StatusOrdemServico;
import com.assistencia.os.domain.repository.OrdemServicoRepository;
import com.assistencia.os.domain.service.OrdemServicoService;
import com.assistencia.os.api.dto.FinalizacaoOSRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/ordens-servico")
@RequiredArgsConstructor
public class OrdemServicoController {


    private final OrdemServicoRepository ordemServicoRepository;
    private final OrdemServicoService ordemServicoService;


    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    @PostMapping
    public OrdemServico criar(@RequestBody OrdemServico os) {
        return ordemServicoService.criar(os);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrdemServico>> listarPorStatus(@PathVariable StatusOrdemServico status) {
        List<OrdemServico> ordens = ordemServicoRepository.findByStatus(status);
        return ResponseEntity.ok(ordens);
    }

    @GetMapping("/tecnico/{tecnicoId}")
    public ResponseEntity<List<OrdemServico>> listarPorTecnico(@PathVariable UUID tecnicoId) {
        List<OrdemServico> ordens = ordemServicoRepository.findByTecnicoId(tecnicoId);
        return ResponseEntity.ok(ordens);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizar(@PathVariable UUID id, @RequestBody FinalizacaoOSRequest request) {
        ordemServicoService.finalizar(id, request.getLaudo(), request.getAssinaturaBase64());
        return ResponseEntity.noContent().build();
    }
}