package com.spring.reviewms.Review.reviewController;


import com.spring.reviewms.Review.messaging.ReviewMessageProducer;
import com.spring.reviewms.Review.review.Review;
import com.spring.reviewms.Review.reviewService.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private ReviewMessageProducer reviewMessageProducer;
    public ReviewController(ReviewService reviewService,ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer=reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<String> addreview(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isReviewSaved=reviewService.addReview(companyId,review);
        if(isReviewSaved)
            return new ResponseEntity<>("Review addedd Successfully",HttpStatusCode.valueOf(201));
        else
            return new ResponseEntity<>("Review Not Saved",HttpStatusCode.valueOf(404));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review){

        boolean isReviewedUpdated = reviewService.updateReview(reviewId,review);
        if(isReviewedUpdated) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review updated successfully", HttpStatusCode.valueOf(200));
        }
        else
            return new ResponseEntity<>("Review Not Updated",HttpStatusCode.valueOf(404));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isReviewDeleted=reviewService.deleteReview(reviewId);
        if(isReviewDeleted)
            return  new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
    }
}
