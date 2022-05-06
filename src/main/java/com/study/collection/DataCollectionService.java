package com.study.collection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Slf4j
@Service
public class DataCollectionService {

    //os에 맞는 구분자 값을 설정
    private final String sep = File.separator;

    private DataCollectionMapper mapper;

    @Autowired
    public DataCollectionService(DataCollectionMapper mapper){
        this.mapper=mapper;
    }

    public boolean collectData(DataCollection dc) {
        boolean result = true;

        //0. INSERT INTO clct_master_info
        int insertResult = mapper.insertIntoTable_clct_master_info(dc);
        if(insertResult == 0) { result=false; }
        log.info(" - - - - [clct_master_info 테이블에 저장 완료] - - - - ");


        String table = dc.getRecv_tbl_nm(); /*이 테이블에*/
        Map mapping = dc.getMapped_cols();
        log.info("@=> {}",mapping);

        StringBuffer columns = new StringBuffer();
        columns.append("(");
        Iterator<String> k = mapping.keySet().iterator();
        while(k.hasNext()){
            columns.append(k.next());
            columns.append(" ,");
        }
        columns.setLength(columns.length()-1);
        columns.append(")");
        log.info("columns=> {}", columns); /*이 컬럼에*/

        StringBuffer mappedColumns = new StringBuffer();
        //mappedColumns.append("{");
        Iterator<String> val = mapping.values().iterator();
        while(val.hasNext()){
            mappedColumns.append(val.next());
            mappedColumns.append(",");
        }
        mappedColumns.setLength(mappedColumns.length()-1);
        //mappedColumns.append("}");
        log.info("mappedColumns=> {}", mappedColumns);

        //2. 파일읽기 - 값 가져오기
        //파일 위치 조회하기
        String method_cd = dc.getMethod_cd();
        String file="";
        if(method_cd.equals("A010001")) { //DB

        }else if(method_cd.equals("A010002")) {//FILE
            String file_id = dc.getFile_id();
            String filePath = mapper.getFilePath(file_id);

            file=filePath+sep+dc.getCol_nm();
            log.info("@ 파일위치=> {}", file);

            StringBuffer sbVal = new StringBuffer();
            //파일 읽어서 값 가져오기
            List values = readFileNgetValues(file, mappedColumns.toString());
            for(int v=0; v<values.size(); v++){
                log.info("=> {}",values.get(v));
                sbVal.append(values.get(v));
                sbVal.append(",");
            }
            sbVal.setLength(sbVal.length()-1);

            insertMutipleRows(table,columns.toString(),sbVal.toString());
        }


        return result;
    }

    public boolean insertMutipleRows(String table, String col, String val)
    {
        boolean result=true;

        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO "+table);
        query.append(col);
        query.append("VALUES");
        query.append(val);


        log.info("!!! {}", query);
        return result;
    }

    public List readFileNgetValues(String filePath, String cols)
    {
        log.info("##{}, #{}", filePath, cols);
        String[] clientCol = cols.split(",");
        log.info("%% {}",clientCol.length);

        File file = new File(filePath);
        //FileReader fr;
        FileInputStream fis;
        InputStreamReader isr;

        List values = new ArrayList();

        try {
            fis = new FileInputStream(filePath);
            isr = new InputStreamReader(fis, "EUC-KR");
            BufferedReader br = new BufferedReader(isr);

            /* 샘플데이터. 사용자가 선택한 컬럼을 순서대로 저장해서 */
            //String[] clientCol = {"lastName", "age"};
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

            System.out.println("clientCol##" + clientCol);

            /* 해당 컬럼의 위치의 값을 읽어서 저장 */

            String line;
            while ((line = br.readLine()) != null) {

                String[] arr = line.split("\t");
                List value = new ArrayList();
                StringBuffer sb = new StringBuffer();
                sb.append("(");
                for(int i=0; i<colIndex.size(); i++) {
                    //value.add(arr[(int) colIndex.get(i)]);
                    sb.append("'");
                    sb.append(arr[(int) colIndex.get(i)]);
                    sb.append("',");
                }
                sb.setLength(sb.length()-1);
                sb.append(")");
                values.add(sb);
                //values.add(value);
            }
            System.out.println("values** " +values);

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }

}
