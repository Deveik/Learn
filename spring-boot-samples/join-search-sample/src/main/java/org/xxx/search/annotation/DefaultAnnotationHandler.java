package org.xxx.search.annotation;

import org.xxx.search.constants.AlignType;
import org.xxx.search.constants.GroupViewType;
import org.xxx.search.constants.ZoomType;

import java.lang.annotation.Annotation;

import static org.xxx.search.constants.AlignType.CENTER;
import static org.xxx.search.constants.PropertiesConstants.*;
import static org.xxx.search.constants.ZoomType.*;

/**
 * @author Deveik
 */
public interface DefaultAnnotationHandler {
    /**
     * 公共模块处理列头信息
     * @return 默认列头信息
     */
    static ColumnHeader getDefaultHeader() {
        return new ColumnHeader() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return ColumnHeader.class;
            }

            @Override
            public String type() {
                return COL_TYPE_NORMAL;
            }

            @Override
            public int width() {
                return COL_PROP_WIDTH;
            }

            @Override
            public boolean hidden() {
                return false;
            }

            @Override
            public String label() {
                return "";
            }

            @Override
            public int priority() {
                return 999;
            }

            @Override
            public AlignType align() {
                return CENTER;
            }

            @Override
            public String[] relevance() {
                return new String[]{};
            }

            @Override
            public ZoomType[] zoom() {
                return new ZoomType[]{ CONDITION, GROUP, SORT };
            }

            @Override
            public String fieldType() {
                return STRING_VALUE;
            }

        };
    }

    /**
     * 公共模块处理选择信息
     * @return 默认选择信息
     */
    static QuerySelect getDefaultSelect() {
        return new QuerySelect() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return QuerySelect.class;
            }

            @Override
            public boolean exist() {
                return true;
            }

            @Override
            public String alias() {
                return "";
            }

            @Override
            public String from() {
                return "";
            }

            @Override
            public String[] depend() {
                return new String[]{};
            }

            @Override
            public boolean additive() {
                return false;
            }

            @Override
            public boolean defaultGroup() {
                return false;
            }

            @Override
            public GroupViewType defaultGroupView() {
                return null;
            }
        };
    }

}
