package org.restbucks;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public enum EventTypeExtractor {
    ;

    public static Optional<String> extractEventType(final Object event) {
        requireNonNull(event);
        final var eventClass = event.getClass();
        final var eventType = extractEventClassType(eventClass);

        return eventType;
    }

    public static Optional<String> extractEventClassType(final Class<?> eventClass) {
        requireNonNull(eventClass);
        final var annotation = eventClass.getAnnotation(DomainEvent.class);

        return Optional
                .ofNullable(annotation)
                .map(DomainEvent::type);
    }
}
