package com.dodam.board.Repository;

import com.dodam.board.Entity.LikeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeyRepository extends JpaRepository<LikeyEntity, Integer> {

}
