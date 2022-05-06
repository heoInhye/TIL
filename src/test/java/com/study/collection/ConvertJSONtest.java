package com.study.collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.*;

@Slf4j
public class ConvertJSONtest {

  @Test
  public void readFileWithoutBreakingKorean() {
    String filePath="C:\\Users\\user\\test\\파일명.txt";

    File file = new File(filePath);
    //FileReader fr;
    FileInputStream fis;
    InputStreamReader isr;

    try {
      fis = new FileInputStream(filePath);
      isr = new InputStreamReader(fis, "EUC-KR");
      BufferedReader br = new BufferedReader(isr);

      /* 샘플데이터. 사용자가 선택한 컬럼을 순서대로 저장해서 */
      String[] clientCol = {"lastName", "age"};
      for(String s : clientCol){
        log.info("!!!  {}", s);
      }

      /* 해당 컬럼의 위치를 파악하고 */
      String col = br.readLine();
      String[] colArr = col.split("\t");

      List<Integer> colIndex = new ArrayList<>();
      for(int i=0; i<clientCol.length; i++) {
        for(int j=0; j<colArr.length; j++) {
          //System.out.println(j);
          if(clientCol[i].equals( colArr[j] )) {
            colIndex.add(j);
          }
        }
      }

      System.out.println(colIndex);

      /* 해당 컬럼의 위치의 값을 읽어서 저장 */
      List values = new ArrayList();
      String line;
      while ((line = br.readLine()) != null) {
        String[] arr = line.split("\t");
        List value = new ArrayList();
        for(int i=0; i<colIndex.size(); i++) {
          value.add(arr[(int) colIndex.get(i)]);
        }
        values.add(value);
      }
      System.out.println(values);

    } catch (FileNotFoundException e1) {
      e1.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//

  @Test
  public void convertJSONtest00() {
    Map map = new HashMap();
    map.put("clct_nm","데이터수집명칭");
    map.put("clct_period_cd",  "데이터수집주기코드");
    map.put("clct_period_month", "데이터수집주기(월)");
    map.put("clct_period_time", "데이터수집주기(시)");
    map.put("storage_period_year", "데이터보관주기(년)");
    map.put("storage_period_month", "데이터보관주기(월)");
    map.put("creator_id", "test11");
    map.put("creat_datetime", new Date());

    map.put("method_cd", "A010001");
    map.put("col_nm", "파일명");

    map.put("recv_tbl_nm", "country");
    Map mapped_cols = new HashMap();
    mapped_cols.put("name","lastName");
    mapped_cols.put("age","id");
    map.put("mapped_cols", mapped_cols);

    Map mapped = (Map) map.get("mapped_cols");
    log.info("{}", mapped);

    List columns = new ArrayList();

    // list to string array
    List li = Arrays.asList(mapped.values().toArray());
    log.info("@@ {}",li);
    log.info("@@@@ {}", li.size());

    for(int a=0; a<li.size(); a++){
      log.info("{}",li.get(a));
      columns.add(li.get(a));
    }
    log.info("{}", columns);


//
//        String strKey = mapped.keySet().toString();
//        String[] arrKey = strKey.split(",");
//        for(String s : arrKey) log.info("@@ {}",s);



//        String mapToJson="";
//        try {
//            mapToJson = new ObjectMapper().writeValueAsString(map);
//            log.info("@ {}",mapToJson);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        Map jsonToMap = new HashMap();
//        try {
//            jsonToMap = new ObjectMapper().readValue(mapToJson, Map.class);
//            log.info("@@ {}", jsonToMap);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        Map col = (Map) jsonToMap.get("mapped_cols");
//        log.info("#{}",col);
//        log.info("#{}, ##{}", col.keySet(), col.values());




  }
}
