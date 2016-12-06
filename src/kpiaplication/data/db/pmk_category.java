package kpiaplication.data.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by alxga on 03.12.2016.
 */
@DatabaseTable(tableName = "pmk_category")
public class pmk_category {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private Integer parent_id;
    @DatabaseField
    private  String category;
    @DatabaseField
    private Double percent;

    public  pmk_category(){

    }

    public pmk_category(Integer parent_id, String category) {
        this.parent_id = parent_id;
        this.category = category;
    }

    public pmk_category(Integer parent_id, String category, Double percent) {
        this.parent_id = parent_id;
        this.category = category;
        this.percent = percent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}
