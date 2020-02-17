package com.company;

import java.sql.*;
import java.util.Date;

public class SchedulingConnection {

    public SchedulingConnection(){

        }

    // JDBC database URL
    static final String DB_URL = "jdbc:postgresql://kandula.db.elephantsql.com:5432/rxikhsae";

    //  Database credentials
    static final String USER = "rxikhsae";
    static final String PASS = "hrdIjtZaLpr4ynLFo1nwVxtoPfL-MJdp";
    private Iterator procIterator;
    private ProcessCollections processCollections;



    int timeStamp = (int) new Date().getTime();
    Connection connect(ProcessCollections processCollection) {
        Connection conn = null;
        Statement stmt = null;
        procIterator= processCollection.createIterator();
        try{

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
            while(procIterator.hasNext()) {
                Process proc = (Process) procIterator.next();
                sbArrival.append(proc.arrivalTime).append(",");
                sbBurst.append(proc.burst).append(",");
                sbidProc.append(proc.getIdProc()).append(",");
            }

            //convert StringBuffer to String
            String arrList = sbArrival.toString();
            String burstList = sbBurst.toString();
            String idProcList = sbidProc.toString();


            //remove last comma from String
            if( processCollection.getSize() > 0 ) {
                arrList = arrList.substring(0, arrList.length() - 1);
                burstList = burstList.substring(0, burstList.length() - 1);
                idProcList = idProcList.substring(0, idProcList.length() - 1);

            }

            System.out.println(arrList);
            String sql = "INSERT INTO process VALUES ( " + timeStamp + ", '{" + arrList + "}', '{" + burstList + "}', '{" + idProcList + "}'  )";

            stmt.executeUpdate(sql);
            System.out.println(sql);

            System.out.println("Inserted records into the table...");

        } catch(Exception se){
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally{
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

    public void delete(int IDtoDelete) {
        try {

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // our SQL SELECT query.
            String query = "DELETE FROM process WHERE \"ID\" = '"+IDtoDelete+"' ";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            st.executeUpdate(query);

        }  catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
