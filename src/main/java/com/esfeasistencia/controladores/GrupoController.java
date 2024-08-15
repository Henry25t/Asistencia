package com.esfeasistencia.controladores;


import com.esfeasistencia.modelos.Grupo;
import com.esfeasistencia.servicios.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.stylesheets.LinkStyle;


import javax.naming.Binding;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller()
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private IGrupoService grupoSevice;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")Optional<Integer> size)
    {
        int currentPage = page.orElse(1) -1;
        int pageSize = size.orElse(5);
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize, Sort.by("id").descending());

        Page<Grupo> grupos = grupoSevice.buscarTodosPaginados(pageable);
        model.addAttribute("grupos", grupos);

        int totalPage = grupos.getTotalPages();
        if(totalPage > 0){
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumber);
        }
        return "grupo/index";
    }

    @GetMapping("/create")
    public String create(Grupo grupo){
        return "grupo/create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Grupo pGrupo, BindingResult result, Model model, RedirectAttributes attributes)
    {
        if(result.hasErrors()){
            model.addAttribute(pGrupo);
            return "grupo/create";
        }
        if(pGrupo.getId() != null && pGrupo.getId() > 0){
            grupoSevice.createOrEditOne(pGrupo);
            attributes.addFlashAttribute("msg", "Grupo actualizado exitosamente");
        }else{
            grupoSevice.createOrEditOne(pGrupo);
            attributes.addFlashAttribute("msg", "Grupo creado exitosamente");
        }
        return "redirect:/grupos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id")Integer id, Model model)
    {
        Grupo grupo = grupoSevice.buscarPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupo/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Integer id, Model model)
    {
        Grupo grupo = grupoSevice.buscarPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupo/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Grupo grupo = grupoSevice.buscarPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupo/delete";
    }
    @PostMapping("/delete")
    public String delete(Grupo grupo, RedirectAttributes attributes){
        grupoSevice.eliminarPorId(grupo.getId());
        attributes.addFlashAttribute("msg", "Grupo eliminado exitosamente");
        return "redirect:/grupos";
    }
}
