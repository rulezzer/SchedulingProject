package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProcessFactory {

    public ProcessInterface getClone(ProcessInterface processType) {
        return processType.makeCopy();
    }


}
