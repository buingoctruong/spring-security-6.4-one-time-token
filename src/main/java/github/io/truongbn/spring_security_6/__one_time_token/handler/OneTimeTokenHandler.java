package github.io.truongbn.spring_security_6.__one_time_token.handler;

import java.io.IOException;

import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OneTimeTokenHandler implements OneTimeTokenGenerationSuccessHandler {
    private final OneTimeTokenGenerationSuccessHandler redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler(
            "/ott/sent");
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            OneTimeToken oneTimeToken) throws IOException, ServletException {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath()).replaceQuery(null).fragment(null)
                .path("/login/ott").queryParam("token", oneTimeToken.getTokenValue());
        String magicLink = builder.toUriString();
        // send email
        System.out.println("Magic Link: " + magicLink);
        this.redirectHandler.handle(request, response, oneTimeToken);
    }
}
