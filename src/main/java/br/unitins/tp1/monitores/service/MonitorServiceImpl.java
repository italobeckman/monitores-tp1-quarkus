package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.monitor.MonitorRequestDTO;
import br.unitins.tp1.monitores.model.Monitor;
import br.unitins.tp1.monitores.repository.MonitorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MonitorServiceImpl implements MonitorService {
    @Inject
    public MonitorRepository monitorRepository;


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
        monitor.setMarca(dto.marca());
        monitor.setModelo(dto.modelo());
        monitor.setPreco(dto.preco());
        monitor.setTaxaAtualizacao(dto.taxaAtualizacao());
        monitor.setTempoResposta(dto.tempoResposta());
        monitorRepository.persist(monitor);
        return monitor;
    }

    @Override
    public List<Monitor> findAll() {
        return monitorRepository.listAll();
       // return estadoRepository.findAll().list();
    }
    
}
