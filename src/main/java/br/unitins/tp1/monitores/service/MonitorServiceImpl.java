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
    public Monitor findById(Long Id) {
        return monitorRepository.findById(Id);
    }

    @Override
    @Transactional
    public List<Monitor> findByModelo(String modelo) {
        return monitorRepository.findByModelo(modelo);
    }

    @Override
    @Transactional
    public List<Monitor> findByMarca(String marca) {
        return monitorRepository.findByMarca(marca);
    }

    @Override
    @Transactional
    public Monitor update(Long id, MonitorRequestDTO dto) {
        Monitor monitor = monitorRepository.findById(id);

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
        monitorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Monitor create(MonitorRequestDTO dto) {
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
        Monitor monitor = monitorRepository.findById(id);
        if (monitor == null)
            throw new ValidationException("idMonitor", "Monitor nao encontrado");

        if (monitor.getListaImagem() == null)
            monitor.setListaImagem(new ArrayList<>());

        monitor.getListaImagem().add(nomeImagem);
        return monitor;
    }
    
}
