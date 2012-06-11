package pl.softmil.validator.condition.validateIf;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

import pl.softmil.validator.conditional.constraint.*;

@ValidatePropertyIf(field = "share", expression = "id != null")
@Data
public class MyBean {
    private Long id;
    @NotBlank(groups=ValidatePropertyIf.ValidatePropertyIfGroup.class)
    private String share;
}
