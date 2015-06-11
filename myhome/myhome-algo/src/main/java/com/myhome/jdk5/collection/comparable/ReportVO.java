package com.myhome.jdk5.collection.comparable;

import java.util.Calendar;

public class ReportVO {

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ( ( uploadedDate!=null)?uploadedDate.getTime():null) + " "+ name;
    }

    /**
	 * 
	 */
    private static final long serialVersionUString = 4205592137164140547L;

    private String nodeString;
    private String nodeName;
    private String name;
    private Calendar uploadedDate;
    private String uploadedBy;
    private String moduleName;
    private String customerCode;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the uploadedDate
     */
    public Calendar getUploadedDate() {
        return uploadedDate;
    }

    /**
     * @param uploadedDate
     *            the uploadedDate to set
     */
    public void setUploadedDate(Calendar uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    /**
     * @return the uploadedBy
     */
    public String getUploadedBy() {
        return uploadedBy;
    }

    /**
     * @param uploadedBy
     *            the uploadedBy to set
     */
    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    /**
     * @return the moduleName
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName
     *            the moduleName to set
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return the customerCode
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * @param customerCode
     *            the customerCode to set
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * @return the nodeString
     */
    public String getNodeString() {
        return nodeString;
    }

    /**
     * @param nodeString
     *            the nodeString to set
     */
    public void setNodeString(String nodeString) {
        this.nodeString = nodeString;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

}
