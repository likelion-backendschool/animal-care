package com.codelion.animalcare.domain.hospital.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LatitudeLongitudeDto {
    Double minLatitude; // 지도 하단 위도
    Double maxLatitude; // 지도 상단 위도
    Double minLongitude; // 지도 죄측 경도
    Double maxLongitude; // 지도 우측 경도
}
