package com.example.travelboard.controller;

import com.example.travelboard.dto.AcceptForm;
import com.example.travelboard.entity.Accept;
import com.example.travelboard.entity.Travel;
import com.example.travelboard.repository.AcceptRepository;
import com.example.travelboard.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TravelController {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private AcceptRepository acceptRepository;

    @GetMapping("/travels")
    public String getAllTravles(Model model){
        List<Travel> getTravelList = (List<Travel>) travelRepository.findAll();
        model.addAttribute("travelList", getTravelList);
        return "/travels/all";
    }

    @GetMapping("/travels/{id}")
    public String getTravelDetails(@PathVariable Long id, Model model){
        Travel travelEntity = travelRepository.findById(id).orElse(null);
        model.addAttribute("travel", travelEntity);
        return "/travels/detail";
    }

    @GetMapping("/travels/accept")
    public String setAcceptForm(){
        return "travels/accept";
    }

    @PostMapping("/travels/create")
    public String createAcceptList(AcceptForm form, Model model){
        Accept accept = form.toEntity();
        Accept saved = acceptRepository.save(accept);

        return "redirect:/travels/acceptlist";
    }

    @GetMapping("/travels/acceptlist")
    public String getAcceptList(Model model){
        List<Accept> getAcceptList = (List<Accept>) acceptRepository.findAll();
        model.addAttribute("acceptList", getAcceptList);
        return "/travels/acceptlist";
    }

    @GetMapping("/travels/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Accept acceptEntity = acceptRepository.findById(id).orElse(null);

        model.addAttribute("acceptList",acceptEntity);
        return "/travels/acceptedit";
    }

    @PostMapping("/travels/update")
    public String update(AcceptForm form){

        Accept acceptEntity = form.toEntity();

        Accept target = acceptRepository.findById(acceptEntity.getId()).orElse(null);

        if(target != null){
            acceptRepository.save(acceptEntity);
        }

        return "redirect:/travels/acceptlist";
    }

    @GetMapping("/travels/{id}/delete")
    public String delte(@PathVariable Long id, RedirectAttributes rttr){
        Accept target = acceptRepository.findById(id).orElse(null);

        //2. 대상을 삭제
        if (target != null) {
            acceptRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료 되었습니다.");
        }

        //3. 결과 페이지로 리다이렉트
        return "redirect:/travels/acceptlist";
    }


}
