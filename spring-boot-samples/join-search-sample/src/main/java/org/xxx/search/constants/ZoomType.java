package org.xxx.search.constants;

/**
 * @author Deveik
 */
public enum ZoomType {
    /**
     * 所有条件
     */
    ZOOM_NONE(0),
    /**
     * 所有条件
     */
    ZOOM_ALL(7),
    /**
     * 筛选条件
     */
    CONDITION(1),
    /**
     * 聚合条件
     */
    GROUP(2),
    /**
     * 排序条件
     */
    SORT(4);

    int val;
    ZoomType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static boolean checkInclude(ZoomType source, ZoomType includeVal) {
        return (source.getVal() & includeVal.getVal()) == includeVal.getVal();
    }

    public static boolean checkInclude(ZoomType[] source, ZoomType includeVal) {
        int inVal = includeVal.getVal();
        if (inVal == ZOOM_NONE.getVal()) {
            return true;
        }
        if (source == null || source.length == 0) {
            return false;
        }
        for (ZoomType zoomType : source) {
            int val = zoomType.getVal();
            if (val == inVal || val == ZOOM_ALL.getVal()) {
                return true;
            }
        }
        return false;
    }

}
