package io.javabrains.coronavirustracker.services;

import io.javabrains.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class CoronaVirusDataService {

    private static String VIRUS_URL;

    @Value("${app.corona.virus.url}")
    private String virus_url;

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    public void init() {
        VIRUS_URL = virus_url;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *") //Rerun the  service daily !
    public void fetchVirusData() throws IOException, InterruptedException {

        List<LocationStats> newStats = new ArrayList<>();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(VIRUS_URL))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);


        for (CSVRecord record : records) {
            String country = record.get("Country/Region");
            LocationStats locationStats = new LocationStats();
            locationStats.setCountry(record.get("Country/Region"));
            locationStats.setState(record.get("Province/State"));
            locationStats.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));
            locationStats.setDiffFromPrevDay(Integer.parseInt(record.get(record.size() - 1)) - Integer.parseInt(record.get(record.size() - 2)));
            System.out.println(locationStats);
            newStats.add(locationStats);
        }

        this.allStats = newStats;


    }
}
