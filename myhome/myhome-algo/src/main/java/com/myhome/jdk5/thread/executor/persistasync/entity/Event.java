package com.myhome.jdk5.thread.executor.persistasync.entity;

import java.util.Date;

public class Event {
    private String eventId;
    private String partnerId;
    private String merchantId;
    private String contractId;
    private String method;
    private String message;
    private String direction;
    private String externalId;
    private String sendEventId;
    private String exectime;
    private String comments;
    private String idUserCreate;
    private String idUserUpdate;
    private Date dateCreate;
    private Date dateUpdate;
    private Integer jourPartition;
    private Integer moisPartition;

    /**
     * @return the exectime
     */
    public String getExectime() {
        return exectime;
    }

    /**
     * @param exectime the exectime to set
     */
    public void setExectime(final String exectime) {
        this.exectime = exectime;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(final String comments) {
        this.comments = comments;
    }

    /**
     * @return the contractId
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * @param contractId the contractId to set
     */
    public void setContractId(final String contractId) {
        this.contractId = contractId;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(final String direction) {
        this.direction = direction;
    }

    /**
     * @return the eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * @param eventId the eventId to set
     */
    public void setEventId(final String eventId) {
        this.eventId = eventId;
    }

    /**
     * @return the sendEventId
     */
    public String getSendEventId() {
        return sendEventId;
    }

    /**
     * @param sendEventId the sendEventId to set
     */
    public void setSendEventId(final String sendEventId) {
        this.sendEventId = sendEventId;
    }

    /**
     * @return the idUserCreate
     */
    public String getIdUserCreate() {
        return idUserCreate;
    }

    /**
     * @param idUserCreate the idUserCreate to set
     */
    public void setIdUserCreate(final String idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    /**
     * @return the idUserUpdate
     */
    public String getIdUserUpdate() {
        return idUserUpdate;
    }

    /**
     * @param idUserUpdate the idUserUpdate to set
     */
    public void setIdUserUpdate(final String idUserUpdate) {
        this.idUserUpdate = idUserUpdate;
    }

    /**
     * @return the merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * @return the partnerId
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * @param partnerId the partnerId to set
     */
    public void setPartnerId(final String partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * @return the externalId
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * @param externalId the externalId to set
     */
    public void setExternalId(final String externalId) {
        this.externalId = externalId;
    }

    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * @return the dateUpdate
     */
    public Date getDateUpdate() {
        return dateUpdate;
    }

    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(final Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * @param dateUpdate the dateUpdate to set
     */
    public void setDateUpdate(final Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    /**
     * @return the jourPartition
     */
    public Integer getJourPartition() {
        return jourPartition;
    }

    /**
     * @param jourPartition the jourPartition to set
     */
    public void setJourPartition(final Integer jourPartition) {
        this.jourPartition = jourPartition;
    }

    /**
     * @return the moisPartition
     */
    public Integer getMoisPartition() {
        return moisPartition;
    }

    /**
     * @param moisPartition the moisPartition to set
     */
    public void setMoisPartition(final Integer moisPartition) {
        this.moisPartition = moisPartition;
    }

}
