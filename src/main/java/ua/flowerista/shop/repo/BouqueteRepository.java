package ua.flowerista.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.flowerista.shop.models.Bouquete;

@Repository
public interface BouqueteRepository extends JpaRepository<Bouquete, Integer> {

}
