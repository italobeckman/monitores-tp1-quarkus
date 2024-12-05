package br.unitins.tp1.monitores.service;

import java.util.List;
import java.util.UUID;

import br.unitins.tp1.monitores.dto.lote.LoteRequestDTO;
import br.unitins.tp1.monitores.model.Lote;
import br.unitins.tp1.monitores.repository.LoteRepository;
import br.unitins.tp1.monitores.validation.ValidationException;
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
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        Lote lote = loteRepository.findById(id);
        if (lote == null) {
            throw new ValidationException("id", "Lote não encontrado.");
        }
        return lote;
    }

    @Override
    public Lote findByIdMonitorLote(Long idMonitor) {
        if (idMonitor == null) {
            throw new ValidationException("idMonitor", "ID do monitor não pode ser nulo.");
        }
        return loteRepository.findByIdMonitorLote(idMonitor);
    }

    @Override
    public Lote findByIdMonitor(Long idMonitor) {
        if (idMonitor == null) {
            throw new ValidationException("idMonitor", "ID do monitor não pode ser nulo.");
        }
        return loteRepository.findByIdMonitor(idMonitor);
    }

    @Override
    public Lote findByCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new ValidationException("codigo", "Código é obrigatório.");
        }
        return loteRepository.findByCodigo(codigo);
    }

    @Override
    public List<Lote> findAll() {
        return loteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Lote create(LoteRequestDTO dto) {
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        if (dto.idMonitor() == null) {
            throw new ValidationException("idMonitor", "ID do monitor é obrigatório.");
        }

        if (dto.data() == null) {
            throw new ValidationException("data", "Data é obrigatória.");
        }

        if (dto.quantidade() == null || dto.quantidade() <= 0) {
            throw new ValidationException("quantidade", "Quantidade deve ser maior que zero.");
        }
        Lote lote = new Lote();
        lote.setMonitor(monitorService.findById(dto.idMonitor()));
        String uuid = UUID.randomUUID().toString();
        String codigoLote = "MONITOR-" + uuid;
        lote.setCodigo(codigoLote);
        lote.setData(dto.data());
        lote.setQuantidade(dto.quantidade());

       
        loteRepository.persist(lote);

        return lote;
    }

    @Override
    @Transactional
    public Lote update(Long id, LoteRequestDTO dto) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        if (dto == null) {
            throw new ValidationException("dto", "DTO não pode ser nulo.");
        }

        if (dto.idMonitor() == null) {
            throw new ValidationException("idMonitor", "ID do monitor é obrigatório.");
        }

        if (dto.data() == null) {
            throw new ValidationException("data", "Data é obrigatória.");
        }

        if (dto.quantidade() == null || dto.quantidade() <= 0) {
            throw new ValidationException("quantidade", "Quantidade deve ser maior que zero.");
        }

        Lote lote = loteRepository.findById(id);
        if (lote == null) {
            throw new ValidationException("id", "Lote não encontrado.");
        }

        lote.setMonitor(monitorService.findById(dto.idMonitor()));
        lote.setData(dto.data());
        lote.setQuantidade(dto.quantidade());

        return lote;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("id", "ID não pode ser nulo.");
        }
        loteRepository.deleteById(id);
    }

}
