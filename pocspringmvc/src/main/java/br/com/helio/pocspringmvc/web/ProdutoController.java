/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helio.pocspringmvc.web;

import br.com.helio.pocspringmvc.entity.Produto;
import br.com.helio.pocspringmvc.service.ProdutService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Helio
 */
@Controller
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutService produtService;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public ModelAndView showPersons() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("produtos", produtService.findAll());
        modelAndView.setViewName("produto/lista");

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("produto", new Produto());
        modelAndView.setViewName("produto/add");

        return modelAndView;
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(@ModelAttribute Produto produto) {
        produtService.salvar(produto);

        return "redirect:/produto/lista";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public ModelAndView editar(@RequestParam("codigo") String codigo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("produto", produtService.findByCodigo(codigo));
        modelAndView.setViewName("produto/add");
        return modelAndView;
    }

    @RequestMapping(value = "/remover", method = RequestMethod.GET)
    public String remover(@RequestParam("codigo") String codigo) {
        produtService.delete(produtService.findByCodigo(codigo));
        return "redirect:/produto/lista";
    }
}
