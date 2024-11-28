package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.monitor.TamanhoMonitorRequestDTO;
import br.unitins.tp1.monitores.model.TamanhoMonitor;
import br.unitins.tp1.monitores.repository.TamanhoMonitorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class TamanhoMonitorServiceImpl implements TamanhoMonitorService {

    @Inject
    public TamanhoMonitorRepository tamanhoMonitorRepository;

    @Override
    public TamanhoMonitor findById(Long id) {
        return tamanhoMonitorRepository.findById(id);
    }

    @Override
    public List<TamanhoMonitor> findAll() {
        return tamanhoMonitorRepository.findAll().list();
    }

    @Override
    @Transactional
    public TamanhoMonitor create(@Valid TamanhoMonitorRequestDTO dto) {
        // verificar se a sigla ja foi utilizada
        TamanhoMonitor tamanhoMonitor = new TamanhoMonitor();

        tamanhoMonitor.setTamanho(dto.tamanho());
        tamanhoMonitor.setLargura(dto.largura());
        tamanhoMonitor.setAltura(dto.altura());
        tamanhoMonitor.setPeso(dto.peso());

        tamanhoMonitorRepository.persist(tamanhoMonitor);
        return tamanhoMonitor;
    }

    @Override
    @Transactional
    public TamanhoMonitor update(Long id, TamanhoMonitorRequestDTO dto) {
        TamanhoMonitor tamanhoMonitor = tamanhoMonitorRepository.findById(id);

        tamanhoMonitor.setTamanho(dto.tamanho());
        tamanhoMonitor.setLargura(dto.largura());
        tamanhoMonitor.setAltura(dto.altura());
        tamanhoMonitor.setPeso(dto.peso());

        return tamanhoMonitor;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tamanhoMonitorRepository.deleteById(id);
    }

}
