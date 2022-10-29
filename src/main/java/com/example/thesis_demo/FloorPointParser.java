package com.example.thesis_demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.example.thesis_demo.model.Record;
import com.opencsv.*;
import com.opencsv.bean.CsvToBeanBuilder;

public class FloorPointParser {

    public static List<Record> readAllData(InputStream inputStream) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            CSVReader csvReader = new CSVReader(bufferedReader);
            String[] nextRecord = csvReader.readNext();

            List<Record> records = new ArrayList<Record>();

            while ((nextRecord = csvReader.readNext()) != null) {
//                for (String cell : nextRecord) {
//                    System.out.println(cell + "\t");
//                }
//                System.out.println();

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

            return records;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Record> filter(String nganh, float[] scores, List<Record> records) {
        List<Record> filteredList = new ArrayList<>();
        float avgScore = average(scores, 5);
        for (Record record : records) {
            if (nganh.equals(record.getNhomNganh()) &&
                    avgScore >= Float.parseFloat(record.getDiemChuan())/4) {
                filteredList.add(record);
            }
        }
        return filteredList;
    }

    public static List<Record> getTop3(List<Record> records) {
        records.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                float diemChuan1 = Float.parseFloat(o1.getDiemChuan());
                float diemChuan2 = Float.parseFloat(o2.getDiemChuan());
                if (diemChuan1 > diemChuan2) return -1;
                if (diemChuan1 < diemChuan2) return 1;
                return 0;
            }
        });
        List<Record> top3 = new ArrayList<>();
        top3.add(records.get(0));
        top3.add(records.get(1));
        top3.add(records.get(2));
        return top3;
    }

    public static float average(float[] scores, int top) {
        float avgScore = 0;
        Arrays.sort(scores);
        int length = scores.length;
        for (int id = length - 1; id >= length - top; id--) {
            avgScore += scores[id];
        }
        avgScore /= top;
        return avgScore;
    }

}
