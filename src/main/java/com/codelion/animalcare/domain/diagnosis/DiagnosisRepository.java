package com.codelion.animalcare.domain.diagnosis;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DiagnosisRepository {

    private final EntityManager em;

    public DiagnosisRepository(EntityManager em) {
        this.em = em;
    }

    public List<Diagnosis> findAllByString(DiagnosisSearch diagnosisSearch) {

        String jpql = "select dn from Diagnosis dn join dn.member m";
        boolean isFirstCondition = true;

        //회원 이름 검색
        if (StringUtils.hasText(diagnosisSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Diagnosis> query = em.createQuery(jpql, Diagnosis.class)
                .setMaxResults(1000);

        if (StringUtils.hasText(diagnosisSearch.getMemberName())) {
            query = query.setParameter("name", diagnosisSearch.getMemberName());
        }

        return query.getResultList();

    }
}
