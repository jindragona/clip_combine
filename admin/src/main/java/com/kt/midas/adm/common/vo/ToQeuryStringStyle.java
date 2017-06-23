/*
 *  MIDAS version 1.0
 *
 *  Copyright ⓒ 2017 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */
package com.kt.midas.adm.common.vo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringStyle;

@SuppressWarnings("serial")
public class ToQeuryStringStyle extends ToStringStyle {

    private static List<String> excludeList = new ArrayList<String>();
    public static final ToStringStyle QUERYSTRING_STYLE = new ToQeuryStringStyle();

    static {
        ToQeuryStringStyle.setDefaultExcludeList();
    }

    private ToQeuryStringStyle() {
        super();
        super.setUseClassName(false);
        super.setUseIdentityHashCode(false);
        super.setContentStart("");
        super.setContentEnd("");
        super.setFieldNameValueSeparator("=");
        super.setFieldSeparator("&");
        super.setNullText("");
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, Object value,
            Boolean fullDetail) {
        if (!excludeList.contains(fieldName)) {
            appendFieldStart(buffer, fieldName);

            if (value == null) {
                appendNullText(buffer, fieldName);

            } else {
                if (value instanceof String) {
                    try {
                        appendInternal(buffer, fieldName, java.net.URLEncoder
                                .encode(((String) value), "UTF-8"),
                                isFullDetail(fullDetail));
                    } catch (UnsupportedEncodingException e) {
                        appendInternal(buffer, fieldName, value,
                                isFullDetail(fullDetail));
                    }
                } else {
                    appendInternal(buffer, fieldName, value,
                            isFullDetail(fullDetail));
                }
            }

            appendFieldEnd(buffer, fieldName);
        }
    }

    public static void setExcludeList(List<String> excludeList) {
        if (ToQeuryStringStyle.excludeList == null) {
            ToQeuryStringStyle.excludeList = excludeList;
            ToQeuryStringStyle.setDefaultExcludeList();
        } else {
            if (ToQeuryStringStyle.excludeList.size() <= 0) {
                ToQeuryStringStyle.setDefaultExcludeList();
            }
            if (excludeList != null) {
                ToQeuryStringStyle.excludeList.addAll(2, excludeList);
            }
        }
    }

    public static void removeExcludeList() {
        if (ToQeuryStringStyle.excludeList != null) {
            ToQeuryStringStyle.excludeList.clear();
            ToQeuryStringStyle.setDefaultExcludeList();
        }
    }

    private static void setDefaultExcludeList() {
        ToQeuryStringStyle.excludeList.add("errCd");
        ToQeuryStringStyle.excludeList.add("errMsg");
    }
}
