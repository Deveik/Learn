package org.xxx.search.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.xxx.search.constants.AlignType;
import org.xxx.search.constants.PropertiesConstants;

/**
 * @author Deveik
 */
@Data
@AllArgsConstructor
public class CustomColumn {
    private String fieldName;
    private String label;
    private int priority;

    private String type;

    private int width;
    private String align;
    private String[] references;

    public CustomColumn() {
        this.fieldName = "";
        this.label = "";
        this.priority = PropertiesConstants.COL_PROP_WIDTH;
        this.type = PropertiesConstants.COL_TYPE_NORMAL;
        this.align = AlignType.CENTER.getValue();
        this.references = new String[]{};
    }
}
