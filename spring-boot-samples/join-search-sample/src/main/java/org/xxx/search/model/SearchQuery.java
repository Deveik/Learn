package org.xxx.search.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xxx.search.constants.OperationType;

/**
 * @author Deveik
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchQuery extends SearchParam {
    private OperationType operationType;
    private String operation;
    private String searchValue;

    public SearchQuery(
            String fieldName,
            String operation,
            String searchValue) {
        super(fieldName, "", "");
        this.operationType = OperationType.valueOf(operation);
        this.operation = operation;
        this.searchValue = searchValue;
    }

    public SearchQuery(
            String fieldName,
            OperationType operationType,
            String searchValue) {
        super(fieldName, "", "");
        this.operationType = operationType;
        this.operation = operationType.getVal();
        this.searchValue = searchValue;
    }

    public SearchQuery(
            String fieldName,
            String fieldDesc,
            String fieldType,
            OperationType operationType,
            String searchValue) {
        super(fieldName, fieldDesc, fieldType);
        this.operationType = operationType;
        this.operation = operationType.getVal();
        this.searchValue = searchValue;
    }
}
