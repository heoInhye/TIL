package com.study.collection;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataCollectionMapper {

    public int insertIntoTable_clct_master_info(DataCollection dc);

    public String getFilePath(String fileId);

}
