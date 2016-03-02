package com.myhome.domain.hbase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cell {
    @XmlAttribute(name = "timestamp")
    private String timestamp;

    @XmlAttribute(name = "column")
    private String column;

    public final String getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public final String getColumn() {
        return column;
    }

    public final void setColumn(String column) {
        this.column = column;
    }

}
