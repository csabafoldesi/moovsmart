package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {

    private MessageRepository messageRepository;
    private AccountService accountService;
    private PropertyService propertyService;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountService accountService,
                          @Lazy PropertyService propertyService) {
        this.messageRepository = messageRepository;
        this.accountService = accountService;
        this.propertyService = propertyService;
    }

    public MessageList getActiveAccountMessages(Integer page, Integer pageSize, String sortBy,
                                                MessageListFilter messageListFilter) {
        Pageable paging;
        if (sortBy.contains(".desc")) {
            int toDeleteFrom = sortBy.lastIndexOf('.');
            sortBy = sortBy.substring(0, toDeleteFrom);
            paging = PageRequest.of(page, pageSize, Sort.by(sortBy).descending().and(Sort.by("id").descending()));
        } else {
            paging = PageRequest.of(page, pageSize, Sort.by(sortBy).and(Sort.by("id")));
        }
        MessageList messageListWithPages = new MessageList();
        Page<Message> messages = getMessages(messageListFilter, paging, messageListWithPages);

        Page<MessageListItem> pagedMessages = null;
        if (messages != null) {
            pagedMessages = messages.map(MessageListItem::new);
        }
        if (pagedMessages != null && pagedMessages.hasContent()) {
            messageListWithPages.setMessageListItems(pagedMessages.getContent());
        }
        return messageListWithPages;
    }

    private Page<Message> getMessages(MessageListFilter messageListFilter, Pageable paging,
                                      MessageList messageListWithPages) {
        Page<Message> messages;
        if (messageListFilter.isIncoming()) {
            messages = getMessagesIncoming(messageListFilter, paging, messageListWithPages);
        } else {
            messages = getMessagesSent(messageListFilter, paging, messageListWithPages);
        }
        return messages;
    }

    private Page<Message> getMessagesIncoming(MessageListFilter messageListFilter,
                                              Pageable paging, MessageList messageListWithPages) {
        Page<Message> messages;
        MessageListInnerFilter messageListInnerFilter = new MessageListInnerFilter();
        messageListInnerFilter.setAuthenticatedEmail(getUserDetails().getUsername());
        messageListInnerFilter.setMessageIncomingState(MessageIncomingState.ACTIVE);
        parseDateToDateTime(messageListFilter, messageListInnerFilter);

        if (messageListFilter.getPropertyId() == 0) {
            messages = messageRepository.findAllWithFilterIncoming(
                    paging, messageListInnerFilter);
            messageListWithPages.setTotalItems(messageRepository.countAllByIdWithFilterIncoming(
                    messageListInnerFilter));
        } else {
            messageListInnerFilter.setPropertyId(messageListFilter.getPropertyId());
            messages = messageRepository.findAllWithFilterIncomingProperty(
                    paging, messageListInnerFilter);
            messageListWithPages.setTotalItems(messageRepository.countAllByIdWithFilterIncomingProperty(
                    messageListInnerFilter));
        }
        return messages;
    }

    private Page<Message> getMessagesSent(MessageListFilter messageListFilter,
                                          Pageable paging, MessageList messageListWithPages) {
        Page<Message> messages;
        MessageListInnerFilter messageListInnerFilter = new MessageListInnerFilter();
        messageListInnerFilter.setAuthenticatedEmail(getUserDetails().getUsername());
        messageListInnerFilter.setMessageSentState(MessageSentState.ACTIVE);
        parseDateToDateTime(messageListFilter, messageListInnerFilter);

        if (messageListFilter.getPropertyId() == 0) {
            messages = messageRepository.findAllWithFilterSent(
                    paging, messageListInnerFilter);
            messageListWithPages.setTotalItems(messageRepository.countAllByIdWithFilterSent(
                    messageListInnerFilter));
        } else {
            messageListInnerFilter.setPropertyId(messageListFilter.getPropertyId());
            messages = messageRepository.findAllWithFilterSentProperty(
                    paging, messageListInnerFilter);
            messageListWithPages.setTotalItems(messageRepository.countAllByIdWithFilterSentProperty(
                    messageListInnerFilter));
        }
        return messages;
    }

    private void parseDateToDateTime(MessageListFilter messageListFilter, MessageListInnerFilter messageListInnerFilter) {
        if (messageListFilter.getStartDate() != null
                && !messageListFilter.getStartDate().toString().isEmpty()) {
            messageListInnerFilter.setStartDateTime(LocalDateTime.of(
                    messageListFilter.getStartDate().getYear(), messageListFilter.getStartDate().getMonth(),
                    messageListFilter.getStartDate().getDayOfMonth(), 0, 0));
        }
        if (messageListFilter.getEndDate() != null
                && !messageListFilter.getEndDate().toString().isEmpty()) {
            messageListInnerFilter.setEndDateTime(LocalDateTime.of(
                    messageListFilter.getEndDate().getYear(), messageListFilter.getEndDate().getMonth(),
                    messageListFilter.getEndDate().getDayOfMonth(), 23, 59));
        }
    }

    public List<PropertyDetailsMessageInitData> getPropertiesForMessages(boolean isIncoming) {
        List<PropertyDetailsMessageInitData> propertyDetailsMessageInitData;
        MessageListInnerFilter messageListInnerFilter = new MessageListInnerFilter();
        messageListInnerFilter.setAuthenticatedEmail(getUserDetails().getUsername());
        if (isIncoming) {
            messageListInnerFilter.setMessageIncomingState(MessageIncomingState.ACTIVE);
            propertyDetailsMessageInitData = messageRepository.findPropertiesForIncomingMessages(
                    PropertyState.ACTIVE, messageListInnerFilter);
        } else {
            messageListInnerFilter.setMessageSentState(MessageSentState.ACTIVE);
            propertyDetailsMessageInitData = messageRepository.findPropertiesForSentMessages(
                    PropertyState.ACTIVE, messageListInnerFilter);
        }

        List<PropertyDetailsMessageInitData> filteredList = new ArrayList<>();
        Map<Long, String> propertyMap = new HashMap<>();
        propertyDetailsMessageInitData
                .forEach(p -> propertyMap.put(p.getPropertyId(), p.getPropertyName()));
        Set<Long> set = propertyMap.keySet();
        for (Long propertyId : set) {
            filteredList.add(new PropertyDetailsMessageInitData(propertyId, propertyMap.get(propertyId)));
        }
        return filteredList;
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public BadgeCounter getBadgeCounter() {
        Long counterUnreadMessages = messageRepository.findCounterUnreadMessages(
                getUserDetails().getUsername(), MessageIncomingState.ACTIVE);
        return new BadgeCounter(counterUnreadMessages);
    }

    public List<MessageThreadListItem> getActiveThreadMessagesForMessage(String emailOtherPerson, Long propertyId) {
        MessageThreadFilter messageThreadFilter = new MessageThreadFilter();
        messageThreadFilter.setMessageIncomingState(MessageIncomingState.ACTIVE);
        messageThreadFilter.setMessageSentState(MessageSentState.ACTIVE);
        messageThreadFilter.setAuthenticatedEmail(getUserDetails().getUsername());
        messageThreadFilter.setEmailOtherPerson(emailOtherPerson);
        messageThreadFilter.setPropertyId(propertyId);
        List<Message> threadMessages = messageRepository.findAllFilteredMessagesForThread(messageThreadFilter);
        return threadMessages
                .stream()
                .map(MessageThreadListItem::new)
                .collect(Collectors.toList());
    }

    public Long createMessage(MessageForm messageForm) {
        Message message = new Message(messageForm);
        Account accountByEmail = accountService.findAccountByEmail(getUserDetails().getUsername());
        message.setFrom(accountByEmail);
        Account accountById = accountService.findAccountById(messageForm.getToId());
        message.setTo(accountById);
        Property propertyById = propertyService.findPropertyById(messageForm.getPropertyId());
        message.setProperty(propertyById);
        Message savedMessage = messageRepository.save(message);
        return savedMessage.getId();
    }

    public boolean readMessage(Long messageId) {
        Message message = findMessageById(messageId);
        message.setUnRead(false);
        return true;
    }

    public boolean deleteIncomingMessage(Long messageId) {
        boolean isSuccessfulDeletion = false;
        String emailLoggedInUser = getUserDetails().getUsername();
        Message message = findMessageById(messageId);
        if (message.getTo().getEmail().equals(emailLoggedInUser)) {
            message.setIncomingState(MessageIncomingState.INACTIVE);
            isSuccessfulDeletion = true;
        }
        return isSuccessfulDeletion;
    }

    public boolean deleteSentMessage(Long messageId) {
        boolean isSuccessfulDeletion = false;
        String emailLoggedInUser = getUserDetails().getUsername();
        Message message = findMessageById(messageId);
        if (message.getFrom().getEmail().equals(emailLoggedInUser)) {
            message.setSentState(MessageSentState.INACTIVE);
            isSuccessfulDeletion = true;
        }
        return isSuccessfulDeletion;
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    private Message findMessageById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message not found with given id: " + messageId));
    }

}
