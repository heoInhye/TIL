package com.study.collection;

import java.util.List;
import java.util.Map;

public class DataCollection {

    private String clct_nm;                 //수집명
    private String clct_period_cd;          //수집주기코드
    private String clct_period_month;       //수집주기(월)
    private String clct_period_time;        //수집주기(시)
    private String storage_period_year;     //보관주기(년)
    private String storage_period_month;    //보관주기(개월)

    private String method_cd;               //수집방식(공통코드)
    private String col_nm;                  //파일명

    private String recv_tbl_nm;             //로컬 테이블명
    private Map<String,String> mapped_cols; //매핑된 컬럼

}
