package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.model.Fabricante;
import br.unitins.tp1.monitores.model.TelefoneFabricante;
import br.unitins.tp1.monitores.repository.TelefoneFabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TelefoneFabricanteServiceImpl implements TelefoneFabricanteService {

    @Inject
    public TelefoneFabricanteRepository telefoneFabricanteRepository;

    @Inject
    public FabricanteService fabricanteService;

    @Override
    public TelefoneFabricante findById(Long id) {
        return telefoneFabricanteRepository.findById(id);
    }

    @Override
    public List<TelefoneFabricante> findAll() {
        return telefoneFabricanteRepository.findAll().list();
    }

    @Override
    @Transactional
    public TelefoneFabricante create(TelefoneFabricanteRequestDTO dto) {
        TelefoneFabricante telefoneFabricante = new TelefoneFabricante();
        telefoneFabricante.setCodigoArea(dto.codigoArea());
        telefoneFabricante.setNumero(dto.numero());
        Fabricante fabricante = fabricanteService.findById(dto.fabricante());
        fabricante.getListaTelefone().add(telefoneFabricante);
        telefoneFabricanteRepository.persist(telefoneFabricante);
        return telefoneFabricante;
    }

    @Override
    @Transactional
    public TelefoneFabricante update(Long id, TelefoneFabricanteRequestDTO dto) {
        TelefoneFabricante telefoneFabricante = telefoneFabricanteRepository.findById(id);

        telefoneFabricante.setCodigoArea(dto.codigoArea());
        telefoneFabricante.setNumero(dto.numero());

        return telefoneFabricante;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        telefoneFabricanteRepository.deleteById(id);
    }

    @Override
    public TelefoneFabricante findByNumero(String string) {
        return telefoneFabricanteRepository.findByNumero(string).get(0);
    }

    
    
    
}
