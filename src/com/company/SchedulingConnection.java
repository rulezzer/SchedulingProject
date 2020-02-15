package com.company;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class SchedulingConnection {

    public SchedulingConnection(){

        }

    // JDBC driver name and database URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:postgresql://kandula.db.elephantsql.com:5432/rxikhsae";

    //  Database credentials
    static final String USER = "rxikhsae";
    static final String PASS = "hrdIjtZaLpr4ynLFo1nwVxtoPfL-MJdp";
  private List<Process> processList;


    int timeStamp = (int) new Date().getTime();
    Connection connect(List<Process> processList) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
//            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            System.out.println("print");




            StringBuilder sbArrival = new StringBuilder("");
            StringBuilder sbBurst = new StringBuilder("");
            StringBuilder sbidProc = new StringBuilder("");


            //append ArrayList element followed by comma
            for(Process proc : processList) {
                sbArrival.append(proc.arrivalTime).append(",");
                sbBurst.append(proc.burst).append(",");
                sbidProc.append(proc.getIdProc()).append(",");
            }

            //convert StringBuffer to String
            String arrList = sbArrival.toString();
            String burstList = sbBurst.toString();
            String idProcList = sbidProc.toString();


            //remove last comma from String
            if( processList.size() > 0 ) {
                arrList = arrList.substring(0, arrList.length() - 1);
                burstList = burstList.substring(0, burstList.length() - 1);
                idProcList = idProcList.substring(0, idProcList.length() - 1);

            }

            System.out.println(arrList);
            String sql = "INSERT INTO process VALUES ( " + timeStamp + ", '{" + arrList + "}', '{" + burstList + "}', '{" + idProcList + "}'  )";

            stmt.executeUpdate(sql);
            System.out.println(sql);

            System.out.println("Inserted records into the table...");

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return conn;
    }//end main

    public int delete(int id) {
        String SQL = "DELETE FROM process WHERE " + timeStamp + " = ?";

        int affectedrows = 0;

        try (Connection conn = connect(processList);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, id);

            affectedrows = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }
}//end JDBCExample
