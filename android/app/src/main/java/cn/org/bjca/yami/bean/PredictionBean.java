package cn.org.bjca.yami.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class PredictionBean implements Serializable {

    private int retcode;//响应码
    private ArrayList<Food> foods;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public class Food implements Serializable {
        private String id;
        private String weekday;//周几
        private String peopleName;//人名
        private String setMeal1;//套餐一
        private String setMeal2;
        private String setMeal3;
        private String setMeal4;
        private String setMeal5;
        private String addMaterial1;//加料1
        private String addMaterial2;
        private String addMaterial3;
        private String addMaterial4;

        public Food(String id, String weekday, String peopleName,
                    String setMeal1, String setMeal2, String setMeal3, String setMeal4, String setMeal5,
                    String addMaterial1, String addMaterial2, String addMaterial3, String addMaterial4) {
            this.id = id;
            this.weekday = weekday;
            this.setMeal1 = setMeal1;
            this.setMeal2 = setMeal2;
            this.setMeal3 = setMeal3;
            this.peopleName = peopleName;
            this.setMeal4 = setMeal4;
            this.setMeal5 = setMeal5;
            this.addMaterial1 = addMaterial1;
            this.addMaterial4 = addMaterial4;
            this.addMaterial3 = addMaterial3;
            this.addMaterial2 = addMaterial2;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWeekday() {
            return weekday;
        }

        public void setWeekday(String weekday) {
            this.weekday = weekday;
        }

        public String getPeopleName() {
            return peopleName;
        }

        public void setPeopleName(String peopleName) {
            this.peopleName = peopleName;
        }

        public String getSetMeal1() {
            return setMeal1;
        }

        public void setSetMeal1(String setMeal1) {
            this.setMeal1 = setMeal1;
        }

        public String getSetMeal2() {
            return setMeal2;
        }

        public void setSetMeal2(String setMeal2) {
            this.setMeal2 = setMeal2;
        }

        public String getSetMeal3() {
            return setMeal3;
        }

        public void setSetMeal3(String setMeal3) {
            this.setMeal3 = setMeal3;
        }

        public String getSetMeal4() {
            return setMeal4;
        }

        public void setSetMeal4(String setMeal4) {
            this.setMeal4 = setMeal4;
        }

        public String getSetMeal5() {
            return setMeal5;
        }

        public void setSetMeal5(String setMeal5) {
            this.setMeal5 = setMeal5;
        }

        public String getAddMaterial1() {
            return addMaterial1;
        }

        public void setAddMaterial1(String addMaterial1) {
            this.addMaterial1 = addMaterial1;
        }

        public String getAddMaterial2() {
            return addMaterial2;
        }

        public void setAddMaterial2(String addMaterial2) {
            this.addMaterial2 = addMaterial2;
        }

        public String getAddMaterial3() {
            return addMaterial3;
        }

        public void setAddMaterial3(String addMaterial3) {
            this.addMaterial3 = addMaterial3;
        }

        public String getAddMaterial4() {
            return addMaterial4;
        }

        public void setAddMaterial4(String addMaterial4) {
            this.addMaterial4 = addMaterial4;
        }
    }
}
