package com.example.administrator.myopenear.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class Fenglei {

    /**
     * classpasad : [{"bgColor":"","name":"时尚","id":24,"bgPicture":"http://img.wdjimg.com/image/video/b1dc8cc45addc496300c4dc87ba16e52_0_0.jpeg"},{"bgColor":"","name":"运动","id":18,"bgPicture":"http://img.wdjimg.com/image/video/b6c4d309809573728da5e627effe1738_0_0.jpeg"},{"bgColor":"","name":"旅行","id":6,"bgPicture":"http://img.wdjimg.com/image/video/3adcb2488d0431ac90193523133a76b7_0_0.jpeg"},{"bgColor":"","name":"剧情","id":12,"bgPicture":"http://img.wdjimg.com/image/video/f209d8f25c101aef55b64cfe9a245cf6_0_0.jpeg"},{"bgColor":"","name":"动画","id":10,"bgPicture":"http://img.wdjimg.com/image/video/821b37d0daaee553906df79237dae6f5_0_0.jpeg"},{"bgColor":"","name":"广告","id":14,"bgPicture":"http://img.wdjimg.com/image/video/5b33fb58f825a6dc893e56322e5f55c4_0_0.jpeg"},{"bgColor":"","name":"音乐","id":20,"bgPicture":"http://img.wdjimg.com/image/video/6af30fc4e6a96856619a9f2e8e745bf5_0_0.jpeg"},{"bgColor":"","name":"开胃","id":4,"bgPicture":"http://img.wdjimg.com/image/video/929c4231b8f3ed6cb6c2180fbe930f33_0_0.jpeg"},{"bgColor":"","name":"预告","id":8,"bgPicture":"http://img.wdjimg.com/image/video/f2b80f69c0b10fe484aacfc24d62645c_0_0.jpeg"},{"bgColor":"","name":"综合","id":16,"bgPicture":"http://img.wdjimg.com/image/video/6676c31afce1e79b40ad3d6c2c9ecc57_0_0.jpeg"},{"bgColor":"","name":"记录","id":22,"bgPicture":"http://img.wdjimg.com/image/video/bbbf63542576f27cc0ab1222601894fa_0_0.jpeg"},{"bgColor":"","name":"创意","id":2,"bgPicture":"http://img.wdjimg.com/image/video/9ead6e8356e42c3382a316f22bc5924e_0_0.jpeg"}]
     */
    private List<ClasspasadEntity> classpasad;

    public void setClasspasad(List<ClasspasadEntity> classpasad) {
        this.classpasad = classpasad;
    }

    public List<ClasspasadEntity> getClasspasad() {
        return classpasad;
    }

    public class ClasspasadEntity {
        /**
         * bgColor :
         * name : 时尚
         * id : 24
         * bgPicture : http://img.wdjimg.com/image/video/b1dc8cc45addc496300c4dc87ba16e52_0_0.jpeg
         */
        private String bgColor;
        private String name;
        private int id;
        private String bgPicture;

        public void setBgColor(String bgColor) {
            this.bgColor = bgColor;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setBgPicture(String bgPicture) {
            this.bgPicture = bgPicture;
        }

        public String getBgColor() {
            return bgColor;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getBgPicture() {
            return bgPicture;
        }
    }
}
