package br.com.fiap.dimdim.cupom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cupons")
public class CupomController {

    @Autowired
    CupomService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("cupons", service.findAll());
        return "cupom/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if(service.delete(id)){
            redirect.addFlashAttribute("success", "Cupom deletado");
        }else{
            redirect.addFlashAttribute("error", "Ocorreu um erro na pesquisa pelo Cupom");
        }
        return "redirect:/cupons";
    }
    
    @GetMapping("new")
    public String form(Cupom cupom){
        return "cupom/form";
    }
    
    @PostMapping
    public String create(@Valid Cupom cupom, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "cupons/form";
        service.save(cupom);
        redirect.addFlashAttribute("success", "Cupom cadastrado com sucesso");
        return "redirect:/cupom";
    }
    
}