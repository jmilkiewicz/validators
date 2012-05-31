package pl.softmil.validator.roi;

import java.math.BigDecimal;

import pl.softmil.validator.roi.constraint.Roi;

import lombok.*;

@Data
@AllArgsConstructor
public class RoiExampleBean {
    @Roi
    private final BigDecimal value;
}
