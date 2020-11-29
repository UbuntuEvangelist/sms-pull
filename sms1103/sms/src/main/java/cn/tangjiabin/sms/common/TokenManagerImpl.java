package cn.tangjiabin.sms.common;

import cn.tangjiabin.sms.pojo.Admin;
import cn.tangjiabin.sms.repository.AdminRepository;
import cn.tangjiabin.sms.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * 通过mysql存储和验证token的实现类
 */
@Service("tokenManager")
public class TokenManagerImpl implements TokenManager {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String secret = "R78{7(53!~3&>5}3}61^~LX,0m";
    private static final String issuer = "www.tangjiabin.cn";
    private static final String key = "user";


    @Override
    public TokenModel createToken(Integer userId) throws UnsupportedEncodingException {
        String token = JWT.create()
                .withIssuer(issuer)
                .withJWTId(UUID.randomUUID().toString().toUpperCase())
                .withClaim(key, userId)
                .sign(Algorithm.HMAC256(secret));
        return new TokenModel(userId, token);
    }

    @Override
    public TokenModel getToken(String token) throws UnsupportedEncodingException, JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        Integer userId = jwt.getClaim(key).asInt();
        return new TokenModel(userId, jwt.getToken());
    }

    @Override
    public boolean checkToken(String token) throws UnsupportedEncodingException, JWTVerificationException {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        TokenModel tokenModel = getToken(token);
        String adminToken = null;
        try {
            if (tokenModel == null) {
                return false;
            }
            adminToken = adminRepository.findByAdminId(tokenModel.getUserId()).getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String userToken = null;
        try {
            if (tokenModel == null) {
                return false;
            }
            userToken = userRepository.findByUserId(tokenModel.getUserId()).getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token.equals(adminToken) || token.equals(userToken);
    }

    @Override
    public void deleteToken(Integer userId) {
        Admin user = adminRepository.findByAdminId(userId);
        user.setToken("");
        adminRepository.save(user);
    }

}
