package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Review(String content) {
        this.content = content;
    }

    public void changeMember(Member member){
        this.member = member;
        if(member != null) {
            member.getReviewList().add(this);
        }
    }

    public void changeRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
        if(restaurant != null) {
            restaurant.getReviewList().add(this);
        }
    }

}
