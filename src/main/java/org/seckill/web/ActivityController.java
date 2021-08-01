package org.seckill.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 重构后的秒杀核心接口
 */
@RestController
@RequestMapping("activity")
public class ActivityController {

    //获取专属秒杀接口，可缓存
    @GetMapping("/{goodsId}")
    public String getSecKillUrl(@RequestParam("userPhone") String phone,
                                @PathVariable("goodsId") String goodsId) {
        return "";
    }

    //提交秒杀请求，限流，入队列
    @PostMapping("/{path}")
    public ResponseEntity secKill(@PathVariable String path) {
        return ResponseEntity.ok("");
    }

    //查询秒杀结果，限流，缓存
    @GetMapping("/{path}")
    public ResponseEntity query(@PathVariable String path) {
        return ResponseEntity.ok("");
    }
}
