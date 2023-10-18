package com.example.test;

import org.junit.jupiter.api.Test;
import org.paratranz.bot.api.ParaTranzApi;
import org.paratranz.bot.api.entity.PageResult;
import org.paratranz.bot.api.entity.data.Application;
import org.paratranz.bot.api.entity.data.Audit;

import java.util.List;

public class ApiTest {

    private final ParaTranzApi paraTranzApi = new ParaTranzApi("5828fb55756dbf6ebb4f76937c16e530");
    @Test
    public void test1() {
        ParaTranzApi.Apply apply = paraTranzApi.apply;
        PageResult<Application> pageResult = apply.listApply(967);
        System.out.println("pageResult = " + pageResult);

        List<Audit> testContent = apply.getTestContent(pageResult.getResults().get(0).getId(), pageResult.getResults().get(0).getProject());

        System.out.println("testContent = " + testContent);
    }
}
