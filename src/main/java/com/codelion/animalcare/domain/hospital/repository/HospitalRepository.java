package com.codelion.animalcare.domain.hospital.repository;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Page<Hospital> findAll(Pageable pageable);

    @Query("select distinct h " +
            "from Hospital h " +
            " " +
            "where :minLatitude < h.address.latitude and h.address.latitude < :maxLatitude and :minLongitude < h.address.longitude and h.address.longitude < :maxLongitude " +
            "and (h.name like %:keyword% or h.address.street like %:keyword%)")
    Page<Hospital> findByLatitudeAndLongitude(
            @Param(value = "minLatitude") Double minLatitude,
            @Param(value = "maxLatitude") Double maxLatitude,
            @Param(value = "minLongitude") Double minLongitude,
            @Param(value = "maxLongitude") Double maxLongitude,
            @Param(value = "keyword") String keyword,
            Pageable pageable
    );
}
