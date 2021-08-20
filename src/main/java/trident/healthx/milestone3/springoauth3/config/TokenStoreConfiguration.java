package trident.healthx.milestone3.springoauth3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class TokenStoreConfiguration {



    @Value("${password}")
    private String password;

    @Value("${privatekey}")
    private String privatekey;

    @Value("${alias}")
    private String alias;



    public JwtAccessTokenConverter jwtAccessTokenConverter () {
        var converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(privatekey), password.toCharArray());

        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(alias));

        return converter;
    }


}
