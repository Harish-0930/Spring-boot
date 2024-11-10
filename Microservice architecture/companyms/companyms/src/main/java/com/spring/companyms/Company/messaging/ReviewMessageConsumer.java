package com.spring.companyms.Company.messaging;

import com.spring.companyms.Company.dto.ReviewMessage;
import com.spring.companyms.Company.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class ReviewMessageConsumer {


    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        companyService.updateCompanyRating(reviewMessage);
    }
}
