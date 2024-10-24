package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.fornecedor.TelefoneFornecedorRequestDTO;
import br.unitins.tp1.monitores.model.Fornecedor;
import br.unitins.tp1.monitores.model.TelefoneFornecedor;
import br.unitins.tp1.monitores.repository.TelefoneFornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TelefoneFornecedorServiceImpl implements TelefoneFornecedorService {

    @Inject
    public TelefoneFornecedorRepository telefoneFornecedorRepository;

    @Inject
    public FornecedorService fornecedorService;

    @Override
    public TelefoneFornecedor findById(Long id) {
        return telefoneFornecedorRepository.findById(id);
    }

    @Override
    public List<TelefoneFornecedor> findAll() {
        return telefoneFornecedorRepository.findAll().list();
    }

    @Override
    @Transactional
    public TelefoneFornecedor create(TelefoneFornecedorRequestDTO dto) {
        TelefoneFornecedor telefoneFornecedor = new TelefoneFornecedor();
        telefoneFornecedor.setCodigoArea(dto.codigoArea());
        telefoneFornecedor.setNumero(dto.numero());
        Fornecedor fornecedor = fornecedorService.findById(dto.fornecedor());
        fornecedor.getListaTelefone().add(telefoneFornecedor);
        telefoneFornecedorRepository.persist(telefoneFornecedor);
        return telefoneFornecedor;
    }

    @Override
    @Transactional
    public TelefoneFornecedor update(Long id, TelefoneFornecedorRequestDTO dto) {
        TelefoneFornecedor telefoneFornecedor = telefoneFornecedorRepository.findById(id);

        telefoneFornecedor.setCodigoArea(dto.codigoArea());
        telefoneFornecedor.setNumero(dto.numero());

        return telefoneFornecedor;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        telefoneFornecedorRepository.deleteById(id);
    }
    
    @Override
    public TelefoneFornecedor findByNumero(String string) {
        return telefoneFornecedorRepository.findByNumero(string).get(0);
    }
}
