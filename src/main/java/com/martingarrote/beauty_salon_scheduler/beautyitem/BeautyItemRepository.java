package com.martingarrote.beauty_salon_scheduler.beautyitem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyItemRepository extends JpaRepository<BeautyItem, Long> {
    @Query("SELECT b FROM BeautyItem b WHERE " +
            "(:name IS NULL OR b.name LIKE %:name%) AND " +
            "(:duration IS NULL OR b.duration = :duration) AND " +
            "(:price IS NULL OR b.price = :price)")
    Page<BeautyItem> search(@Param("name") String name,
                            @Param("duration") Integer duration,
                            @Param("price") Double price,
                            Pageable pageable);
}
