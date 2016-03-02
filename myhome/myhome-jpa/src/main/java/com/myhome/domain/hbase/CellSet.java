package com.myhome.domain.hbase;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CellSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class CellSet {

    @XmlElement(name = "Row")
    private List<Row> rows;

    public final List<Row> getRows() {
        return rows;
    }

    public final void setRows(List<Row> rows) {
        this.rows = rows;
    }

}
