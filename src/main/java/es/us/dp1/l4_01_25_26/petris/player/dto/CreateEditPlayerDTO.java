package es.us.dp1.l4_01_25_26.petris.player.dto;

import java.util.List;

import es.us.dp1.l4_01_25_26.petris.authorities.Authorities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEditPlayerDTO {
    private String firstName;
    private String lastName;
    private String avatar;
    private String profileInfo;
    private Boolean isOnline;
    private String username;
    private String password;
    private Authorities authority; 
    private List<String> friends;       
    private List<String> achievements;  
}
