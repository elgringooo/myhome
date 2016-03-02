package com.myhome.domain.hbase;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Row {

    @XmlElement(name = "Cell")
    private List<Cell> cells;

    @XmlAttribute(name = "key")
    protected String key;

    public final List<Cell> getCells() {
        return cells;
    }

    public final void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public final String getKey() {
        return key;
    }

    public final void setKey(String key) {
        this.key = key;
    }

}
