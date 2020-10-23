package io.javabrains.coronavirustracker.controller;


import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//Not  a  rest controller, we are not returning  a  rest response!!

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {

        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalCases = allStats.stream()
                .mapToInt(s -> s.getLatestTotalCases())
                .sum();
        int totalReportedNewCases = allStats
                .stream()
                .mapToInt(s -> s.getDiffFromPrevDay())
                .sum();
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("newCases", totalReportedNewCases);
        model.addAttribute("locationStats", coronaVirusDataService.getAllStats());

        return "Home";
    }


}
