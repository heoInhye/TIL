package com.study.collection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/clct",
                consumes="application/json",
                produces="application/text;charset=utf-8")
public class DataCollectionController {

    private DataCollectionService service;

    @Autowired
    public DataCollectionController(DataCollectionService service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity collectData(@RequestBody DataCollection dc)
    {
        log.info("[controller] 데이터 수집중...");
        boolean result = service.collectData(dc);
        if(!result) {return ResponseEntity.badRequest().build();}

        return ResponseEntity.ok().build();
    }


}
