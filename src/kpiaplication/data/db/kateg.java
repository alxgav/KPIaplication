package kpiaplication.data.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by alxga on 19.11.2016.
 */
    @DatabaseTable(tableName = "tablename")

public class kateg {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String kateg;

    public kateg(String kateg) {
        this.kateg = kateg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKateg() {
        return kateg;
    }

    public void setKateg(String kateg) {
        this.kateg = kateg;
    }
}
