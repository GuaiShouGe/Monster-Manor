package com.example.administrator.myopenear.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/28.
 */
public class CategoryListBean {

    /**
     * id : 24
     * name : 时尚
     * alias : null
     * bgPicture : http://img.wdjimg.com/image/video/b1dc8cc45addc496300c4dc87ba16e52_0_0.jpeg
     * bgColor :
     */

    private List<CategoryBeanEntity> CategoryBean;

    public List<CategoryBeanEntity> getCategoryBean() {
        return CategoryBean;
    }

    public void setCategoryBean(List<CategoryBeanEntity> CategoryBean) {
        this.CategoryBean = CategoryBean;
    }

    public static class CategoryBeanEntity {
        private int id;
        private String name;
        private Object alias;
        private String bgPicture;
        private String bgColor;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAlias() {
            return alias;
        }

        public void setAlias(Object alias) {
            this.alias = alias;
        }

        public String getBgPicture() {
            return bgPicture;
        }

        public void setBgPicture(String bgPicture) {
            this.bgPicture = bgPicture;
        }

        public String getBgColor() {
            return bgColor;
        }

        public void setBgColor(String bgColor) {
            this.bgColor = bgColor;
        }
    }
}
