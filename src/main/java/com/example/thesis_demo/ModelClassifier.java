package com.example.thesis_demo;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelClassifier {

    private Attribute attrToan;
    private Attribute attrLi;
    private Attribute attrHoa;
    private Attribute attrSinh;
    private Attribute attrVan;
    private Attribute attrSu;
    private Attribute attrDia;
    private Attribute attrNgoaiNgu;

    private ArrayList attributes;
    private ArrayList classVal;
    private Instances dataRaw;

    public ModelClassifier() {
        attrToan = new Attribute("Toan");
        attrLi = new Attribute("Li");
        attrHoa = new Attribute("Hoa");
        attrSinh = new Attribute("Sinh");
        attrVan = new Attribute("Van");
        attrSu = new Attribute("Su");
        attrDia = new Attribute("Dia");
        attrNgoaiNgu = new Attribute("Ngoai_Ngu");

        attributes = new ArrayList<>();
        classVal = new ArrayList<>();
        classVal.add("Kinh doanh");
        classVal.add("Nghiên cứu");
        classVal.add("Xã hội");
        classVal.add("Kỹ thuật");
        classVal.add("Nghiệp vụ");
        classVal.add("Nghệ thuật");

        attributes.add(attrToan);
        attributes.add(attrLi);
        attributes.add(attrHoa);
        attributes.add(attrSinh);
        attributes.add(attrVan);
        attributes.add(attrSu);
        attributes.add(attrDia);
        attributes.add(attrNgoaiNgu);

        attributes.add(new Attribute("Nganh_Holland", classVal));
        dataRaw = new Instances("TestInstances", attributes, 0);
        dataRaw.setClassIndex(dataRaw.numAttributes() - 1);
    }

//    public Instances createInstance(String[] grade) {
//        dataRaw.clear();
//        Instance inst = new DenseInstance(9);
//
//        inst.setValue(attrToan, grade[0]);
//        inst.setValue(attrLi, grade[1]);
//        inst.setValue(attrHoa, grade[2]);
//        inst.setValue(attrSinh, grade[3]);
//        inst.setValue(attrVan, grade[4]);
//        inst.setValue(attrSu, grade[5]);
//        inst.setValue(attrDia, grade[6]);
//        inst.setValue(attrNgoaiNgu, grade[7]);
//        dataRaw.add(inst);
//        return dataRaw;
//    }


    public String classify(Instance inst, String path) {
        String result = "Not classified!!";
        Classifier cls = null;
        try {
            cls = (J48) SerializationHelper.read(path);
            result = (String) classVal.get((int) cls.classifyInstance(inst));
        } catch (Exception ex) {
            Logger.getLogger(ModelClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


    public Instances getInstance() {
        return dataRaw;
    }

}
