package com.woof.carousel.dao;

import com.woof.carousel.entity.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarouselResponsitory extends JpaRepository<Carousel, Integer> {
}
