package com.ajithan.demo.controller;

import com.ajithan.demo.model.OverAllCases;
import com.ajithan.demo.services.CoronaVirusTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CoronaVirusTrackerController {
    @Autowired
    CoronaVirusTrackerService coronaVirusTrackerService;
    @Autowired
    OverAllCases overAllCases;
    @GetMapping("/tn_disctrictWise_covid-19")
    public String getDetails(Model model){
        model.addAttribute("districtWiseDetails",coronaVirusTrackerService.getAllDistrictWiseDataList());
        model.addAttribute("totalCases",overAllCases.getOverAllTotalCases());
        model.addAttribute("totalDeathCases",overAllCases.getOverAllDeathCases());
        model.addAttribute("totalRecoveredCases",overAllCases.getOverAllRecoverdCases());
        model.addAttribute("totalActiveCases",overAllCases.getOverAllActiveCases());
        model.addAttribute("yesterdayDate",coronaVirusTrackerService.getDate());
        return "Home";
    }
}
