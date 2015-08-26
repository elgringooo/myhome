package com.myhome.jdk5.thread.executor.persistasync.entity;

import java.io.Serializable;
import java.util.Date;

public class WebServiceInOut implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String webServiceIN;
    private String merchantId;
    private String method;
    private String direction;
    private String timeExec;
    private String timeExecGTM;
    private String data;
    private Date wsDate;
    private String externalTransactionID;
    private String origin;
    private String returnCodeId;

    public Date getWsDate() {
        return wsDate;
    }

    public void setWsDate(final Date wsDate) {
        this.wsDate = wsDate;
    }

    /**
     * @return Returns the data.
     */
    public String getData() {
        return data;
    }

    /**
     * @param data The data to set.
     */
    public void setData(final String data) {
        this.data = data;
    }

    /**
     * @return Returns the direction.
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction The direction to set.
     */
    public void setDirection(final String direction) {
        this.direction = direction;
    }

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return Returns the merchantId.
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId The merchantId to set.
     */
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return Returns the method.
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method The method to set.
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * @return Returns the timeExec.
     */
    public String getTimeExec() {
        return timeExec;
    }

    /**
     * @param timeExec The timeExec to set.
     */
    public void setTimeExec(final String timeExec) {
        this.timeExec = timeExec;
    }

    /**
     * @return Returns the timeExecGTM.
     */
    public String getTimeExecGTM() {
        return timeExecGTM;
    }

    /**
     * @param timeExecGTM The timeExecGTM to set.
     */
    public void setTimeExecGTM(final String timeExecGTM) {
        this.timeExecGTM = timeExecGTM;
    }

    /**
     * @return the webServiceIN
     */
    public String getWebServiceIN() {
        return webServiceIN;
    }

    /**
     * @param webServiceIN the webServiceIN to set
     */
    public void setWebServiceIN(final String webServiceIN) {
        this.webServiceIN = webServiceIN;
    }

    /**
     * Gets the external transaction id.
     * @return the external transaction id
     */
    public String getExternalTransactionID() {
        return externalTransactionID;
    }

    /**
     * Sets the external transaction id.
     * @param externalTransactionID the new external transaction id
     */
    public void setExternalTransactionID(final String externalTransactionID) {
        this.externalTransactionID = externalTransactionID;
    }

    /**
     * @return the origin of the WebService.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set.
     */
    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getReturnCodeId() {
        return returnCodeId;
    }

    public void setReturnCodeId(final String returnCodeId) {
        this.returnCodeId = returnCodeId;
    }

}
