package com.mele.rmi;

import java.math.BigInteger;

public interface IProfileService {

    boolean isUserExist(BigInteger uid);

    String getUserName(String uid);

    String getPasswd(String uid);

    String getUserGender(BigInteger bigInteger);

    String getUserAge(String uid);

	Integer getUserStatus(String uid);
}
