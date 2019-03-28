package cn.com.flaginfo.platform.littleProject.mongo.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "MetroLine")
public class MetroLine  extends BaseMongoDbModel{

    private String name;
    private String city;
    private List<String> stations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "MetroLine{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", stations=" + stations +
                ", id='" + id + '\'' +
                '}';
    }
}
