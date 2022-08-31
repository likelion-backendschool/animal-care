package com.codelion.animalcare.global.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class OpeningHour {
    private String monStart;
    private String monEnd;
    private String tueStart;
    private String tueEnd;
    private String wedStart;
    private String wedEnd;
    private String thuStart;
    private String thuEnd;
    private String friStart;
    private String friEnd;
    private String satStart;
    private String satEnd;
    private String sunStart;
    private String sunEnd;

    public String openingHourToString() {
        StringBuilder sb = new StringBuilder();

        sb.append(monStart).append("~").append(monEnd).append("/")
                .append(tueStart).append("~").append(tueEnd).append("/")
                .append(wedStart).append("~").append(wedEnd).append("/")
                .append(thuStart).append("~").append(thuEnd).append("/")
                .append(friStart).append("~").append(friEnd).append("/")
                .append(satStart).append("~").append(satEnd).append("/")
                .append(sunStart).append("~").append(sunEnd).append("/");

        return  sb.toString();
    }
    public OpeningHour(String openingHours){
        if(openingHours == null) return;
        List<String[]> list = Arrays.stream(openingHours.split("/")).map(item -> item.split("~")).toList();
        if(list.size() != 7) return;
        if(list.get(0).length == 2){
            this.monStart = list.get(0)[0];
            this.monEnd = list.get(0)[1];
        }
        if(list.get(1).length == 2){
            this.tueStart = list.get(1)[0];
            this.tueEnd = list.get(1)[1];
        }
        if(list.get(2).length == 2){
            this.wedStart = list.get(2)[0];
            this.wedEnd = list.get(2)[1];
        }
        if(list.get(3).length == 2){
            this.thuStart = list.get(3)[0];
            this.thuEnd = list.get(3)[1];
        }
        if(list.get(4).length == 2) {
            this.friStart = list.get(4)[0];
            this.friEnd = list.get(4)[1];
        }
        if(list.get(5).length == 2) {
            this.satStart = list.get(5)[0];
            this.satEnd = list.get(5)[1];
        }
        if(list.get(6).length == 2) {
            this.sunStart = list.get(6)[0];
            this.sunEnd = list.get(6)[1];
        }
    }
}
