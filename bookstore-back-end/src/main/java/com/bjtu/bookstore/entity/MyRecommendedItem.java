package com.bjtu.bookstore.entity;

import lombok.Data;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

/**
 * @program: bookstore-back-end
 * @description: aaa
 * @author: CodingLiOOT
 * @create: 2021-07-16 03:14
 * @version: 1.0
 **/
@Data
public class MyRecommendedItem implements RecommendedItem {
    Long bookId;
    Long userId;
    Long rate;
    Long itemId;
    Long value;

    @Override
    public long getItemID() {
        return itemId;
    }

    @Override
    public float getValue() {
        return value;
    }
}
