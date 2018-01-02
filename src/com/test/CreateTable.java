package com.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.dao.DBUtils;

public class CreateTable {
    /**
     * 建user表
     *
     */
    public static void main(String[] args) {
        Connection conn = DBUtils.getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS `user` (" +
                "`ID_num` VARCHAR (255) DEFAULT NULL, " +
                "`name` VARCHAR ( 255 ) DEFAULT NULL," +
                "`sex` VARCHAR ( 255 ) DEFAULT NULL," +
                "`age` VARCHAR ( 255 ) DEFAULT NULL," +
                "`balance` VARCHAR ( 255 ) DEFAULT NULL," +
                "`password` VARCHAR ( 255 ) DEFAULT NULL," +
                "`id` BIGINT ( 18 ) UNSIGNED NOT NULL AUTO_INCREMENT," +
                "`registered_city` VARCHAR ( 255 ) DEFAULT NULL," +
                "`adress` VARCHAR ( 255 ) DEFAULT NULL,"+
                "`phone_num` VARCHAR ( 255 ) DEFAULT NULL," +
                "PRIMARY KEY ( `id` ) " +
                "UNIQUE (`ID_num`)"+
                ") ENGINE = INNODB AUTO_INCREMENT = 10000000000000000 DEFAULT CHARSET = utf8;";
        Statement stat = null;
        try {
            stat = conn.createStatement();
            stat.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
