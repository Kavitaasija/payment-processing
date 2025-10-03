classDiagram
%% Core Entities
class Entity {
<<abstract>>
+String partitionKey
+String sortKey
+String id
}

    class Event {
        +EventType type
        +EventStatus status
        +ObservabilityMetadata observabilityMetadata
        +String payload
    }

class OrderedEventStatus {
+EventStatus status
+String eventSourceId
}

    class PollerLock {
        +Instant leasedAt
    }

    class PollerRegistration {
        +Instant registeredAt
    }

    class EventStatus {
        <<enumeration>>
        PENDING
    }

    class EventType {
        <<enumeration>>
        +String eventTypeIdentifier
    }

class ObservabilityMetadata {
+Map<String,String> metricTags
}

    %% Main Service Classes
    class DynamoDbEventQueueService {
        -EventPersistence
        -EventExecutor
        +lockEvent()
        +completeEvent()
    }

    class EventPollerJob {
        -EventRepository
        -EventPollerRouter
        +poll() void
    }

class ShardedEventPollerJob {
-EventPollerScope
+pollPartition(String partition) void
}

    class DelayedEventPollerJob {
        -EventRepository
        -EventOrderingService
        +pollDelayedEvents() void
    }

    class EventPollerRouter {
        -EventPollerHandler
        -EventOrderingService
        -EventRetryService
        +routeEvent()
    }

    class EventPollerHandler {
        -DynamoDbEventQueueService
        -EventExecutor
        +handleEvent()
    }

    %% Service Interfaces and Implementations
    class EventExecutor {
        <<interface>>
        +executeEvent(Event event) void
    }

class DefaultEventExecutor {
+executeEvent(Event event) void
}

    class TracedEventExecutor {
        -EventExecutor delegate
        +executeEvent(Event event) void
    }

    class EventOrderingService {
        -EventPersistence
        -OrderedEventStatusRepository
        +processOutOfOrderEvent()
    }

class EventRetryService {
-EventPersistence
+retryEvent()
}

    class LeaseExtensionService {
        -EventRepository
        +start(Event event)
    }

    %% Persistence Layer
    class EventPersistence {
        +save(Event event)
    }

    class EventRepository {
        <<interface>>
        +getPollerEvents()
        +extendLease()
    }

class EventRepositoryImpl {
+getPollerEvents()
}

    class OrderedEventStatusRepository {
        <<interface>>
        +findBySourceId()
    }

    class OrderedEventStatusRepositoryImpl {
        +findBySourceId()
    }

    class EventEntityFactory {
        +createProcessedEvent() Event
    }

    %% Observability and Metrics
    class EventQueueObservability {
        -EventQueueMetrics
        +registerPollerEventRead() void
    }

class EventQueueMetrics {
+registerPollerLatency() void
}

    class EventExecutionRegistry {
        +getExecutingEventsCount() int
    }

    %% Configuration
    class EventQueueConfiguration {
        +eventTableSchema()
    }

class EventQueueProperties {
+PollingProperties polling
}

    %% Utility Classes
    class EventPollerFilter {
        <<interface>>
        +filter(Event event) boolean
    }

    class DefaultEventPollerFilter {
        +filter(Event event) boolean
    }

    %% Inheritance Relationships
    Entity <|-- Event
    Entity <|-- OrderedEventStatus
    Entity <|-- PollerLock
    Entity <|-- PollerRegistration

    %% Composition and Aggregation Relationships
    Event ||--o ObservabilityMetadata : contains
    Event --> EventType : uses
    Event --> EventStatus : uses

    %% Service Dependencies
    DynamoDbEventQueueService --> EventPersistence
    DynamoDbEventQueueService --> EventExecutor
    DynamoDbEventQueueService --> EventOrderingService
    DynamoDbEventQueueService --> LeaseExtensionService
    DynamoDbEventQueueService --> EventRepository

    DefaultEventExecutor ..|> EventExecutor : implements
    TracedEventExecutor ..|> EventExecutor : implements

    EventPollerJob --> EventPollerRouter
    EventPollerJob --> EventExecutionRegistry

    EventPollerRouter --> EventPollerHandler
    EventPollerRouter --> EventOrderingService
    EventPollerRouter --> EventRetryService

    EventPollerHandler --> DynamoDbEventQueueService
    EventPollerHandler --> EventExecutor

    EventOrderingService --> OrderedEventStatusRepository
    EventRetryService --> EventPersistence

    EventPersistence --> EventRepository
    EventRepositoryImpl ..|> EventRepository : implements
    OrderedEventStatusRepositoryImpl ..|> OrderedEventStatusRepository : implements

    EventQueueObservability --> EventQueueMetrics
    EventExecutionRegistry --> EventQueueObservability

    EventQueueConfiguration --> EventQueueProperties

    DefaultEventPollerFilter ..|> EventPollerFilter : implements