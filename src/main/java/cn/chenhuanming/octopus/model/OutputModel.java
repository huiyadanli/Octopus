package cn.chenhuanming.octopus.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by chenhuanming on 2017-07-01.
 *
 * @author chenhuanming
 */
@Getter
@Setter
public class OutputModel implements OutputSize {
    private Class clazz;
    private int height;
    private int width;
    private List<OutputField> fields;

    public OutputModel(Class clazz) {
        this.clazz = clazz;
        height = 1;
    }

    @Override
    public void calculateSize(){
        //calculate all fields it has
        maxFieldHeight(fields);
        adjustHeight(fields);
        this.height = fields.get(0).getHeight();
        this.width = sumFieldWidth(fields);
    }

    private void adjustHeight(List<OutputField> fields){
        if(fields.isEmpty())
            return ;
        int max = fields.get(0).getHeight();
        for (int i = 1; i < fields.size(); i++) {
            if(fields.get(i).getHeight()>max)
                max = fields.get(i).getHeight();
        }
        for (OutputField f:fields) {
            f.setHeight(max);
            adjustHeight(f.getFields());
        }

    }

}
