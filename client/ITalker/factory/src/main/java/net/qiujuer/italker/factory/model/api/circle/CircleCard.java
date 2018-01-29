package net.qiujuer.italker.factory.model.api.circle;

public class CircleCard {


    public String id;
    public String name;
    public String portrait;

    public String description;

    public String imgs;

    public String personId;


    @Override
    public String toString() {
        return "CircleCard{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", portrait='" + portrait + '\'' +
                ", description='" + description + '\'' +
                ", imgs='" + imgs + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }
}
