package com.ajithan.demo.model;

import org.springframework.stereotype.Component;

@Component
public class OverAllCases {
    public int getOverAllTotalCases() {
        return overAllTotalCases;
    }

    public void setOverAllTotalCases(int overAllTotalCases) {
        this.overAllTotalCases = overAllTotalCases;
    }

    public int getOverAllDeathCases() {
        return overAllDeathCases;
    }

    public void setOverAllDeathCases(int overAllDeathCases) {
        this.overAllDeathCases = overAllDeathCases;
    }

    public int getOverAllActiveCases() {
        return overAllActiveCases;
    }

    public void setOverAllActiveCases(int overAllActiveCases) {
        this.overAllActiveCases = overAllActiveCases;
    }

    public int getOverAllRecoverdCases() {
        return overAllRecoverdCases;
    }

    public void setOverAllRecoverdCases(int overAllRecoverdCases) {
        this.overAllRecoverdCases = overAllRecoverdCases;
    }
    OverAllCases()
    {

    }
    private int overAllTotalCases;
    private int overAllDeathCases;
    private int overAllActiveCases;
    private int overAllRecoverdCases;
}
