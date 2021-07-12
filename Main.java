package com.company;

import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        try {
            InputStream in = new FileInputStream(new File(args[0]));
            prop.load(in);

            String folder = prop.getProperty("Directory");
            Scanner scnr = new Scanner(System.in);
            Boolean run = true;
            while (run) {
                List<String> infoList;
                if (prop.getProperty("ListType").equals("ArrayList")) {
                    infoList = new ArrayList<>();
                } else {
                    infoList = new LinkedList<>();
                }
                System.out.println("Enter Name of Person (or EXIT to quit) :");
                String name = scnr.nextLine();
                if (name.equals("Exit") || name.equals("EXIT")) {
                    break;
                }
                infoList.add(name);
                System.out.println("Gender (M/F) :");
                String gender = scnr.nextLine();
                while(!gender.equals("M")&&!gender.equals("F")){
                    System.out.println("Please input a valid gender");
                    System.out.println("Gender (M/F) :");
                    gender = scnr.nextLine();
                }
                System.out.println("State of birth (two-letter state code) :");
                String State = scnr.nextLine();
                infoList.add("0");
                infoList.add("0");
                File file = new File(folder + "/" + State + ".txt");
                while(!file.exists()) {
                    System.out.println("Enter a valid State.");
                    System.out.println("State of birth (two-letter state code) :");
                    State = scnr.nextLine();
                    file = new File(folder + "/" + State + ".txt");
                }
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                line = br.readLine();
                while (line != null) {
                    String[] lines = line.split(",");
                    if ((Integer.parseInt(lines[4]) > Integer.parseInt(infoList.get(1))) && lines[3].equals(name) && lines[1].equals(gender)) {
                        infoList.remove(1);
                        infoList.add(1, lines[4]);
                        infoList.remove(2);
                        infoList.add(lines[2]);
                    }
                    line = br.readLine();
                }
                br.close();
                if (Integer.parseInt(infoList.get(2)) == 0) {
                    System.out.println("No age hypothesis please enter new information");
                } else {
                    System.out.println(infoList.get(0) + ", born in " + State + " is most likely around " + (2021 - Integer.parseInt(infoList.get(2))) + " years old");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
