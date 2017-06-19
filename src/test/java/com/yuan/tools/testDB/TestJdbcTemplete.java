package com.yuan.tools.testDB;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yuan.tools.TestRootApplication;

public class TestJdbcTemplete extends TestRootApplication{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Test
    public void testJdbc(){
        Date date = jdbcTemplate.queryForObject("select now()", Date.class);
        System.out.println(date);
    }
    
    @Test
    public void testQuery(){
        String sql = "select * from t_task_config t";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> mp : map) {
            for (Map.Entry<String, Object> entry : mp.entrySet()) {
                System.out.println(entry.getKey() +  entry.getValue());
            }
        }
    }

}
