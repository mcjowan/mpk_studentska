package edu.mpk.controllers;

import edu.mpk.domain.Predmet;
import edu.mpk.services.PredmetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PredmetController {

    private PredmetService predmetService;

    @Autowired
    public void setPredmetService(PredmetService predmetService) {
        this.predmetService = predmetService;
    }
    

    @RequestMapping(value = "/predmeti", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("predmeti", predmetService.listAllPredmeti());
        return "predmeti";
    }

    @RequestMapping("predmet/{id}")
    public String showPredmet(@PathVariable Long id, Model model){
        model.addAttribute("predmet", predmetService.getPredmetById(id));
        return "predmetshow";
    }

    @RequestMapping("predmet/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("predmet", predmetService.getPredmetById(id));
        return "predmetform";
    }

    @RequestMapping("predmet/new")
    public String newPredmet(Model model){
        model.addAttribute("predmet", new Predmet());
        return "predmetform";
    }

    @RequestMapping(value = "predmet", method = RequestMethod.POST)
    public String savePredmet(Predmet predmet){
    	predmetService.savePredmet(predmet);
        return "redirect:/predmet/" + predmet.getId();
    }

    @RequestMapping("predmet/delete/{id}")
    public String delete(@PathVariable Long id){
    	predmetService.deletePredmet(id);
        return "redirect:/predmeti";
    }

}
