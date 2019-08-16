package com.zhang.controller;

import com.zhang.dto.ProductDto;
import com.zhang.service.KafkaService;
import com.zhang.service.RecommandService;
import com.zhang.util.Result;
import com.zhang.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class RecommandController {

    @Autowired
    RecommandService recommandService;

    @Autowired
    KafkaService kafkaService;
    /**
     * 返回推荐页面
     * @param userId
     * @return
     * @throws IOException
     */
    @GetMapping("/recommand")
    public String recommandByUserId(@RequestParam("userId") String userId,
                                    Model model) throws IOException {

        // 拿到不同推荐方案的结果
        List<ProductDto> hotList = recommandService.recommandByHotList();
        List<ProductDto> itemCfCoeffList = recommandService.recomandByItemCfCoeff();
        List<ProductDto> productCoeffList = recommandService.recomandByProductCoeff();

        // 将结果返回给前端
        model.addAttribute("userId", userId);
        model.addAttribute("hotList",hotList);
        model.addAttribute("itemCfCoeffList", itemCfCoeffList);
        model.addAttribute("productCoeffList", productCoeffList);

        return "user";
    }

    @GetMapping("/log")
    @ResponseBody
    public Result logToKafka(@RequestParam("id") String userId,
                             @RequestParam("prod") String productId,
                             @RequestParam("action") String action){

        String log = kafkaService.makeLog(userId, productId, action);
        kafkaService.send(null, log);
        return ResultUtils.success();
    }

}
