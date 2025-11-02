package es.us.dp1.l4_01_25_26.petris.achievement;

import es.us.dp1.l4_01_25_26.petris.model.NamedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "achievements")
public class Achievement extends NamedEntity {

    @NotNull(message = "Description can't be null")
    @Size(min = 5, max = 100, message = "Description must be between 5 and 100 characters")
    @Column(nullable = false, length = 100)
    private String description;

    @NotBlank(message = "Badge image can't be blank")
    @Size(min = 5, max = 50, message = "Badge image must be between 5 and 50 characters")
    @Column(nullable = false, length = 50)
    private String badgeImage;

    
    @NotNull(message = "Metric can't be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Metric metric;

    
    @NotNull(message = "Threshold can't be null")
    @PositiveOrZero(message = "Threshold must be zero or positive")
    @Column(nullable = false)
    private Integer threshold = 0;

    public String getActualDescription() {
        return description.replace("<THRESHOLD>", String.valueOf(threshold));
    }
}