package com.hqyj.springBoot.modules.test.repostiory;

import com.hqyj.springBoot.modules.test.entity.Card;
import org.apache.ibatis.annotations.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

}
