package com.hqyj.springBoot.modules.test.service;

import com.hqyj.springBoot.modules.common.vo.Result;
import com.hqyj.springBoot.modules.test.entity.Card;

public interface CardService {

    Result<Card> insertCard(Card card);
}
