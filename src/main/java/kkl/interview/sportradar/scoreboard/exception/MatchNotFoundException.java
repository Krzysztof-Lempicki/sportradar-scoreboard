package kkl.interview.sportradar.scoreboard.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MatchNotFoundException extends RuntimeException {

    public static final String MESSAGE = "Football match with given id does not exist. Id was: ";
    private final UUID id;

    public MatchNotFoundException(UUID id) {
        super(MESSAGE + id);
        this.id = id;
    }
}
