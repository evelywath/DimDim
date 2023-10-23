package br.com.fiap.dimdim.cupom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.oauth2.core.user.OAuth2User;


import jakarta.validation.Valid;

    public class CupomController {

        @Autowired
        CupomService service;

        @Autowired
        MessageSource message;

        @GetMapping
        public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        return null;

        }
        
        @GetMapping("/delete/{id}")
        public String delete(@PathVariable Long id, RedirectAttributes redirect){

            if(service.delete(id)){
            redirect.addFlashAttribute("success", "Tarefa apagada com sucesso");
            redirect.addFlashAttribute("success", getMessage("task.delete.success") );
            }else{
            redirect.addFlashAttribute("error", "Tarefa não foi encontrada");
            redirect.addFlashAttribute("error", getMessage("task.notfound"));
        }
        
        return "redirect:/task";
    }

    public String form(Cupom cupom){
        return null;
        
    }
        
        public String create(@Valid Cupom cupom, BindingResult result, RedirectAttributes redirect){
            if (result.hasErrors()) return "cupom/form";
            service.save(cupom);
            redirect.addFlashAttribute("success", "Cupom cadastrada com sucesso");
            redirect.addFlashAttribute("success", getMessage("cupom.create.success")); 

        return "redirect:/cupom";
    }

    private String getMessage(String code){
        return message.getMessage(code, null, LocaleContextHolder.getLocale());
    }

}