package hu.minelush.backend.creator;

public record ContentCreatorResponse(
    Long id,
    String handle,
    Integer subscriberCount,
    String imageUrl,
    String channelUrl
    ) {
    static ContentCreatorResponse from(ContentCreator creator) {
        return new ContentCreatorResponse(
            creator.getId(),
            creator.getHandle(),
            creator.getSubscriberCount(),
            creator.getImageUrl(),
            creator.getChannelUrl()
        );
    }
}