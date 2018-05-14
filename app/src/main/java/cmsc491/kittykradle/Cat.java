package cmsc491.kittykradle;

class Cat {
    private int id;
    private String name;
    private String gender;
    private String imgUrl;
    public static final String GENDER_MALE="male";
    public static final String GENDER_FEMALE="female";

    public Cat(){

    }
    public Cat(int id, String name, String gender,String imgUrl){
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.imgUrl=imgUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
