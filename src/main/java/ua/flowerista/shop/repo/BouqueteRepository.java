package ua.flowerista.shop.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.flowerista.shop.models.Bouquete;

@Repository
public interface BouqueteRepository extends JpaRepository<Bouquete, Integer> {

	List<Bouquete> findTop5ByOrderBySoldQuantityDesc();

	@Query("SELECT b FROM Bouquete b WHERE b.discount IS NOT NULL ORDER BY b.discount DESC LIMIT 5")
	List<Bouquete> findTop5ByOrderByDiscountDesc();

	@Query("SELECT b FROM Bouquete b " + "WHERE "
			+ "(:flowerIds IS NULL OR EXISTS (SELECT 1 FROM b.flowers flower WHERE flower.id IN :flowerIds)) AND "
			+ "(:colorIds IS NULL OR EXISTS (SELECT 1 FROM b.colors color WHERE color.id IN :colorIds)) AND "
			+ "(:minPrice IS NULL OR (b.discountPrice IS NOT NULL AND b.discountPrice >= :minPrice) OR (b.discountPrice IS NULL AND b.defaultPrice >= :minPrice)) AND "
			+ "(:maxPrice IS NULL OR (b.discountPrice IS NOT NULL AND b.discountPrice <= :maxPrice) OR (b.discountPrice IS NULL AND b.defaultPrice <= :maxPrice)) "
			+ "ORDER BY " + "CASE WHEN :sortByNewest = true THEN b.id END DESC, "
		    + "CASE WHEN :sortByPriceHighToLow = true THEN COALESCE(b.discountPrice, b.defaultPrice) END DESC, "
		    + "CASE WHEN :sortByPriceLowToHigh = true THEN COALESCE(b.discountPrice, b.defaultPrice) END ASC")
	Page<Bouquete> findByFilters(@Param("flowerIds") List<Integer> flowerIds, @Param("colorIds") List<Integer> colorIds,
			@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice,
			@Param("sortByNewest") Boolean sortByNewest, @Param("sortByPriceHighToLow") Boolean sortByPriceHighToLow,
			@Param("sortByPriceLowToHigh") Boolean sortByPriceLowToHigh, Pageable pageable);
	
    @Query("SELECT MIN(COALESCE(b.discountPrice, b.defaultPrice)) FROM Bouquete b")
    Integer findMinPrice();

    @Query("SELECT MAX(COALESCE(b.discountPrice, b.defaultPrice)) FROM Bouquete b")
    Integer findMaxPrice();

}
