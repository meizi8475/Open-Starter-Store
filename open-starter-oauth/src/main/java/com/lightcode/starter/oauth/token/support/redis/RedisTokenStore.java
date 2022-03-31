package com.lightcode.starter.oauth.token.support.redis;

import com.lightcode.starter.oauth.authentication.Authentication;
import com.lightcode.starter.oauth.domain.UserDetails;
import com.lightcode.starter.oauth.enums.ResultEnum;
import com.lightcode.starter.oauth.exception.AuthenticationException;
import com.lightcode.starter.oauth.properties.OAuthProperties;
import com.lightcode.starter.oauth.properties.token.TokenProperties;
import com.lightcode.starter.oauth.token.AbstractTokenStore;
import com.lightcode.starter.oauth.token.AccessToken;
import com.lightcode.starter.oauth.token.TokenEnhancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description: Redis token store，把 token 及其映射存储到 Redis 中
 */
@Slf4j
public class RedisTokenStore extends AbstractTokenStore {

    private static final String ACCESS_TOKEN_KEY = "accessToken::";
    private final RedisTemplate<String, Object> redisTemplate;
    private final OAuthProperties oauthProperties;

    public RedisTokenStore(TokenEnhancer tokenEnhancer, RedisTemplate<String, Object> redisTemplate, OAuthProperties oauthProperties) {
        super(tokenEnhancer);
        this.redisTemplate = redisTemplate;
        this.oauthProperties = oauthProperties;
    }

    @Override
    public AccessToken doGenerateToken(Authentication authentication) {
        UserDetails userDetails = authentication.getUserDetails();
        final TokenProperties tokenProperties = oauthProperties.getToken();
        AccessToken token = new AccessToken();
        String accessToken = UUID.randomUUID().toString();
        long now = System.currentTimeMillis();
        long expiredTime = now + tokenProperties.getAccessTokenExpiresIn() * 1000;
        token.setExpiredTime(String.valueOf(expiredTime));
        token.setAccessToken(accessToken);
        redisTemplate.opsForValue().set(buildAccessTokenKey(accessToken), userDetails, tokenProperties.getAccessTokenExpiresIn(), TimeUnit.SECONDS);
        return token;
    }

    @Override
    public Authentication readAuthentication(String accessToken){
        Object o = redisTemplate.opsForValue().get(buildAccessTokenKey(accessToken));
        if (Objects.isNull(o)){
            throw new AuthenticationException(ResultEnum.UNAUTHORIZED);
        }
        UserDetails userDetails = (UserDetails) o;
        Authentication authentication = new Authentication();
        authentication.setUserDetails(userDetails);
        return authentication;
    }

    private String buildAccessTokenKey(String accessToken){
        return ACCESS_TOKEN_KEY + accessToken;
    }



}
