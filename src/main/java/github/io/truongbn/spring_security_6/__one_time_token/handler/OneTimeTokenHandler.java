package github.io.truongbn.spring_security_6.__one_time_token.handler;

import java.io.IOException;

import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import github.io.truongbn.spring_security_6.__one_time_token.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OneTimeTokenHandler implements OneTimeTokenGenerationSuccessHandler {
    private final EmailService emailService;
    private final OneTimeTokenGenerationSuccessHandler redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler(
            "/token/one-time");
    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response,
            final OneTimeToken oneTimeToken) throws IOException, ServletException {
        final UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath()).replaceQuery(null).fragment(null)
                .path("/login/ott").queryParam("token", oneTimeToken.getTokenValue());
        final String magicLink = builder.toUriString();
        final String body = generateEmailBody(magicLink);
        final String sendTo = oneTimeToken.getUsername();
        log.info("Sending One Time Token to user email. Email: {}", sendTo);
        final String toEmail = getUserEmail(oneTimeToken.getUsername());
        emailService.sendEmail(toEmail, "One Time Token Login", body);
        this.redirectHandler.handle(request, response, oneTimeToken);
    }

    private String getUserEmail(final String username) {
        // TODO: Add the logic to generate email from username
        return "ToEmail";
    }

    private String generateEmailBody(final String magicLink) {
        return """
                Your login link is ready.

                For a safe experience, use this secure link: %s
                """.formatted(magicLink);
    }
}
