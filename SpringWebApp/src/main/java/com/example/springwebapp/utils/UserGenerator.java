package com.example.springwebapp.utils;

import com.example.springwebapp.pojo.User;
import com.example.springwebapp.valueObject.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * <p>
 *   Generate dummy users
 * </>
 *
 * This class is used to generate dummy users for testing
 * It writes a file "config.txt", with the following format:
 *  UserID,userTicket
 * The file is used by Jmeter CSV Data Set Config, to simulate 5000 users using the service
 *
 * !CHECK MYSQL FIRST TO AVOID DUPLICATE USERS!
 *
 */

public class UserGenerator {
    private static void generateUser(int amount) {
        List<User> users = new ArrayList<>(amount);
        for (int i=0; i<amount; i++) {
            User user = new User();
            user.setId(50000000000L + i);
            user.setNickname("dummy_" + i);
            user.setPassword("b7797cce01b4b131b433b6acf4add449");
            user.setSalt("1a2b3c4d");
            users.add(user);
        }
        /** uncomment this block when new users are needed **/
//        // create user
//        Connection conn = null;
//        try {
//            conn = getConnection();
//            String sql = "insert into t_user (id, nickname, password, salt, avater, last_login_date, login_count) values (?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement prep = conn.prepareStatement(sql);
//            for (User user : users) {
//                prep.setLong(1, user.getId());
//                prep.setString(2, user.getNickname());
//                prep.setString(3, user.getPassword());
//                prep.setString(4, user.getSalt());
//                prep.setString(5, null);
//                prep.setTimestamp(6, null);
//                prep.setInt(7, 0);
//                prep.addBatch();
//            }
//            prep.executeBatch();
//            prep.close();
//            conn.close();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        // login, generate token
        try {
            File file = new File("config.txt");
            if (file.exists()) {
                file.delete();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(0);
            for (User user: users) {
                URL url = new URL("http://localhost:8080/login/doLogin");
                HttpURLConnection co = (HttpURLConnection) url.openConnection();
                co.setRequestMethod("POST");
                co.setDoOutput(true);
                OutputStream out = co.getOutputStream();
                String params = "username=" + user.getId() + "&password=" + md5.inputPassToSaltedPass("123456");
                out.write(params.getBytes());
                out.flush();
                InputStream inputStream = co.getInputStream();
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                byte buff[] = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buff)) >= 0) {
                    bout.write(buff, 0, len);
                }
                inputStream.close();
                bout.close();
                String response = bout.toString();
                ObjectMapper mapper = new ObjectMapper();
                RespBean respBean = mapper.readValue(response, RespBean.class);
                String token = (String) respBean.getObj();
                String row = user.getId() + "," + token + "\n";
                raf.seek(raf.length());
                raf.write(row.getBytes());
                System.out.println("write to file: " + row);
            }
            raf.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        String Url = "jdbc:mysql://localhost:3306/springwebapp?useUnicode=true&characterEncoding=UTF-8&serverTimezone=America/New_York";
        String username = "root";
        String password = "root";
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        return DriverManager.getConnection(Url, username, password);
    }

    public static void main(String[] args) {
        generateUser(5000);
    }
}
