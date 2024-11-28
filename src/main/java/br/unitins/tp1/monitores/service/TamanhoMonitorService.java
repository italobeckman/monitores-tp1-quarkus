package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.monitor.TamanhoMonitorRequestDTO;
import br.unitins.tp1.monitores.model.TamanhoMonitor;
import jakarta.validation.Valid;


public interface TamanhoMonitorService {

    TamanhoMonitor findById(Long id);

    List<TamanhoMonitor> findAll();

    TamanhoMonitor create(@Valid TamanhoMonitorRequestDTO dto);

    TamanhoMonitor update(Long id, TamanhoMonitorRequestDTO dto);

    void delete(Long id); 
    
}
