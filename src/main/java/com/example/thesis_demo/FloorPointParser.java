package com.example.thesis_demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.thesis_demo.model.Record;
import com.opencsv.*;
import com.opencsv.bean.CsvToBeanBuilder;

public class FloorPointParser {

    public static void readAllData(InputStream inputStream) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            CSVReader csvReader = new CSVReader(bufferedReader);
            String[] nextRecord = csvReader.readNext();

            List<Record> records = new ArrayList<Record>();

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.println(cell + "\t");
                }
                System.out.println();

                Record record = new Record();
                record.setNhomNganh(nextRecord[0]);
                record.setTenNganh(nextRecord[1]);
                record.setMaNganh(nextRecord[2]);
                record.setTenTruong(nextRecord[3]);
                record.setMaTruong(nextRecord[4]);
                record.setTohopMon(nextRecord[5]);
                record.setDiemChuan(nextRecord[6]);
                records.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
