package com.codelion.animalcare.domain.animal;

import com.codelion.animalcare.domain.animal.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimalRepository {

    private final EntityManager em;

    public void save(Animal animal) {

        em.persist(animal);
    }

    public Animal findOne(Long id) {
        return em.find(Animal.class, id);
    }

    public List<Animal> findAll() {
        return em.createQuery("select a from Animal a", Animal.class)
                .getResultList();
    }

    public List<Animal> findByName(String name) {
        return em.createQuery("select a from Animal a where a.name = :name", Animal.class)
                .setParameter("name", name)
                .getResultList();
    }
}
