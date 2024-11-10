package com.spring.reviewms.Review.reviewServiceImpl;


import com.spring.reviewms.Review.review.Review;
import com.spring.reviewms.Review.reviewRepository.ReviewRepository;
import com.spring.reviewms.Review.reviewService.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
//    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
//        Company company = companyService.getCompanyById(companyId);
        if(companyId!=null && review!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
//        List<Review> reviews= reviewRepository.findByCompanyId(companyId);
//        return reviews.stream()
//                .filter(review -> review.getId().equals(reviewId))
//                .findFirst()
//                .orElse(null);
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedreview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(reviewId!=null){
//            updatedreview.setCompany(companyService.getCompanyById(companyId));
//            updatedreview.setId(reviewId);
//            reviewRepository.save(updatedreview);
            review.setTitle(updatedreview.getTitle());
            review.setDescription(updatedreview.getDescription());
            review.setRating(updatedreview.getRating());
            review.setCompanyId(updatedreview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview( Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
//        if(companyService.getCompanyById(companyId)!=null &&
//            reviewRepository.existsById(reviewId)){
//            Review review = reviewRepository.findById(reviewId).orElse(null);
//            Company company = review.getCompany();
//            company.getReviews().remove(review);
//            review.setCompany(null);
//            companyService.updateCompany(company,companyId);
//            reviewRepository.deleteById(reviewId);
//            return true;
        if(review!=null){
            reviewRepository.delete(review);
            return true;
        } else {
            return false;
        }
    }
}
