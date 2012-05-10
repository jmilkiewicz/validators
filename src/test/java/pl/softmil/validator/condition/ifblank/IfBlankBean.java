package pl.softmil.validator.condition.ifblank;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.softmil.validator.condition.MyEnum;
import pl.softmil.validator.conditional.constraint.FieldNotBlankIf;

@FieldNotBlankIf(field = "cos", expression = "order == T(pl.softmil.validator.condition.MyEnum).RAZ")
@Data
@AllArgsConstructor
public class IfBlankBean {
    private String cos;
    private MyEnum order;
}
