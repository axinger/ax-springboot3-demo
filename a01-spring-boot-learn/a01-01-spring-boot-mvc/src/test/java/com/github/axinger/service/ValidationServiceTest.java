package com.github.axinger.service;

import cn.hutool.core.date.DateUtil;
import com.github.axinger.model.dto.DateDTO;
import com.github.axinger.model.dto.ParamDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class ValidationServiceTest {

    @Autowired
    ValidationService validationService;
//    ValidationServiceImpl validationService;


    @Test
    void test1() {
        validationService.strNo(null);
    }

    @Test
    void strNotBlank_1() {
        validationService.strNotBlank(null); // 不通过
    }

    @Test
    void strNotBlank_2() {
        validationService.strNotBlank(""); // 不通过
    }

    @Test
    void strNotBlank_3() {
        validationService.strNotBlank(" "); //不通过
    }

    @Test
    void strNotEmpty_1() {
        validationService.strNotEmpty(null); // 不通过
    }

    @Test
    void strNotEmpty_2() {
        validationService.strNotEmpty(""); // 不通过
    }

    @Test
    void strNotEmpty_3() {
        validationService.strNotEmpty(" "); //通过
    }

    @Test
    void funOne_1() {
        ParamDTO dto = new ParamDTO();
        validationService.funOne(dto);
    }

    @Test
    void funOne_2() {
        ParamDTO dto = new ParamDTO();
        dto.setName("jim");
        validationService.funOne(dto);
    }

    @Test
    void funOne_3() {
        ParamDTO dto = new ParamDTO();
        dto.setDate(new Date());
        validationService.funOne(dto);
    }

    @Test
    void funOne_4() {
        ParamDTO dto = new ParamDTO();
        dto.setName("jim");
        dto.setDate(new Date());
        validationService.funOne(dto);
    }

//    @Test
//    void testDate_1(){
//        DateDTO dto = new DateDTO();
//        dto.setStart(LocalDateTime.now());
//        dto.setEnd(LocalDateTime.now());
//        validationService.testDate(dto);
//    }
//    @Test
//    void testDate_2(){
//        DateDTO dto = new DateDTO();
//        dto.setStart(LocalDateTime.now());
//        dto.setEnd(LocalDateTime.now().plusDays(-1));
//        validationService.testDate(dto);
//    }

    @Test
    void testDate_3() {
        DateDTO dto = new DateDTO();
        dto.setBeginTime(DateUtil.parse("2020-01-02 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        dto.setEndTime(DateUtil.parse("2020-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        validationService.testDate(dto);
    }

    @Test
    void testDate_4() {
        DateDTO dto = new DateDTO();
        dto.setBeginTime(DateUtil.parse("2020-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        dto.setEndTime(DateUtil.parse("2020-01-02 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        validationService.testDate(dto);
    }
}
