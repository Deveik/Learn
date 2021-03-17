package org.xxx.search.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Deveik
 */
@Data
@NoArgsConstructor
public class SearchParam {
    private String fieldName;
    private String fieldDesc;
    private String fieldType;
    private String aliasName;

    public SearchParam(String fieldName, String fieldDesc, String fieldType) {
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
        this.fieldType = fieldType;
    }
}
