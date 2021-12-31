package resell.shoes.RShoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    READY("READY"),
    START("START"),
    ARRIVE("ARRIVE");

    private final String value;
}
