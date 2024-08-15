package com.esfeasistencia.servicios.implementaciones;

import com.esfeasistencia.modelos.DocenteGrupo;
import com.esfeasistencia.repositorios.IDocenteGrupoRespositorio;
import com.esfeasistencia.servicios.interfaces.IDocenteGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteGrupoService implements IDocenteGrupoService {
    @Autowired
    private IDocenteGrupoRespositorio docenteGrupoRespositorio;

    @Override
    public List<DocenteGrupo> obtenerTodos() {
        return docenteGrupoRespositorio.findAll();
    }

    @Override
    public Page<DocenteGrupo> buscarTodosPaginados(Pageable pageable) {
        return docenteGrupoRespositorio.findByOrderByDocenteDesc(pageable);
    }

    @Override
    public DocenteGrupo buscarPorId(Integer id) {
        return docenteGrupoRespositorio.findById(id).get();
    }

    @Override
    public DocenteGrupo crearOEditar(DocenteGrupo docenteGrupo) {
        return docenteGrupoRespositorio.save(docenteGrupo);
    }

    @Override
    public void eliminarPorId(Integer id) {
    docenteGrupoRespositorio.deleteById(id);
    }
}
