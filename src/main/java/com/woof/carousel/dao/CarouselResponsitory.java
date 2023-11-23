package com.woof.carousel.dao;

import com.woof.carousel.entity.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarouselResponsitory extends JpaRepository<Carousel, Integer> {
    List<Carousel> findByIsActiveOrderBySequence(Integer isActive);

}
