package io.javabrains.coronavirustracker.controller;


import io.javabrains.coronavirustracker.models.LocationStats;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@org.springframework.web.bind.annotation.RestController
public class RestController {


    @PostMapping("/LocationStats/{LocationStatsId}/add")
    public LocationStats addLocationStats(
            @PathVariable String LocationStatsId,
            @Valid @RequestBody LocationStats locationStats) {

        System.out.println("The passed path variable is: " + LocationStatsId);

        LocationStats newLocationStat = new LocationStats(
                locationStats.getState(),
                locationStats.getCountry(),
                locationStats.getLatestTotalCases(),
                locationStats.getDiffFromPrevDay());
        return newLocationStat;
    }
}
