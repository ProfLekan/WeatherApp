package com.weatherapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores 5-day forecast information.
 *
 * Each field represents a simplified forecast
 * for a particular day.
 */
public class ForecastData {
	
	/*
	 * Stores all forecast entries
	 * returned by the API.
	 */
	private List<ForecastEntry> entries =
	        new ArrayList<>();

    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private String day5;
    
    private double day1Temp;
    private double day2Temp;
    private double day3Temp;
    private double day4Temp;
    private double day5Temp;
    
    private String day1Name;
    private String day2Name;
    private String day3Name;
    private String day4Name;
    private String day5Name;

    public String getDay1Name() {
		return day1Name;
	}

	public void setDay1Name(String day1Name) {
		this.day1Name = day1Name;
	}

	public String getDay2Name() {
		return day2Name;
	}

	public void setDay2Name(String day2Name) {
		this.day2Name = day2Name;
	}

	public String getDay3Name() {
		return day3Name;
	}

	public void setDay3Name(String day3Name) {
		this.day3Name = day3Name;
	}

	public String getDay4Name() {
		return day4Name;
	}

	public void setDay4Name(String day4Name) {
		this.day4Name = day4Name;
	}

	public String getDay5Name() {
		return day5Name;
	}

	public void setDay5Name(String day5Name) {
		this.day5Name = day5Name;
	}

	public double getDay1Temp() {
		return day1Temp;
	}

	public void setDay1Temp(double day1Temp) {
		this.day1Temp = day1Temp;
	}

	public double getDay2Temp() {
		return day2Temp;
	}

	public void setDay2Temp(double day2Temp) {
		this.day2Temp = day2Temp;
	}

	public double getDay3Temp() {
		return day3Temp;
	}

	public void setDay3Temp(double day3Temp) {
		this.day3Temp = day3Temp;
	}

	public double getDay4Temp() {
		return day4Temp;
	}

	public void setDay4Temp(double day4Temp) {
		this.day4Temp = day4Temp;
	}

	public double getDay5Temp() {
		return day5Temp;
	}

	public void setDay5Temp(double day5Temp) {
		this.day5Temp = day5Temp;
	}

	/**
     * Default constructor.
     */
    public ForecastData() {
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }
    
    public List<ForecastEntry> getEntries() {
        return entries;
    }

    public void setEntries(
            List<ForecastEntry> entries) {

        this.entries = entries;
    }
    
}