package com.example._proj;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/place")
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/allplaces")
    public List<Place> fetchAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/place-with-placename/{placeName}")
    public Optional<Place> getPlace(@PathVariable String placeName) {
        return placeService.getPlaceByPlacename(placeName);
    }

    @DeleteMapping("/delete-with-placename/{placeName}")
    public Optional<Place> deletePlaceName(@PathVariable String placeName) {
        return placeService.deletePlace(placeName);
    }

    @GetMapping("/get-place-with-adress/{address}")
    public List<Place> getWithAddress(@PathVariable String address) {
        return placeService.getPlacesByAdress(address);
    }

    @GetMapping("/get-comment-of-place/{placeName}")
    public List<Comment> getCommentsWithPlaceName(@PathVariable String placeName) {
        return placeService.getPlaceByPlacename(placeName).get().getComments();
    }

    @GetMapping("/get-cafes")
    public List<Place> getCafes() {
        return placeService.getPlacesByType("Cafe");
    }

    @GetMapping("/get-restaurants")
    public List<Place> getRestaurants() {
        return placeService.getPlacesByType("Restaurant");
    }

    @GetMapping("/get-historicals")
    public List<Place> getHistoricals() {
        return placeService.getPlacesByType("Historical");
    }

    @PostMapping("/add-place")
    public ResponseEntity<Place> addPlace(@RequestBody Place newPlace) {
        Place savedPlace = placeService.addPlace(newPlace);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlace);
    }

    @PostMapping("/add-rating")
    public ResponseEntity<Place> addRating(@RequestBody RatingRequest ratingRequest) {
        String placeName = ratingRequest.getPlaceName();
        double userRating = ratingRequest.getUserRating();

        Place updatedPlace = placeService.addRating(placeName, userRating);

        if (updatedPlace != null) {
            return ResponseEntity.ok(updatedPlace);
        } else {
            // Handle the case where the place with the given name is not found
            return ResponseEntity.notFound().build();
        }
    }
}
