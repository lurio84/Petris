package es.us.dp1.l4_01_25_26.petris.configuration;

//#region Imports
import static org.springframework.security.config.Customizer.withDefaults;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//############CORS############
import java.util.List;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//############CORS############

import es.us.dp1.l4_01_25_26.petris.configuration.jwt.AuthEntryPointJwt;
import es.us.dp1.l4_01_25_26.petris.configuration.jwt.AuthTokenFilter;
import es.us.dp1.l4_01_25_26.petris.configuration.services.UserDetailsServiceImpl;

//#endregion

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    DataSource dataSource;

    private static final String ADMIN = "ADMIN";
    private static final String PLAYER = "PLAYER";

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .cors(withDefaults())
                // Si usas H2, conviene ignorar su CSRF:
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))

                .authorizeHttpRequests(auth -> auth
                        // Recursos estáticos comunes (css, js, images, webjars…) públicos
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // H2 Console accesible
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        // Raíz / páginas públicas
                        .requestMatchers("/", "/oups").permitAll()

                        // Swagger / OpenAPI accesible
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources/**")
                        .permitAll()

                        // API pública
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/developers").permitAll()
                        .requestMatchers("/api/v1/plan").permitAll()

                        // USER
                        .requestMatchers("/api/v1/users/**").permitAll()

                        // PLAYER
                        .requestMatchers(HttpMethod.GET, "api/v1/players/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/players/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "api/v1/players/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "api/v1/players/**").permitAll()

                        // PLAYER STATISTICS
                        .requestMatchers(HttpMethod.GET, "api/v1/playerstatistics/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/playerstatistics/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "api/v1/playerstatistics/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "api/v1/playerstatistics/**").permitAll()

                        // GAMES
                        .requestMatchers(HttpMethod.GET, "api/v1/games/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/games/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/games/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/games/**").permitAll()

                        // GAME STATISTICS
                        .requestMatchers(HttpMethod.GET, "api/v1/game-statistics/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/game-statistics/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/game-statistics/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/game-statistics/**").permitAll()

                        // ACHIEVEMENTS
                        .requestMatchers(HttpMethod.GET, "/api/v1/achievements/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/achievements/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/achievements/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/achievements/**").permitAll()

                        // El resto denegado
                        .anyRequest().denyAll())

                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // #region CORS HEADERS
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://127.0.0.1:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    // #endregion

}
