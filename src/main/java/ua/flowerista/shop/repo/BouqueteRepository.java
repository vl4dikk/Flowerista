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

	@Query("SELECT b FROM Bouquete b JOIN b.sizes bs WHERE bs.discount IS NOT NULL ORDER BY bs.discount DESC LIMIT 5")
	List<Bouquete> findTop5ByOrderByDiscountDesc();

	@Query("SELECT DISTINCT b FROM Bouquete b " +
		       "LEFT JOIN FETCH b.sizes bs " +
		       "WHERE " +
		       "(:flowerIds IS NULL OR EXISTS (SELECT 1 FROM b.flowers flower WHERE flower.id IN :flowerIds)) AND " +
		       "(:colorIds IS NULL OR EXISTS (SELECT 1 FROM b.colors color WHERE color.id IN :colorIds)) AND " +
		       "(:minPrice IS NULL OR (bs.size = 'MEDIUM' AND COALESCE(bs.discountPrice, bs.defaultPrice) >= :minPrice) OR bs IS NULL) AND " +
		       "(:maxPrice IS NULL OR (bs.size = 'MEDIUM' AND COALESCE(bs.discountPrice, bs.defaultPrice) <= :maxPrice) OR bs IS NULL) " +
		       "ORDER BY " +
		       "CASE WHEN :sortByNewest = true THEN b.id END DESC, " +
		       "CASE WHEN :sortByPriceHighToLow = true AND bs.size = 'MEDIUM' THEN COALESCE(bs.discountPrice, bs.defaultPrice) END DESC, " +
		       "CASE WHEN :sortByPriceLowToHigh = true AND bs.size = 'MEDIUM' THEN COALESCE(bs.discountPrice, bs.defaultPrice) END ASC")
	Page<Bouquete> findByFilters(@Param("flowerIds") List<Integer> flowerIds, @Param("colorIds") List<Integer> colorIds,
			@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice,
			@Param("sortByNewest") Boolean sortByNewest, @Param("sortByPriceHighToLow") Boolean sortByPriceHighToLow,
			@Param("sortByPriceLowToHigh") Boolean sortByPriceLowToHigh, Pageable pageable);

	@Query("SELECT MIN(COALESCE(bs.discountPrice, bs.defaultPrice)) FROM Bouquete b JOIN b.sizes bs WHERE bs.size = 'MEDIUM'")
	Integer findMinPrice();

	@Query("SELECT MAX(COALESCE(bs.discountPrice, bs.defaultPrice)) FROM Bouquete b JOIN b.sizes bs WHERE bs.size = 'MEDIUM'")
	Integer findMaxPrice();

}
