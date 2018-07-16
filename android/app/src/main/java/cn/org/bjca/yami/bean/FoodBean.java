package cn.org.bjca.yami.bean;

import java.util.ArrayList;


/**
 * Created by 吴腾飞.
 */

public class FoodBean {
    private ArrayList<SetMeal> setMeal;
    private ArrayList<AddMaterial> addMaterial;

    public void setSetMeal(ArrayList<SetMeal> setMeal) {
        this.setMeal = setMeal;
    }

    public ArrayList<SetMeal> getSetMeal() {
        return setMeal;
    }

    public ArrayList<AddMaterial> getAddMaterial() {
        return addMaterial;
    }

    public void setAddMaterial(ArrayList<AddMaterial> addMaterial) {
        this.addMaterial = addMaterial;
    }

    public class SetMeal {

        private String setMealHead;
        private String setMealBody;

        public void setSetMealHead(String setMealHead) {
            this.setMealHead = setMealHead;
        }

        public String getSetMealHead() {
            return setMealHead;
        }

        public void setSetMealBody(String setMealBody) {
            this.setMealBody = setMealBody;
        }

        public String getSetMealBody() {
            return setMealBody;
        }

    }

    public class AddMaterial {
        private String addMaterialHead;
        private String addMaterialBody;

        public String getAddMaterialHead() {
            return addMaterialHead;
        }

        public void setAddMaterialHead(String addMaterialHead) {
            this.addMaterialHead = addMaterialHead;
        }

        public String getAddMaterialBody() {
            return addMaterialBody;
        }

        public void setAddMaterialBody(String addMaterialBody) {
            this.addMaterialBody = addMaterialBody;
        }
    }
}
