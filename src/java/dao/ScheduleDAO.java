/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;

/**
 *
 * @author ASUS
 */
public class ScheduleDAO extends DBContext{
    private static ScheduleDAO instance;
    private ScheduleDAO() {
        super();
    }

    public static ScheduleDAO getInstance() {
        if (instance == null) {
            instance = new ScheduleDAO();
        }
        return instance;
    }
    
    
}
