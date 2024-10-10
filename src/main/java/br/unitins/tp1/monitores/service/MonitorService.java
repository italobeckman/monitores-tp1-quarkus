package br.unitins.tp1.monitores.service;

import br.unitins.tp1.monitores.dto.monitor.MonitorRequestDTO;
import br.unitins.tp1.monitores.model.Monitor;
import java.util.List;

public interface MonitorService {

    List<Monitor> findAll();

    Monitor findById(Long Id);

    List<Monitor> findByModelo(String modelo);
    List<Monitor> findByMarca(String marca);

    Monitor update(Long id, MonitorRequestDTO dto);

    void delete(Long id);

    Monitor create(MonitorRequestDTO dto);
}
