package com.progmasters.moovsmart.domain;

import com.progmasters.moovsmart.dto.MessageForm;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "timestamp_created")
    private LocalDateTime timestampCreated;

    @Column(name = "unread")
    private Boolean unRead = true;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Account from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Account to;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Enumerated(EnumType.STRING)
    @Column(name = "incoming_state")
    private MessageIncomingState incomingState = MessageIncomingState.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "sent_state")
    private MessageSentState sentState = MessageSentState.ACTIVE;

    public Message() {
    }

    public Message(MessageForm messageForm) {
        this.content = messageForm.getContent();
        this.timestampCreated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(LocalDateTime timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public MessageIncomingState getIncomingState() {
        return incomingState;
    }

    public void setIncomingState(MessageIncomingState state) {
        this.incomingState = state;
    }

    public MessageSentState getSentState() {
        return sentState;
    }

    public void setSentState(MessageSentState sentState) {
        this.sentState = sentState;
    }

    public Boolean getUnRead() {
        return unRead;
    }

    public void setUnRead(Boolean unRead) {
        this.unRead = unRead;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
