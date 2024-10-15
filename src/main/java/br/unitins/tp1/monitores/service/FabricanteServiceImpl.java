package br.unitins.tp1.monitores.service;

import java.util.List;

import br.unitins.tp1.monitores.dto.fabricante.FabricanteRequestDTO;
import br.unitins.tp1.monitores.dto.fabricante.TelefoneFabricanteRequestDTO;
import br.unitins.tp1.monitores.model.Fabricante;
import br.unitins.tp1.monitores.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService {

    @Inject
    public FabricanteRepository fabricanteRepository;

    @Override
    public Fabricante findById(Long id) {
        return fabricanteRepository.findById(id);
    }

    @Override
    public List<Fabricante> findByNome(String nome) {
        return fabricanteRepository.findByNome(nome);
    }

    @Override
    public List<Fabricante> findAll() {
        return fabricanteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Fabricante create(FabricanteRequestDTO dto) {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(dto.nome());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setEmail(dto.email());
        fabricante.setListaTelefone(dto.telefones().stream().map(TelefoneFabricanteRequestDTO::valueOf).toList());

        fabricanteRepository.persist(fabricante);
        return fabricante;
    }

    @Override
    @Transactional
    public Fabricante update(Long id, FabricanteRequestDTO dto) {
        Fabricante fabricante = fabricanteRepository.findById(id);

        fabricante.setNome(dto.nome());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setEmail(dto.email());

        return fabricante;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fabricanteRepository.deleteById(id);
    }
    
}
