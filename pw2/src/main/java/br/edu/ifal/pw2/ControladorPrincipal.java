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
    public String cadastro(Aluno aluno){
        String infoCadastrados = "Alunos cadastrados: <br>";
        rep.save(aluno);

        Iterable<Aluno> alunos = rep.findAll();    
       // ModelAndView resposta = new ModelAndView("form.html");
        for (Aluno al : alunos) {

        //resposta.addObject("nome", al.getNome());
        //resposta.addObject("email", al.getEmail());
        //resposta.addObject("cpf", al.getCpf());
        //resposta.addObject("sexo", al.getSexo());
        //resposta.addObject("modulo", al.getModulo());
        //resposta.addObject("areasAt", al.getDadosAreasAt());
        //resposta.addObject("senha", al.getSenha());
        infoCadastrados += "[Nome: " + al.getNome() + ", Email: " + al.getEmail() + ", CPF: " +
        al.getCpf() + ", Sexo: " + al.getSexo() + ", Módulo: " + al.getModulo() + ", Áreas: " +
        al.getDadosAreasAt() + " Senha: " + al.getSenha() + "]" + "<br>";
            
        }
        return infoCadastrados + "<br> <a href='/'>Clique para voltar para o formulário</a>";
    }
}