package com.homefood.service;

import com.homefood.model.User;
import com.homefood.model.UserAuthenticationToken;

public interface UserAuthenticationTokenService {

	public UserAuthenticationToken createToken(User user);

	public void deleteToken(UserAuthenticationToken authenticationToken);

	public boolean isValidToken(String token);

	public void clearUnusedTokens();

	public UserAuthenticationToken findByToken(String token);

}
