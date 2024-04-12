package com.krishnan.bookreview.services;

import com.krishnan.bookreview.model.Review;
import com.krishnan.bookreview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReviewById(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        return optionalReview.orElse(null);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public boolean deleteReviewById(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Review updateReview(Long id, Review updatedReview) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setReviewData(updatedReview.getReviewData());
            review.setReviewDate(updatedReview.getReviewDate());
            return reviewRepository.save(review);
        }
        return null;
    }
}
