package com.example.springBackend_Hibernate.service;


import com.example.springBackend_Hibernate.MEntityNotFoundException;
import com.example.springBackend_Hibernate.entity.Review;
import com.example.springBackend_Hibernate.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review reviewDetails) throws MEntityNotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new MEntityNotFoundException("Review not found with id: " + id));
        review.setClient(reviewDetails.getClient());
        review.setProduct(reviewDetails.getProduct());
        review.setReview(reviewDetails.getReview());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
