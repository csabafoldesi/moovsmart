package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.service.MessageService;
import com.progmasters.moovsmart.validation.MessageFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageFormValidator messageFormValidator;
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService, MessageFormValidator messageFormValidator) {
        this.messageService = messageService;
        this.messageFormValidator = messageFormValidator;
    }

    @InitBinder("messageForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(messageFormValidator);
    }

    @GetMapping
    public ResponseEntity<MessageList> getActiveAccountMessages(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id.desc") String sortBy,
            MessageListFilter messageListFilter) {
        logger.debug("Message list requested for message page.");
        return new ResponseEntity<>(messageService.getActiveAccountMessages(
                page, pageSize, sortBy, messageListFilter), HttpStatus.OK);
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDetailsMessageInitData>> getPropertiesForMessages(
            @RequestParam(name = "incoming") boolean isIncoming) {
        logger.debug("Advertisements requested for messages.");
        return new ResponseEntity<>(messageService.getPropertiesForMessages(isIncoming), HttpStatus.OK);
    }

    @GetMapping("/current-date")
    public ResponseEntity<LocalDate> getCurrentDate() {
        logger.debug("Current date requested for message page.");
        return new ResponseEntity<>(messageService.getCurrentDate(), HttpStatus.OK);
    }

    @GetMapping("/badgeCounter")
    public ResponseEntity<BadgeCounter> getBadgeCounter() {
        logger.debug("Badge counter of navbar requested for unread messages.");
        return new ResponseEntity<>(messageService.getBadgeCounter(), HttpStatus.OK);
    }

    @GetMapping("/thread/email/{emailOtherPerson}/property/{propertyId}")
    public ResponseEntity<List<MessageThreadListItem>> getActiveThreadMessagesForMessage(@PathVariable String emailOtherPerson,
                                                                                         @PathVariable Long propertyId) {
        logger.debug("Thread message list requested for message with email: {}", emailOtherPerson);
        return new ResponseEntity<>(messageService.getActiveThreadMessagesForMessage(
                emailOtherPerson, propertyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createMessage(@RequestBody @Valid MessageForm messageForm) {
        logger.debug("Message creation requested...");
        Long messageId = messageService.createMessage(messageForm);
        logger.debug("Message successfully created with id {}", messageId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/read/{messageId}")
    public ResponseEntity<Void> readMessage(@PathVariable("messageId") Long messageId) {
        if (messageService.readMessage(messageId)) {
            logger.debug("Message is read with id: {}", messageId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.debug("Unable to read message with id: {}", messageId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/incoming/{messageId}")
    public ResponseEntity<Void> deleteIncomingMessage(@PathVariable("messageId") Long messageId) {
        if (messageService.deleteIncomingMessage(messageId)) {
            logger.debug("Delete (inactivate) incoming message with id: {}", messageId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.debug("Unable to delete (inactivate) incoming message with id: {}", messageId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/sent/{messageId}")
    public ResponseEntity<Void> deleteSentMessage(@PathVariable("messageId") Long messageId) {
        if (messageService.deleteSentMessage(messageId)) {
            logger.debug("Delete (inactivate) sent message with id: {}", messageId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.debug("Unable to delete (inactivate) sent message with id: {}", messageId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
