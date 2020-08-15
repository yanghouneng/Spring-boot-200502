package com.hqyj.springBoot.modules.test.controller;

import com.hqyj.springBoot.modules.common.vo.Result;
import com.hqyj.springBoot.modules.test.entity.Card;
import com.hqyj.springBoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardService cardService;

    /*
    *127.0.0.1/api/inserCard
    * {"cardNo":"1545558fjafh"}
    * */
    @PostMapping(value = "/inserCard",consumes = "application/json")
    public Result<Card> inserCard(@RequestBody Card card){
        return cardService.insertCard(card);
    }
}
