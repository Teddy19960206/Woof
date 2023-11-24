package com.woof.carousel.controller;

import com.woof.carousel.entity.Carousel;
import com.woof.carousel.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class CarouselController {

    @Autowired
    private CarouselService carouselService;


    @PostMapping("/addCarousel")
    public ResponseEntity<Carousel> addCarousel(
        @RequestParam("image") MultipartFile image,
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam(value = "sequence" , required = false) Integer sequence,
        @RequestParam(value = "isActive" , defaultValue = "true") Integer isActive) throws IOException {

        Carousel carousel = carouselService.saveCarousel(image.getBytes(), title, content, sequence, isActive);

        return new ResponseEntity<>(carousel , HttpStatus.CREATED);
    }

    @PutMapping("/updateCarousel/{carId}")
    public ResponseEntity<Carousel> updateCarousel(
            @PathVariable Integer carId,
            @RequestParam(value = "image" , required = false) MultipartFile image,
            @RequestParam(value = "title" , required = false) String title,
            @RequestParam(value = "content" , required = false) String content,
            @RequestParam(value = "sequence" , required = false) Integer sequence,
            @RequestParam(value = "isActive" , required = false) Integer isActive ) throws IOException {


        Optional<Carousel> carouselById = carouselService.getCarouselById(carId);
        if (carouselById.isPresent()){
            Carousel carousel = carouselById.get();

            if (image != null){
                carousel.setImage(image.getBytes());
            } else {
                carousel.setImage(carousel.getImage());
            }
            if (title != null) carousel.setTitle(title);
            if (content != null) carousel.setContent(content);
            if (isActive != null) carousel.setIsActive(isActive);
            carousel.setSequence(sequence);




            carouselService.updateCarousel(carousel);
            return new ResponseEntity<>(carousel , HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/Carousels")
    public List<Carousel> findAllCarousel(){
        return carouselService.getAll();
    }

    @GetMapping("/CarouselById/{carId}")
    public ResponseEntity<Carousel> findCarouselById(@PathVariable Integer carId){
        Optional<Carousel> carouselById = carouselService.getCarouselById(carId);

        if (carouselById.isPresent()) {
            Carousel carousel = carouselById.get();
            return new ResponseEntity<>(carousel , HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/CarouselImage/{carId}")
    public ResponseEntity<byte[]> getCarouselImage(@PathVariable Integer carId){
        byte[] carouselImage = carouselService.getCarouselImage(carId);
        if (carouselImage != null){
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(carouselImage);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/CarouselActive")
    public List<Carousel> getActiveCarousel(){

        return carouselService.getActiveCarousel();
    }

    @DeleteMapping("/deleteCarousel/{carId}")
    public void deleteCarousel(@PathVariable Integer carId){
        carouselService.deleteCarousel(carId);
    }

}
