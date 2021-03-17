package org.xxx.search.constants;

/**
 * @author Deveik
 */

public enum AlignType {
    /**
     * 居中
     */
    CENTER("center"),
    /**
     * 左对齐
     */
    LEFT("left"),
    /**
     * 右对齐
     */
    RIGHT("right");

    private String value;

    AlignType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
