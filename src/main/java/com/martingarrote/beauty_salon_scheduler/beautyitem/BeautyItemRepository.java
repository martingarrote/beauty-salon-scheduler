package com.martingarrote.beauty_salon_scheduler.beautyitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyItemRepository extends JpaRepository<BeautyItem, Long> {
}
