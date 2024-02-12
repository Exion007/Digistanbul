package com.example._proj;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlaceByPlacename(String placeName) {
        return placeRepository.findPlaceByPlaceName(placeName);
    }

    public Optional<Place> deletePlace(String placeName) {
        return placeRepository.deletePlaceByPlaceName(placeName);
    }

    public List<Place> getPlacesByAdress(String address) {
        return placeRepository.findPlacesByAddress(address);
    }

    public List<Place> getPlacesByType(String placeType) {
        return placeRepository.findPlacesByPlaceType(placeType);
    }

    public Place addPlace(Place newPlace) {
        return placeRepository.save(newPlace);
    }

    public void addCommentToPlace(String placeName, Comment comment) {
        Optional<Place> optionalPlace = placeRepository.findPlaceByPlaceName(placeName);
        if (optionalPlace.isPresent()) {
            Place place = optionalPlace.get();
            place.addComment(comment);
            placeRepository.save(place);
        } else {
            throw new RuntimeException("Place not found with name: " + placeName);
        }
    }
    public Place addRating(String placeName, double userRating) {
        Optional<Place> optionalPlace = placeRepository.findPlaceByPlaceName(placeName);

        if (optionalPlace.isPresent()) {
            Place place = optionalPlace.get();
            double newRating = (userRating + place.getRating() * place.getRateCount()) / (place.getRateCount() + 1);
            place.setRating(newRating);
            place.setRateCount(place.getRateCount() + 1);
            return placeRepository.save(place);
        }

        return null;
    }

    public void removeCommentFromPlace(String placeName, Comment comment) {
        Optional<Place> optionalPlace = placeRepository.findPlaceByPlaceName(placeName);
        if (optionalPlace.isPresent()) {
            Place place = optionalPlace.get();
            place.getComments().remove(comment);
            placeRepository.save(place);
        } else {
            throw new RuntimeException("Place not found with name: " + placeName);
        }
    }
}