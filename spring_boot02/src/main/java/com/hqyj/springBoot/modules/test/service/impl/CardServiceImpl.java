package com.hqyj.springBoot.modules.test.service.impl;

import com.hqyj.springBoot.modules.common.vo.Result;
import com.hqyj.springBoot.modules.test.entity.Card;
import com.hqyj.springBoot.modules.test.repostiory.CardRepository;
import com.hqyj.springBoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;
    @Override
    public Result<Card> insertCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<Card>(Result.ResultStats.SUCCESS.status,"insert success",card);
    }
}
