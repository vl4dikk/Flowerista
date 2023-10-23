package ua.flowerista.shop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.flowerista.shop.models.Bouquete;

@Repository
public interface BouqueteRepository extends JpaRepository<Bouquete, Integer> {
	
    List<Bouquete> findTop5ByOrderBySoldQuantityDesc();
    
    @Query("SELECT b FROM Bouquete b WHERE b.discount IS NOT NULL ORDER BY b.discount DESC LIMIT 5")
    List<Bouquete> findTop5ByOrderByDiscountDesc();

}
