package com.packtpub.springmvc.dao;

import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;

public interface TokenDAO {
	 public VerificationToken findByToken(String token);

	 public VerificationToken findByUser(User user);
	 
	 public void saveVerificationTokenForUser(VerificationToken Token);

	 public void updateVerificationToken(VerificationToken token);
	 

}
