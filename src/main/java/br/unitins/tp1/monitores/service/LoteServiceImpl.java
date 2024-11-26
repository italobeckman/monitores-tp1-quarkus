package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.lote.LoteRequestDTO;
import br.unitins.tp1.monitores.model.Lote;
import br.unitins.tp1.monitores.repository.LoteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LoteServiceImpl implements LoteService {

    @Inject
    public LoteRepository loteRepository;

    @Inject
    public MonitorService monitorService;

    @Override
    public Lote findById(Long id) {
        return loteRepository.findById(id);
    }

    @Override
    public Lote findByIdMonitor(Long idMonitor) {
        return loteRepository.findByIdMonitor(idMonitor);
    }

    @Override
    public Lote findByCodigo(String codigo) {
        return loteRepository.findByCodigo(codigo);
    }

    @Override
    public List<Lote> findAll() {
        return loteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Lote create(LoteRequestDTO dto) {
        // buscando o estado a partir de um id do lote
        Lote lote = new Lote();
        lote.setMonitor(monitorService.findById(dto.idMonitor()));
        lote.setCodigo(dto.codigo());
        lote.setData(dto.data());
        lote.setQuantidade(dto.quantidade());

        //salvando o lote
        loteRepository.persist(lote);
        
        return lote;
    }

    @Override
    @Transactional
    public Lote update(Long id, LoteRequestDTO dto) {
        Lote lote = loteRepository.findById(id);

        lote.setMonitor(monitorService.findById(dto.idMonitor()));
        lote.setCodigo(dto.codigo());
        lote.setData(dto.data());
        lote.setQuantidade(dto.quantidade());

        return lote;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loteRepository.deleteById(id);
    }
    
}
