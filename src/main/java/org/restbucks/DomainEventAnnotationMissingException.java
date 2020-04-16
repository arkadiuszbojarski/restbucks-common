package org.restbucks;

import java.util.Optional;

import static java.lang.String.format;

public class DomainEventAnnotationMissingException extends IllegalStateException {
    private static final String MESSAGE = "Cannot extract domain event type, because class %s doest not appear to be annotated with @%s annotation.";

    private static String extractEventClassName(Object event) {
        return Optional
                .ofNullable(event)
                .map(Object::getClass)
                .map(Class::getSimpleName)
                .orElse("null");
    }

    public static DomainEventAnnotationMissingException domainEventAnnotationMissing(Object event) {
        return new DomainEventAnnotationMissingException(event);
    }

    private static String extractDomainEventClassName() {
        return DomainEvent.class.getSimpleName();
    }

    private DomainEventAnnotationMissingException(Object event) {
        super(format(MESSAGE, extractEventClassName(event), extractDomainEventClassName()));
    }
}
