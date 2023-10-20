package com.example.test;

import cn.hutool.http.HttpException;
import org.junit.jupiter.api.Test;
import org.paratranz.bot.api.ParaTranzApi;
import org.paratranz.bot.api.entity.PageResult;
import org.paratranz.bot.api.entity.data.Application;
import org.paratranz.bot.api.entity.data.Audit;
import org.paratranz.bot.api.entity.terms.TermConfig;
import org.paratranz.bot.api.entity.terms.TermConfigRes;
import org.paratranz.bot.api.entity.terms.TermDetail;

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

    @Test
    public void termTest() {
        int projectId = 5727;
        ParaTranzApi.Terms terms = paraTranzApi.terms;

        try {
            TermConfig config = TermConfig.of(TermConfig.PosEnum.ADJ, "prettier", "漂亮", "通常用于形容一个人很漂亮", null);

            TermConfigRes res = terms.createTerm(projectId, config);
            System.out.println("创建术语 = " + res);

            PageResult<TermDetail> termsPageResult = terms.listTerms(projectId);
            System.out.println("列举术语 = " + termsPageResult);

            TermConfigRes termDetail = terms.getTermDetail(5727, termsPageResult.getResults().get(0).getId());
            System.out.println("获取术语详情 = " + termDetail);

            TermConfigRes termConfigRes = terms.editTerm(projectId, termsPageResult.getResults().get(0).getId(), config
                    .setVariants(new String[]{"Pretty"}));
            System.out.println("编辑术语 = " + termConfigRes);

            boolean b = terms.deleteTerm(projectId, termsPageResult.getResults().get(0).getId());
            System.out.println("删除术语 = " + b);

            PageResult<TermDetail> termDetailPageResult = terms.listTerms(projectId);
            System.out.println("termDetailPageResult = " + termDetailPageResult);
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void artifactsTest() {
        int projectId = 5727;

        ParaTranzApi.Artifacts artifacts = paraTranzApi.artifacts;

        long total = artifacts.downloadArtifacts(projectId, "opt/artifacts.zip");
        System.out.println("总下载大小 = " + total + " byte");

    }
}
