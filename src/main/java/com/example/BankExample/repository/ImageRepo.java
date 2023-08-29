package com.example.BankExample.repository;

import com.example.BankExample.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image,Long> {
    @Query(value = "SELECT image_id FROM users WHERE user_id = :id", nativeQuery = true)
    public long returnImageId(@Param("id")int id);
}
