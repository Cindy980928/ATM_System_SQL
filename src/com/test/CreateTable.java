package com.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.dao.DBUtils;

public class CreateTable {
    /**
     * 建user表
     *
     * @param args
     */
    public static void main(String[] args) {
        Connection conn = DBUtils.getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS `user` (" +
                "`ID_num`  INT NOT NULL AUTO_INCREMENT," + // 身份证号
                "PRIMARY KEY(`ID_num`)," +
                "`name`  varchar(255)  NOT NULL ," +
                "`sex`  varchar(255)  NOT NULL ," +
                "`age`  varchar(255)  NOT NULL ," +
                "`balance`  varchar(255)  NOT NULL," +
                "`password`  varchar(255)  NOT NULL," +
                "`card_num` varchar(255)  NOT NULL ," + // 银行卡号
                "`registered_city`  varchar(255)  NOT NULL," +
                "`phone_num`  varchar(255)  NOT NULL);";
        Statement stat = null;
        try {
            stat = conn.createStatement();
            stat.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
