package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class AccidentControl {

    private AccidentService accidentService;
    private AccidentTypeService accidentTypeService;
    private RuleService ruleService;

    public AccidentControl(AccidentService accidentService, AccidentTypeService accidentTypeService, RuleService ruleService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
        this.ruleService = ruleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentTypeService.getTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        addTypeAndRules(accident, req);
        accidentService.addAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdate")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findByIdAccident(id));
        return "accident/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        addTypeAndRules(accident, req);
        accidentService.update(accident);
        return "redirect:/";
    }

    private void addTypeAndRules(Accident accident, HttpServletRequest req) {
        accident.setAccidentType(accidentTypeService.findByIdType(accident.getAccidentType().getId()));
        String[] ids = req.getParameterValues("rIds");
        Arrays.stream(ids)
                .map(Integer::parseInt)
                .forEach(el -> accident.addRule(ruleService.findByIdRule(el)));
    }
}