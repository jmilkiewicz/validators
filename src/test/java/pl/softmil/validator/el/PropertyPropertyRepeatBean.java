package pl.softmil.validator.el;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.softmil.validator.el.constraint.SpelAssert;

@Data
@SpelAssert(value = "prop == propRepeat", errorFields = { "prop", "propRepeat" }, message = "{password.passwordRepeat.mismatch}")
@AllArgsConstructor
public class PropertyPropertyRepeatBean {
    private final String prop;
    private final String propRepeat;
}
