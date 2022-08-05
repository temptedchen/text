package com.cy.bean;


public class TbPower {

  private long powerId;
  private String powerName;
  private String requestCon;
  private long parentId;


  public long getPowerId() {
    return powerId;
  }

  public void setPowerId(long powerId) {
    this.powerId = powerId;
  }


  public String getPowerName() {
    return powerName;
  }

  public void setPowerName(String powerName) {
    this.powerName = powerName;
  }


  public String getRequestCon() {
    return requestCon;
  }

  public void setRequestCon(String requestCon) {
    this.requestCon = requestCon;
  }


  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }

}
