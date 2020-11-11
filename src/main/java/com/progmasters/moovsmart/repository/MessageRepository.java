package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.Message;
import com.progmasters.moovsmart.domain.MessageIncomingState;
import com.progmasters.moovsmart.domain.PropertyState;
import com.progmasters.moovsmart.dto.MessageListInnerFilter;
import com.progmasters.moovsmart.dto.MessageThreadFilter;
import com.progmasters.moovsmart.dto.PropertyDetailsMessageInitData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m " +
            "WHERE m.incomingState = :#{#messageListInnerFilter.messageIncomingState} " +
            "AND m.from.email <> :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email = :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.timestampCreated > :#{#messageListInnerFilter.startDateTime} " +
            "AND m.timestampCreated < :#{#messageListInnerFilter.endDateTime}")
    Page<Message> findAllWithFilterIncoming(Pageable paging, MessageListInnerFilter messageListInnerFilter);

    @Query("SELECT count(m.id) FROM Message m " +
            "WHERE m.incomingState = :#{#messageListInnerFilter.messageIncomingState} " +
            "AND m.from.email <> :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email = :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.timestampCreated > :#{#messageListInnerFilter.startDateTime} " +
            "AND m.timestampCreated < :#{#messageListInnerFilter.endDateTime}")
    Integer countAllByIdWithFilterIncoming(MessageListInnerFilter messageListInnerFilter);

    @Query("SELECT m FROM Message m " +
            "WHERE m.incomingState = :#{#messageListInnerFilter.messageIncomingState} " +
            "AND m.from.email <> :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email = :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.timestampCreated > :#{#messageListInnerFilter.startDateTime} " +
            "AND m.timestampCreated < :#{#messageListInnerFilter.endDateTime} " +
            "AND m.property.id = :#{#messageListInnerFilter.propertyId}")
    Page<Message> findAllWithFilterIncomingProperty(Pageable paging, MessageListInnerFilter messageListInnerFilter);

    @Query("SELECT count(m.id) FROM Message m " +
            "WHERE m.incomingState = :#{#messageListInnerFilter.messageIncomingState} " +
            "AND m.from.email <> :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email = :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.timestampCreated > :#{#messageListInnerFilter.startDateTime} " +
            "AND m.timestampCreated < :#{#messageListInnerFilter.endDateTime} " +
            "AND m.property.id = :#{#messageListInnerFilter.propertyId}")
    Integer countAllByIdWithFilterIncomingProperty(MessageListInnerFilter messageListInnerFilter);


    @Query("SELECT m FROM Message m " +
            "WHERE m.sentState = :#{#messageListInnerFilter.messageSentState} " +
            "AND m.from.email = :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email <> :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.timestampCreated > :#{#messageListInnerFilter.startDateTime} " +
            "AND m.timestampCreated < :#{#messageListInnerFilter.endDateTime}")
    Page<Message> findAllWithFilterSent(Pageable paging, MessageListInnerFilter messageListInnerFilter);

    @Query("SELECT count(m.id) FROM Message m " +
            "WHERE m.sentState = :#{#messageListInnerFilter.messageSentState} " +
            "AND m.from.email = :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email <> :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.timestampCreated > :#{#messageListInnerFilter.startDateTime} " +
            "AND m.timestampCreated < :#{#messageListInnerFilter.endDateTime}")
    Integer countAllByIdWithFilterSent(MessageListInnerFilter messageListInnerFilter);

    @Query("SELECT m FROM Message m " +
            "WHERE m.sentState = :#{#messageListInnerFilter.messageSentState} " +
            "AND m.from.email = :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email <> :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.timestampCreated > :#{#messageListInnerFilter.startDateTime} " +
            "AND m.timestampCreated < :#{#messageListInnerFilter.endDateTime} " +
            "AND m.property.id = :#{#messageListInnerFilter.propertyId}")
    Page<Message> findAllWithFilterSentProperty(Pageable paging, MessageListInnerFilter messageListInnerFilter);

    @Query("SELECT count(m.id) FROM Message m " +
            "WHERE m.sentState = :#{#messageListInnerFilter.messageSentState} " +
            "AND m.from.email = :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email <> :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.timestampCreated > :#{#messageListInnerFilter.startDateTime} " +
            "AND m.timestampCreated < :#{#messageListInnerFilter.endDateTime} " +
            "AND m.property.id = :#{#messageListInnerFilter.propertyId}")
    Integer countAllByIdWithFilterSentProperty(MessageListInnerFilter messageListInnerFilter);


    @Query("SELECT NEW com.progmasters.moovsmart.dto.PropertyDetailsMessageInitData(m) " +
            "FROM Message m " +
            "WHERE m.property.state = :propertyState " +
            "AND m.incomingState = :#{#messageListInnerFilter.messageIncomingState} " +
            "AND m.from.email <> :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email = :#{#messageListInnerFilter.authenticatedEmail}")
    List<PropertyDetailsMessageInitData> findPropertiesForIncomingMessages(
            @Param("propertyState") PropertyState propertyState, MessageListInnerFilter messageListInnerFilter);

    @Query("SELECT NEW com.progmasters.moovsmart.dto.PropertyDetailsMessageInitData(m) " +
            "FROM Message m " +
            "WHERE m.property.state = :propertyState " +
            "AND m.sentState = :#{#messageListInnerFilter.messageSentState} " +
            "AND m.from.email = :#{#messageListInnerFilter.authenticatedEmail} " +
            "AND m.to.email <> :#{#messageListInnerFilter.authenticatedEmail} ")
    List<PropertyDetailsMessageInitData> findPropertiesForSentMessages(
            @Param("propertyState") PropertyState propertyState, MessageListInnerFilter messageListInnerFilter);

    @Query("SELECT COUNT(m.unRead) from Message m WHERE m.to.email = :username " +
            "and m.unRead = true and m.incomingState = :incomingState")
    Long findCounterUnreadMessages(@Param("username") String username,
                                   @Param("incomingState") MessageIncomingState incomingState);

    @Query("SELECT m FROM Message m " +
            "WHERE (m.from.email = :#{#messageThreadFilter.authenticatedEmail} " +
            "AND m.to.email = :#{#messageThreadFilter.emailOtherPerson} " +
            "AND m.sentState = :#{#messageThreadFilter.messageSentState} " +
            "AND m.property.id = :#{#messageThreadFilter.propertyId} " +
            "OR m.from.email = :#{#messageThreadFilter.emailOtherPerson} " +
            "AND m.to.email = :#{#messageThreadFilter.authenticatedEmail} " +
            "AND m.incomingState = :#{#messageThreadFilter.messageIncomingState} " +
            "AND m.property.id = :#{#messageThreadFilter.propertyId} )" +
            "ORDER BY m.timestampCreated ASC")
    List<Message> findAllFilteredMessagesForThread(MessageThreadFilter messageThreadFilter);

}
