// IAccount.aidl
package com.demo.phy.phybasedemo.data.bean;
import com.demo.phy.phybasedemo.data.bean.User;
// Declare any non-default types here with import statements

interface IAccount {
       void setUser(in User user);
       User getUser();
       void setPasswrod(String password);
       String getPasswrod();
       void setId(String id);
       String getId();
}
