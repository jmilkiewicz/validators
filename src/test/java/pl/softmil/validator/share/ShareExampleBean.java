package pl.softmil.validator.share;

import lombok.Data;
import pl.softmil.validator.share.constraint.Share;

@Data
public class ShareExampleBean {
    @Share
    private String share;
}
