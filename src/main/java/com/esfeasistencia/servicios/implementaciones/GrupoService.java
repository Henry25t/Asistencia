package com.esfeasistencia.servicios.implementaciones;

import com.esfeasistencia.modelos.Grupo;
import com.esfeasistencia.repositorios.IGrupoRepositorio;
import com.esfeasistencia.servicios.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService implements IGrupoService {
    @Autowired
    private IGrupoRepositorio grupoRepository;

    @Override
    public Page<Grupo> buscarTodosPaginados(Pageable pageable) {
        return grupoRepository.findAll(pageable);
    }

    @Override
    public List<Grupo> obtenerTodos() {
        return grupoRepository.findAll();
    }

    @Override
    public Optional<Grupo> buscarPorId(Integer id) {
        return grupoRepository.findById(id);
    }

    @Override
    public Grupo createOrEditOne(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public void eliminarPorId(Integer id) {
        grupoRepository.deleteById(id);
    }
}