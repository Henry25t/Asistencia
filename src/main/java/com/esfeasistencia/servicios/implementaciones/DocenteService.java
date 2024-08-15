package com.esfeasistencia.servicios.implementaciones;

import com.esfeasistencia.modelos.Docente;
import com.esfeasistencia.repositorios.IDocenteRepositorio;
import com.esfeasistencia.servicios.interfaces.IDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService implements IDocenteService {
    @Autowired
    private IDocenteRepositorio docenteRepositorio;

    @Override
    public Page<Docente> buscarTodosPaginados(Pageable pPageable) {
        return docenteRepositorio.findAll(pPageable);
    }

    @Override
    public List<Docente> obtenerTodos() {
        return docenteRepositorio.findAll();
    }

    @Override
    public Optional<Docente> buscarPorId(Integer id) {
        return docenteRepositorio.findById(id);
    }

    @Override
    public Docente createOrEditOne(Docente pDocente) {
        return docenteRepositorio.save(pDocente);
    }

    @Override
    public void eliminarPorId(Integer id) {
        docenteRepositorio.deleteById(id);
    }
}
