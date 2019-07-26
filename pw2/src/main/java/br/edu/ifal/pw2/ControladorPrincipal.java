package br.edu.ifal.pw2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ControladorPrincipal{

    @Autowired
    AlunoRepositorio rep;

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("form.html");
    }
    @RequestMapping("/cadastro")
    public ModelAndView cadastro(Aluno aluno){

        rep.save(aluno);

        Iterable<Aluno> alunos = rep.findAll();    
        ModelAndView resposta = new ModelAndView("form.html");
        for (Aluno al : alunos) {

        resposta.addObject("nome", al.getNome());
        resposta.addObject("email", al.getEmail());
        resposta.addObject("cpf", al.getCpf());
        resposta.addObject("sexo", al.getSexo());
        resposta.addObject("modulo", al.getModulo());
        resposta.addObject("areasAt", al.getDadosAreasAt());
        resposta.addObject("senha", al.getSenha());
            
        }
        return resposta;
    }
}