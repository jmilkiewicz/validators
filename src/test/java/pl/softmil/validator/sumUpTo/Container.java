package pl.softmil.validator.sumUpTo;

import java.util.*;

import lombok.Data;

import pl.softmil.validator.sumUpTo.constraint.SumUpTo;
import pl.softmil.validator.sumUpTo.impl.AsBigDecimal;

@Data
public class Container {
    @SumUpTo
    private List<AsBigDecimal> elems = new LinkedList<AsBigDecimal>();
}
