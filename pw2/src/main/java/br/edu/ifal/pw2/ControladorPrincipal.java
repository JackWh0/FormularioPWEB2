package br.edu.ifal.pw2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class ControladorPrincipal{

    @Autowired
    AlunoRepositorio rep;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView retorno = new ModelAndView("form.html");
        retorno.addObject("aluno", new Aluno());
        return retorno;
    }
    @RequestMapping("/cadastro")
    public ModelAndView cadastroAluno(Aluno aluno, RedirectAttributes redirect) {
        rep.save(aluno);
        ModelAndView retorno = new ModelAndView("redirect:/listar_alunos");
        redirect.addFlashAttribute("mensagem", aluno.getNome() + " cadastrado com sucesso.");
        return retorno;
    }

    @RequestMapping("/listar_alunos")
    public ModelAndView listarAlunos() {
        ModelAndView retorno = new ModelAndView("listar_alunos.html");
        Iterable<Aluno> alunos = rep.findAll();
        retorno.addObject("alunos", alunos);
        return retorno;
    }

    @RequestMapping("/excluir_aluno/{idAluno}")
    public ModelAndView excluirAlunos(@PathVariable("idAluno") Long alunoID, RedirectAttributes redirect) {
        Optional<Aluno> opcao = rep.findById(alunoID);
        ModelAndView retorno = new ModelAndView("redirect:/listar_alunos");        
        if(opcao.isPresent()){
            Aluno aluno = opcao.get();
            rep.deleteById(aluno.getId());            
            redirect.addFlashAttribute("mensagem", aluno.getNome() + " excluído com sucesso.");
            return retorno;
        } else {
            redirect.addFlashAttribute("mensagem", "Aluno " + alunoID + " não existe no Banco de Dados.");
            return retorno;
        }
        
    }

    @GetMapping("/atualizar_aluno/{idAluno}")
    public ModelAndView atualizar(@PathVariable("idAluno") Long alunoID) {
        Optional<Aluno> opcao = rep.findById(alunoID);
        
        ModelAndView retorno = new ModelAndView("form.html");

        if(opcao.isPresent()){
            Aluno aluno = opcao.get();
            retorno.addObject("aluno", aluno);
            return retorno;
        }
        return retorno;
    }
}
