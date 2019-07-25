package br.edu.ifal.pw2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ControladorPrincipal{

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("form.html");
    }
    @RequestMapping("/cadastro")
    public ModelAndView cadastro(String nome, String email, String cpf, String sexo, String modulo, String areasAt, String senha){
        ModelAndView resposta = new ModelAndView("form.html");
        resposta.addObject("nome", nome);
        resposta.addObject("email", email);
        resposta.addObject("cpf", cpf);
        resposta.addObject("sexo", sexo);
        resposta.addObject("modulo", modulo);
        resposta.addObject("areasAt", areasAt);
        resposta.addObject("senha", senha);
        return resposta;
    }
}