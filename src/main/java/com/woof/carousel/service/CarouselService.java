package com.woof.carousel.service;

import com.woof.carousel.dao.CarouselResponsitory;
import com.woof.carousel.entity.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarouselService {

    @Autowired
    private CarouselResponsitory responsitory;

//    儲存跟修改
    public Carousel saveCarousel(byte[] image , String title , String content , Integer sequence , Integer isActive	){
        Carousel carousel = new Carousel();
        carousel.setImage(image);
        carousel.setTitle(title);
        carousel.setContent(content);
        carousel.setSequence(sequence);
        carousel.setIsActive(isActive);

        return responsitory.save(carousel);
    }

//    取得單一物件
    public Optional<Carousel> getCarouselById(Integer carId){
        return responsitory.findById(carId);
    }

//    刪除單一物件
    public void deleteCarousel(Integer carId){
        responsitory.deleteById(carId);
    }

//    取得單一物件的照片
    public byte[] getCarouselImage(Integer carId){
        return responsitory.findById(carId)
                .map(Carousel::getImage)
                .orElse(null);
    }

//    取得全部
    public List<Carousel> getAll(){
        return responsitory.findAll();
    }

//    單一修改
    public Carousel updateCarousel(Carousel carousel){
        return responsitory.save(carousel);
    }

//    批量修改
    public List<Carousel> updateCarousels(List<Integer> carIds, byte[] image, String title, String content, Integer sequence, Integer isActive) {
        List<Carousel> carouselsToUpdate = responsitory.findAllById(carIds);

        for (Carousel carousel : carouselsToUpdate) {
            carousel.setImage(image);
            carousel.setTitle(title);
            carousel.setContent(content);
            carousel.setSequence(sequence);
            carousel.setIsActive(isActive);
        }

        return responsitory.saveAll(carouselsToUpdate);
    }

    public List<Carousel> getActiveCarousel(){
        return responsitory.findByIsActiveOrderBySequence(1);
    }
}
