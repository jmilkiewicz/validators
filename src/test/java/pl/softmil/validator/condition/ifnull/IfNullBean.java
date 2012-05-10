package pl.softmil.validator.condition.ifnull;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.softmil.validator.condition.MyEnum;
import pl.softmil.validator.conditional.constraint.FieldNotNullIf;

@FieldNotNullIf(field = "cos", expression = "order == T(pl.softmil.validator.condition.MyEnum).RAZ")
@Data
@AllArgsConstructor
public class IfNullBean {
    private Boolean cos;
    private MyEnum order;
}
