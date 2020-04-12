package com.ajithan.demo.services;

import com.ajithan.demo.model.DistrictWiseData;
import com.ajithan.demo.model.OverAllCases;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CoronaVirusTrackerService {
    private List<DistrictWiseData> allDistrictWiseDataList = new ArrayList<>();

    public List<DistrictWiseData> getAllDistrictWiseDataList() {
        return allDistrictWiseDataList;
    }
    public String getDate() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return (df.format(calendar.getTime())).toString();
    }

    @Autowired
    OverAllCases overAllCases;

    @PostConstruct
    public void fetchDataFromWiki() {
        List<DistrictWiseData> districtWiseDataList = new ArrayList<>();
        List<String> extractedDataList = new ArrayList<>();
        String wikiUrl = "https://en.wikipedia.org/wiki/2020_coronavirus_pandemic_in_Tamil_Nadu";
        try {
            Document document= Jsoup.connect(wikiUrl).get();
            Element element = document.getElementById("covid19-container");
            Elements elements = element.select("tr");
            for (Element e:elements
                 ) {
                extractedDataList.add(e.text().toString());
            }
            String[] overAllCasesDetails = extractedDataList.get(2).split(" ");
           overAllCases.setOverAllTotalCases(Integer.parseInt(overAllCasesDetails[1]));
            overAllCases.setOverAllDeathCases(Integer.parseInt(overAllCasesDetails[2]));
            overAllCases.setOverAllActiveCases(Integer.parseInt(overAllCasesDetails[1])-(Integer.parseInt(overAllCasesDetails[2])+Integer.parseInt(overAllCasesDetails[3])));
            overAllCases.setOverAllRecoverdCases(Integer.parseInt(overAllCasesDetails[3]));
            for(int i = 3;i<=36;i++){
                DistrictWiseData districtWiseData = new DistrictWiseData();
                String[] districtWiseDetailsData = extractedDataList.get(i).split(" ");
                districtWiseData.setDistrictName(districtWiseDetailsData[0]);
                districtWiseData.setTotalCases(Integer.parseInt(districtWiseDetailsData[1]));
                districtWiseData.setActiveCases(Integer.parseInt(districtWiseDetailsData[1])-(Integer.parseInt(districtWiseDetailsData[2])+Integer.parseInt(districtWiseDetailsData[3])));
                districtWiseData.setRecoveredCases(Integer.parseInt(districtWiseDetailsData[2]));
                districtWiseData.setDeathCases(Integer.parseInt(districtWiseDetailsData[3]));
                districtWiseDataList.add(districtWiseData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.allDistrictWiseDataList=districtWiseDataList;
    }
}
