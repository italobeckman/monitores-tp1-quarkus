package br.unitins.tp1.monitores.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.tp1.monitores.dto.monitor.MonitorRequestDTO;
import br.unitins.tp1.monitores.model.Monitor;
import br.unitins.tp1.monitores.repository.MonitorRepository;
import br.unitins.tp1.monitores.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MonitorServiceImpl implements MonitorService {
    @Inject
    public MonitorRepository monitorRepository;

    @Inject
    public FabricanteService fabricanteService;

    @Inject
    public TamanhoMonitorService tamanhoMonitorService;

    @Override
    @Transactional
    public Monitor findById(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        Monitor monitor = monitorRepository.findById(id);
        if (monitor == null) {
            throw new ValidationException("id", "Monitor não encontrado.");
        }
        
        return monitor;
    }

    @Override
    @Transactional
    public List<Monitor> findByModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new ValidationException("modelo", "Modelo é obrigatório.");
        }
        return monitorRepository.findByModelo(modelo);    }

    @Override
    @Transactional
    public List<Monitor> findByMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new ValidationException("marca", "Marca é obrigatória.");
        }
        return monitorRepository.findByMarca(marca);    }

    @Override
    @Transactional
    public Monitor update(Long id, MonitorRequestDTO dto) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

       
        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }
        if (dto.marca() == null || dto.marca().trim().isEmpty()) {
            throw new ValidationException("marca", "Marca é obrigatória.");
        }
        if (dto.modelo() == null || dto.modelo().trim().isEmpty()) {
            throw new ValidationException("modelo", "Modelo é obrigatório.");
        }
        if (dto.preco() == null || dto.preco() <= 0) {
            throw new ValidationException("preco", "Preço deve ser maior que zero.");
        }
        if (dto.taxaAtualizacao() == null || dto.taxaAtualizacao().trim().isEmpty()) {
            throw new ValidationException("taxaAtualizacao", "Taxa de atualização deve ser maior que zero.");
        }
        if (dto.tempoResposta() == null || dto.tempoResposta().trim().isEmpty()) {
            throw new ValidationException("tempoResposta", "Tempo de resposta deve ser maior que zero.");
        }
        if (dto.anoLancamento() == null || dto.anoLancamento() <= 0) {
            throw new ValidationException("anoLancamento", "Ano de lançamento deve ser maior que zero.");
        }


        Monitor monitor = monitorRepository.findById(id);
        if (monitor == null) {
            throw new ValidationException("id", "Monitor não encontrado.");
        }
        monitor.setModelo(dto.modelo());
        monitor.setMarca(dto.marca());
        monitor.setPreco(dto.preco());
        monitor.setTaxaAtualizacao(dto.taxaAtualizacao());
        monitor.setTempoResposta(dto.tempoResposta());
        return monitor;

    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        monitorRepository.deleteById(id);

    }

    @Override
    @Transactional
    public Monitor create(MonitorRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        
        if (dto.nome() == null || dto.nome().trim().isEmpty()) {
            throw new ValidationException("nome", "Nome é obrigatório.");
        }
        if (dto.marca() == null || dto.marca().trim().isEmpty()) {
            throw new ValidationException("marca", "Marca é obrigatória.");
        }
        if (dto.modelo() == null || dto.modelo().trim().isEmpty()) {
            throw new ValidationException("modelo", "Modelo é obrigatório.");
        }
        if (dto.preco() == null || dto.preco() <= 0) {
            throw new ValidationException("preco", "Preço deve ser maior que zero.");
        }
        if (dto.taxaAtualizacao() == null || dto.taxaAtualizacao().trim().isEmpty()) {
            throw new ValidationException("taxaAtualizacao", "Taxa de atualização deve ser maior que zero.");
        }
        if (dto.tempoResposta() == null || dto.tempoResposta().trim().isEmpty()) {
            throw new ValidationException("tempoResposta", "Tempo de resposta deve ser maior que zero.");
        }
        if (dto.anoLancamento() == null || dto.anoLancamento() <= 0) {
            throw new ValidationException("anoLancamento", "Ano de lançamento deve ser maior que zero.");
        }



        Monitor monitor = new Monitor();
        monitor.setNome(dto.nome());
        monitor.setMarca(dto.marca());
        monitor.setModelo(dto.modelo());
        monitor.setPreco(dto.preco());
        monitor.setTaxaAtualizacao(dto.taxaAtualizacao());
        monitor.setTempoResposta(dto.tempoResposta());
        monitor.setAnoLancamento(dto.anoLancamento());

        
        monitor.setFabricante(fabricanteService.findById(dto.idFabricante()));
        monitor.setTamanhoMonitor(tamanhoMonitorService.findById(dto.idTamanhoMonitor()));

        monitorRepository.persist(monitor);
        return monitor;
    }

    @Override
    public List<Monitor> findAll() {
        return monitorRepository.listAll();
    }

    @Override
    @Transactional
    public Monitor updateNomeImagem(Long id, String nomeImagem) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        Monitor monitor = monitorRepository.findById(id);
        if (monitor == null)
            throw new ValidationException("idMonitor", "Monitor nao encontrado");

        if (monitor.getListaImagem() == null)
            monitor.setListaImagem(new ArrayList<>());

        monitor.getListaImagem().add(nomeImagem);
        return monitor;
    }
    
}
