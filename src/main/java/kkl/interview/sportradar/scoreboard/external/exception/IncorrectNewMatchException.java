package kkl.interview.sportradar.scoreboard.external.exception;

import lombok.Getter;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class IncorrectNewMatchException extends RuntimeException {

    public static final String MESSAGE = "Cant create new match because of incorrect data. Violations: ";

    @Getter
    private final Set<Violation> violations;

    public IncorrectNewMatchException(Set<Violation> violations) {
        super(MESSAGE + violations);
        this.violations = unmodifiableSet(violations);
    }
}
